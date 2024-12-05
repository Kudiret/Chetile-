package com.example.chetile

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HadithDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "HadithCollection.db"  // Имя базы данных
        private const val DB_VERSION = 1  // Версия базы данных

        private const val TABLE_NAME = "Hadiths"
        private const val COLUMN_ID = "id"
        private const val COLUMN_BOOK_NAME = "book_name"
        private const val COLUMN_HADITH_NUMBER = "hadith_number"
        private const val COLUMN_TEXT = "text"
        private const val COLUMN_NARRATOR = "narrator"
    }

    // Создание таблицы
    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_BOOK_NAME VARCHAR(255) NOT NULL,
                $COLUMN_HADITH_NUMBER INT NOT NULL,
                $COLUMN_TEXT TEXT NOT NULL,
                $COLUMN_NARRATOR TEXT NOT NULL
            )
        """
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Метод для получения случайного хадиса
    fun getRandomHadith(): String {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT $COLUMN_TEXT FROM $TABLE_NAME ORDER BY RANDOM() LIMIT 1", null)

        return if (cursor.moveToFirst()) {
            // Получаем индекс столбца и проверяем его
            val columnIndex = cursor.getColumnIndex(COLUMN_TEXT)
            if (columnIndex != -1) {
                cursor.getString(columnIndex)
            } else {
                "Не найден столбец с текстом хадиса"
            }
        } else {
            "Нет хадисов в базе данных"
        }.also {
            cursor.close()
        }
    }
}
