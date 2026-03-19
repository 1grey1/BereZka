mod db;
mod models;
mod repository;
mod schema;

use std::net::SocketAddr;

use argon2::{
    password_hash::{
        rand_core::OsRng,
        PasswordHash, PasswordHasher, PasswordVerifier, SaltString,
    },
    Argon2,
};
use axum::{
    extract::State,
    http::StatusCode,
    routing::{get, post},
    Json, Router,
};
use diesel::result::{DatabaseErrorKind, Error as DieselError};
use repository::UserRepository;
use serde::{Deserialize, Serialize};

#[derive(Clone)]
struct AppState {
    pool: db::DbPool,
}

#[derive(Deserialize)]
struct AuthRequest {
    nickname: String,
    password: String,
}

#[derive(Serialize)]
struct MessageResponse {
    message: String,
}

#[derive(Serialize)]
struct LoginResponse {
    message: String,
    user_id: i64,
    nickname: String,
}

#[tokio::main]
async fn main() {
    let pool = db::establish_pool();

    let app_state = AppState { pool };

    let app = Router::new()
        .route("/health", get(health))
        .route("/register", post(register))
        .route("/login", post(login))
        .with_state(app_state);

    let addr: SocketAddr = "127.0.0.1:8000"
        .parse()
        .expect("Invalid listen address");

    println!("Server started on http://{}", addr);

    let listener = tokio::net::TcpListener::bind(addr)
        .await
        .expect("Failed to bind TCP listener");

    axum::serve(listener, app)
        .await
        .expect("Server crashed");
}

async fn health() -> &'static str {
    "OK"
}

async fn register(
    State(state): State<AppState>,
    Json(payload): Json<AuthRequest>,
) -> Result<(StatusCode, Json<MessageResponse>), (StatusCode, Json<MessageResponse>)> {
    if payload.nickname.trim().is_empty() || payload.password.trim().is_empty() {
        return Err((
            StatusCode::BAD_REQUEST,
            Json(MessageResponse {
                message: "Nickname and password must not be empty".to_string(),
            }),
        ));
    }

    let password_hash = hash_password(&payload.password).map_err(|_| {
        (
            StatusCode::INTERNAL_SERVER_ERROR,
            Json(MessageResponse {
                message: "Failed to hash password".to_string(),
            }),
        )
    })?;

    let mut conn = state.pool.get().map_err(|_| {
        (
            StatusCode::INTERNAL_SERVER_ERROR,
            Json(MessageResponse {
                message: "Failed to get DB connection".to_string(),
            }),
        )
    })?;

    let result = UserRepository::create_user(&mut conn, &payload.nickname, &password_hash);

    match result {
        Ok(_) => Ok((
            StatusCode::CREATED,
            Json(MessageResponse {
                message: "User created".to_string(),
            }),
        )),
        Err(DieselError::DatabaseError(DatabaseErrorKind::UniqueViolation, _)) => Err((
            StatusCode::CONFLICT,
            Json(MessageResponse {
                message: "Nickname already exists".to_string(),
            }),
        )),
        Err(_) => Err((
            StatusCode::INTERNAL_SERVER_ERROR,
            Json(MessageResponse {
                message: "Failed to create user".to_string(),
            }),
        )),
    }
}

async fn login(
    State(state): State<AppState>,
    Json(payload): Json<AuthRequest>,
) -> Result<(StatusCode, Json<LoginResponse>), (StatusCode, Json<MessageResponse>)> {
    if payload.nickname.trim().is_empty() || payload.password.trim().is_empty() {
        return Err((
            StatusCode::BAD_REQUEST,
            Json(MessageResponse {
                message: "Nickname and password must not be empty".to_string(),
            }),
        ));
    }

    let mut conn = state.pool.get().map_err(|_| {
        (
            StatusCode::INTERNAL_SERVER_ERROR,
            Json(MessageResponse {
                message: "Failed to get DB connection".to_string(),
            }),
        )
    })?;

    let user = match UserRepository::find_by_nickname(&mut conn, &payload.nickname) {
        Ok(user) => user,
        Err(DieselError::NotFound) => {
            return Err((
                StatusCode::UNAUTHORIZED,
                Json(MessageResponse {
                    message: "Invalid nickname or password".to_string(),
                }),
            ));
        }
        Err(_) => {
            return Err((
                StatusCode::INTERNAL_SERVER_ERROR,
                Json(MessageResponse {
                    message: "Failed to read user".to_string(),
                }),
            ));
        }
    };

    let is_valid = verify_password(&payload.password, &user.password_hash).map_err(|_| {
        (
            StatusCode::INTERNAL_SERVER_ERROR,
            Json(MessageResponse {
                message: "Failed to verify password".to_string(),
            }),
        )
    })?;

    if !is_valid {
        return Err((
            StatusCode::UNAUTHORIZED,
            Json(MessageResponse {
                message: "Invalid nickname or password".to_string(),
            }),
        ));
    }

    Ok((
        StatusCode::OK,
        Json(LoginResponse {
            message: "Login successful".to_string(),
            user_id: user.id,
            nickname: user.nickname,
        }),
    ))
}

fn hash_password(password: &str) -> Result<String, argon2::password_hash::Error> {
    let salt = SaltString::generate(&mut OsRng);
    let argon2 = Argon2::default();

    let password_hash = argon2.hash_password(password.as_bytes(), &salt)?;
    Ok(password_hash.to_string())
}

fn verify_password(password: &str, password_hash: &str) -> Result<bool, argon2::password_hash::Error> {
    let parsed_hash = PasswordHash::new(password_hash)?;
    let argon2 = Argon2::default();

    Ok(argon2
        .verify_password(password.as_bytes(), &parsed_hash)
        .is_ok())
}

