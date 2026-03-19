use diesel::pg::PgConnection;
use diesel::prelude::*;
use diesel::result::Error;

use crate::models::{NewUser, User};
use crate::schema::users;
use crate::schema::users::dsl::{id, nickname, users as users_table};

// Методы для работы с таблицей users.
pub struct UserRepository;

impl UserRepository {
    // Создает пользователя и возвращает созданную запись.
    pub fn create_user(
        conn: &mut PgConnection,
        user_nickname: &str,
        user_password_hash: &str,
    ) -> Result<User, Error> {
        let new_user = NewUser {
            nickname: user_nickname,
            password_hash: user_password_hash,
        };

        // Вставляет запись в users и сразу получает результат как User.
        diesel::insert_into(users::table)
            .values(&new_user)
            .returning(User::as_returning())
            .get_result(conn)
    }

    // Ищет пользователя по nickname.
    pub fn find_by_nickname(
        conn: &mut PgConnection,
        user_nickname: &str,
    ) -> Result<User, Error> {
        users_table
            .filter(nickname.eq(user_nickname))
            .select(User::as_select())
            .first(conn)
    }

    // Ищет пользователя по id.
    pub fn find_by_id(
        conn: &mut PgConnection,
        user_id_value: i64,
    ) -> Result<User, Error> {
        users_table
            .filter(id.eq(user_id_value))
            .select(User::as_select())
            .first(conn)
    }
}
