diesel::table! {
    users (id) {
        id -> Int8,
        nickname -> Varchar,
        password_hash -> Varchar,
        created_at -> Timestamp,
    }
}
