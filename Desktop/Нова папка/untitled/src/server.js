const express = require('express');
const { v4: uuidv4 } = require('uuid');

const app = express();
app.use(express.json());

// In-memory "database"
let books = [
    { id: '1', title: 'Clean Code', author: 'Robert C. Martin', year: 2008, genre: 'Programming' },
    { id: '2', title: 'The Pragmatic Programmer', author: 'David Thomas', year: 1999, genre: 'Programming' },
    { id: '3', title: 'Design Patterns', author: 'Gang of Four', year: 1994, genre: 'Programming' },
];

// ─── GET /api/books ──────────────────────────────────────────────────────────
// Returns all books (supports ?genre= filter)
app.get('/api/books', (req, res) => {
    const { genre } = req.query;
    let result = books;

    if (genre) {
        result = books.filter(b => b.genre.toLowerCase() === genre.toLowerCase());
    }

    res.status(200).json({
        status: 'success',
        count: result.length,
        data: result,
    });
});

// ─── GET /api/books/:id ───────────────────────────────────────────────────────
// Returns a single book by ID
app.get('/api/books/:id', (req, res) => {
    const book = books.find(b => b.id === req.params.id);

    if (!book) {
        return res.status(404).json({
            status: 'error',
            message: `Book with id '${req.params.id}' not found`,
        });
    }

    res.status(200).json({
        status: 'success',
        data: book,
    });
});

// ─── POST /api/books ──────────────────────────────────────────────────────────
// Creates a new book
app.post('/api/books', (req, res) => {
    const { title, author, year, genre } = req.body;

    if (!title || !author || !year || !genre) {
        return res.status(400).json({
            status: 'error',
            message: 'Fields title, author, year and genre are required',
        });
    }

    const newBook = {
        id: uuidv4(),
        title,
        author,
        year: Number(year),
        genre,
    };

    books.push(newBook);

    res.status(201).json({
        status: 'success',
        data: newBook,
    });
});

// ─── PATCH /api/books/:id ─────────────────────────────────────────────────────
// Partially updates a book
app.patch('/api/books/:id', (req, res) => {
    const index = books.findIndex(b => b.id === req.params.id);

    if (index === -1) {
        return res.status(404).json({
            status: 'error',
            message: `Book with id '${req.params.id}' not found`,
        });
    }

    const allowedFields = ['title', 'author', 'year', 'genre'];
    const updates = {};

    for (const key of allowedFields) {
        if (req.body[key] !== undefined) {
            updates[key] = key === 'year' ? Number(req.body[key]) : req.body[key];
        }
    }

    if (Object.keys(updates).length === 0) {
        return res.status(400).json({
            status: 'error',
            message: 'No valid fields provided for update',
        });
    }

    books[index] = { ...books[index], ...updates };

    res.status(200).json({
        status: 'success',
        data: books[index],
    });
});

// ─── DELETE /api/books/:id ────────────────────────────────────────────────────
// Deletes a book by ID
app.delete('/api/books/:id', (req, res) => {
    const index = books.findIndex(b => b.id === req.params.id);

    if (index === -1) {
        return res.status(404).json({
            status: 'error',
            message: `Book with id '${req.params.id}' not found`,
        });
    }

    books.splice(index, 1);

    res.status(204).send();
});

// ─── 404 handler ─────────────────────────────────────────────────────────────
app.use((req, res) => {
    res.status(404).json({
        status: 'error',
        message: `Route ${req.method} ${req.originalUrl} not found`,
    });
});

// ─── Start ────────────────────────────────────────────────────────────────────
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});

module.exports = app;
