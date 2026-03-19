use diesel::pg::PgConnection;
use diesel::r2d2::{self, ConnectionManager};
use dotenvy::dotenv;
use std::env;

// Удобное короткое имя для пула подключений к PostgreSQL.
pub type DbPool = r2d2::Pool<ConnectionManager<PgConnection>>;
// Удобное короткое имя для одного подключения, взятого из пула.
pub type DbPooledConnection = r2d2::PooledConnection<ConnectionManager<PgConnection>>;

pub fn establish_pool() -> DbPool {
    // Загружаем переменные из файла .env, если он существует.
    dotenv().ok();

    // Берем адрес базы данных из переменной окружения DATABASE_URL.
    let database_url = env::var("DATABASE_URL")
        .expect("DATABASE_URL must be set");

    // Создаем менеджер, который умеет открывать PostgreSQL-подключения.
    let manager = ConnectionManager::<PgConnection>::new(database_url);

    // Создаем пул соединений, чтобы не открывать новое подключение каждый раз вручную.
    r2d2::Pool::builder()
        .build(manager)
        .expect("Failed to create DB pool")
}
