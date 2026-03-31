# 📚 Books REST API

RESTful API для управління колекцією книг. Реалізовано з використанням **Node.js** та **Express**.

---

## 🚀 Запуск проєкту

### Передумови

- Встановлений [Node.js](https://nodejs.org/) версії **18** або вище
- Встановлений **npm** (входить у комплект з Node.js)

### Кроки для запуску

1. **Клонуйте репозиторій:**

```bash
git clone https://github.com/<ваш-username>/<назва-репозиторію>.git
cd <назва-репозиторію>
```

2. **Встановіть залежності:**

```bash
npm install
```

3. **Запустіть сервер:**

```bash
# Звичайний запуск
npm start

# Або режим розробки (з автоперезавантаженням)
npm run dev
```

4. **Сервер буде доступний за адресою:**

```
http://localhost:3000
```

---

## 📋 Ресурс API

Ресурс: **`/api/books`** — колекція книг.

### Модель даних `Book`

| Поле     | Тип      | Опис                   |
|----------|----------|------------------------|
| `id`     | `string` | Унікальний ідентифікатор (UUID) |
| `title`  | `string` | Назва книги            |
| `author` | `string` | Автор книги            |
| `year`   | `number` | Рік видання            |
| `genre`  | `string` | Жанр книги             |

---

## 🔌 Endpoints

### GET `/api/books`

Отримати список усіх книг. Підтримує фільтрацію за жанром.

**Query параметри:**
- `genre` (опціонально) — фільтр за жанром

**Приклад запиту:**
```
GET http://localhost:3000/api/books
GET http://localhost:3000/api/books?genre=Programming
```

**Відповідь `200 OK`:**
```json
{
  "status": "success",
  "count": 3,
  "data": [
    { "id": "1", "title": "Clean Code", "author": "Robert C. Martin", "year": 2008, "genre": "Programming" }
  ]
}
```

---

### GET `/api/books/:id`

Отримати одну книгу за ID.

**Приклад запиту:**
```
GET http://localhost:3000/api/books/1
```

**Відповідь `200 OK`:**
```json
{
  "status": "success",
  "data": { "id": "1", "title": "Clean Code", "author": "Robert C. Martin", "year": 2008, "genre": "Programming" }
}
```

**Відповідь `404 Not Found`:**
```json
{
  "status": "error",
  "message": "Book with id '99' not found"
}
```

---

### POST `/api/books`

Створити нову книгу.

**Body (JSON):**
```json
{
  "title": "The Clean Coder",
  "author": "Robert C. Martin",
  "year": 2011,
  "genre": "Programming"
}
```

**Відповідь `201 Created`:**
```json
{
  "status": "success",
  "data": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "title": "The Clean Coder",
    "author": "Robert C. Martin",
    "year": 2011,
    "genre": "Programming"
  }
}
```

**Відповідь `400 Bad Request`** (якщо пропущені обов'язкові поля):
```json
{
  "status": "error",
  "message": "Fields title, author, year and genre are required"
}
```

---

### PATCH `/api/books/:id`

Частково оновити дані книги (одне або кілька полів).

**Приклад запиту:**
```
PATCH http://localhost:3000/api/books/1
```

**Body (JSON):**
```json
{
  "year": 2009
}
```

**Відповідь `200 OK`:**
```json
{
  "status": "success",
  "data": { "id": "1", "title": "Clean Code", "author": "Robert C. Martin", "year": 2009, "genre": "Programming" }
}
```

---

### DELETE `/api/books/:id`

Видалити книгу за ID.

**Приклад запиту:**
```
DELETE http://localhost:3000/api/books/1
```

**Відповідь `204 No Content`** — порожнє тіло відповіді (успішне видалення).

**Відповідь `404 Not Found`:**
```json
{
  "status": "error",
  "message": "Book with id '1' not found"
}
```

---

## 📌 HTTP Статус-коди

| Код  | Значення                         |
|------|----------------------------------|
| 200  | OK — успішний запит              |
| 201  | Created — ресурс створено        |
| 204  | No Content — видалення успішне   |
| 400  | Bad Request — некоректні дані    |
| 404  | Not Found — ресурс не знайдено   |

---

## 🧰 Технічний стек

- **Runtime:** Node.js 18+
- **Framework:** Express 4
- **Генерація ID:** uuid v9
- **Зберігання даних:** In-memory (масив)

---

## 📄 Документація Postman

**Посилання на документацію:** `https://documenter.getpostman.com/view/XXXXXXX/XXXXXXX`

---

## 📂 Структура проєкту

```
├── src/
│   └── server.js       # Основний файл сервера та всі маршрути
├── .gitignore
├── package.json
└── README.md
```
