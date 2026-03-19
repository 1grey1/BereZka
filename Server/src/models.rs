use chrono::NaiveDateTime;
use diesel::prelude::*;

// Подключаем описание таблицы users из schema.rs,
// чтобы Diesel понимал, с какой таблицей связаны наши структуры.
use crate::schema::users;

// Эта структура описывает одну готовую запись, прочитанную из таблицы users.
// Проще говоря: когда мы делаем SELECT из базы, результат можно сохранить в User.
#[derive(Debug, Queryable, Selectable)]
// Говорим Diesel, что структура User относится именно к таблице users.
#[diesel(table_name = users)]
pub struct User {
    // Уникальный идентификатор пользователя.
    pub id: i64,
    // Никнейм пользователя.
    pub nickname: String,
    // Хэш пароля, а не сам пароль в открытом виде.
    pub password_hash: String,
    // Дата и время создания пользователя.
    // NaiveDateTime хранит дату и время без информации о часовом поясе.
    pub created_at: NaiveDateTime,
}

// Эта структура нужна для вставки нового пользователя в базу.
// Обычно при INSERT не передают id и created_at вручную:
// id часто создается самой базой автоматически,
// а created_at может подставляться через DEFAULT CURRENT_TIMESTAMP.
#[derive(Insertable)]
// Говорим Diesel, что NewUser используется для вставки в таблицу users.
#[diesel(table_name = users)]
pub struct NewUser<'a> {
    // Ссылка на строку с никнеймом.
    // &'a str используется, чтобы не копировать данные без необходимости.
    pub nickname: &'a str,
    // Ссылка на строку с хэшем пароля.
    pub password_hash: &'a str,
}
