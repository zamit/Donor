package com.example.donor.ui.book

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "books.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_BOOKS = "books"
        private const val COLUMN_ID = "id"
        private const val COLUMN_DATA = "data"
        private const val COLUMN_HOSPITAL = "hospital"
        private const val COLUMN_KOMMENTARII = "kommentarii"

        private const val CREATE_TABLE = """
            CREATE TABLE $TABLE_BOOKS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DATA TEXT,
                $COLUMN_HOSPITAL TEXT,
                $COLUMN_KOMMENTARII TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKS")
        onCreate(db)
    }

    fun addBook(book: Book): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DATA, book.data)
            put(COLUMN_HOSPITAL, book.hospital)
            put(COLUMN_KOMMENTARII, book.kommentarii)
        }

        val id = db.insert(TABLE_BOOKS, null, values)
        db.close()
        return id
    }

    fun getAllBooks(): List<Book> {
        val books = mutableListOf<Book>()  // Инициализация списка
        val selectQuery = "SELECT * FROM $TABLE_BOOKS ORDER BY $COLUMN_ID DESC"

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                   val book = Book(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA)),
                    hospital = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOSPITAL)),
                    kommentarii = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KOMMENTARII))
                   )
                    books.add(book)  // Добавление в список
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return books
    }
}