# **Berezka API**
## **Основная информация**


**Домен API:** api.zxc1000-7.online

**Base URL:** https://api.zxc1000-7.online/

**Протокол:** HTTPS

**Формат запросов и ответов:** application/json

**Кодировка:** UTF-8

  

## **Как обращаться к API**

  

Фронтенд должен отправлять запросы на домен:

```
https://api.zxc1000-7.online/
```

То есть полный адрес каждого endpoint строится так:

- health: https://api.zxc1000-7.online/health
    
- register: https://api.zxc1000-7.online/register
    
- login: https://api.zxc1000-7.online/login
    

  

## **Назначение API**

  

Данный API используется мобильным клиентом для:

- проверки доступности сервера
    
- регистрации нового пользователя
    
- входа пользователя в систему
    

---

## **Доступные endpoint’ы**

  

### **GET /health**

  

Проверка, что backend доступен и работает.

  

**Полный URL:**

```
https://api.zxc1000-7.online/health
```

**Успешный ответ:**

- 200 OK
    

  

Тело ответа:

```
OK
```

---

### **POST /register**

  

Регистрация нового пользователя.

  

**Полный URL:**

```
https://api.zxc1000-7.online/register
```

**Тело запроса:**

```
{
  "nickname": "string",
  "password": "string"
}
```

**Успешный ответ:**

- 201 Created
    

```
{
  "message": "User created"
}
```

**Ошибки:**

  

400 Bad Request

```
{
  "message": "Nickname and password must not be empty"
}
```

409 Conflict

```
{
  "message": "Nickname already exists"
}
```

500 Internal Server Error

```
{
  "message": "Failed to create user"
}
```

---

### **POST /login**

  

Вход пользователя в систему.

  

**Полный URL:**

```
https://api.zxc1000-7.online/login
```

**Тело запроса:**

```
{
  "nickname": "string",
  "password": "string"
}
```

**Успешный ответ:**

- 200 OK
    

```
{
  "message": "Login successful",
  "user_id": 0,
  "nickname": "string"
}
```

**Ошибки:**

  

400 Bad Request

```
{
  "message": "Nickname and password must not be empty"
}
```

401 Unauthorized

```
{
  "message": "Invalid nickname or password"
}
```

500 Internal Server Error

```
{
  "message": "Failed to read user"
}
```

или

```
{
  "message": "Failed to verify password"
}
```

---

## **Модели данных**

### **AuthRequest**

Используется для /register и /login.

```
{
  "nickname": "string",
  "password": "string"
}
```

### **MessageResponse**

```
{
  "message": "string"
}
```

### **LoginResponse**

```
{
  "message": "string",
  "user_id": 0,
  "nickname": "string"
}
```

---
## **Таблица API**

|**Метод**|**Endpoint**|**Полный URL**|**Назначение**|
|---|---|---|---|
|GET|/health|https://api.zxc1000-7.online/health|Проверка доступности API|
|POST|/register|https://api.zxc1000-7.online/register|Регистрация пользователя|
|POST|/login|https://api.zxc1000-7.online/login|Вход пользователя|

---
## **Что использовать на фронтенде**

Для Android/Kotlin в качестве baseUrl нужно использовать:

```
https://api.zxc1000-7.online/
```

Для всех POST запросов отправлять заголовок:

```
Content-Type: application/json
```

---
## **Текущие ограничения API**

На текущем этапе API:

- не возвращает access token
- не возвращает refresh token
- не поддерживает logout
- не поддерживает /me
- не поддерживает восстановление пароля
- не поддерживает защищённые маршруты

То есть сейчас API покрывает только базовую регистрацию и логин.
