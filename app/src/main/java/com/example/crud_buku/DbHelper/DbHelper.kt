package com.example.crud_buku.DbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.crud_buku.model.ModelBuku

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "
                + JUDUL + " TEXT, "
                + PENGARANG + " TEXT, "
                + GENRE + " TEXT" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertDataBuku(dataBuku: ModelBuku): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(JUDUL, dataBuku.judul)
        values.put(PENGARANG, dataBuku.pengarang)
        values.put(GENRE, dataBuku.genre)
        return db.insert(TABLE_NAME, null, values)  // Use TABLE_NAME constant
    }

    fun getBukuById(bukuId: Int): ModelBuku {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $ID_COL = $bukuId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToNext()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))
        val judul = cursor.getString(cursor.getColumnIndexOrThrow(JUDUL))
        val pengarang = cursor.getString(cursor.getColumnIndexOrThrow(PENGARANG))
        val genre = cursor.getString(cursor.getColumnIndexOrThrow(GENRE))

        cursor.close()
        db.close()
        return ModelBuku(id, judul, pengarang, genre)
    }

    fun updateBuku(buku: ModelBuku) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(JUDUL, buku.judul)
            put(PENGARANG, buku.pengarang)
            put(GENRE, buku.genre)
        }
        val whereClause = "$ID_COL = ?"
        val whereArgs = arrayOf(buku.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun deleteBuku(bukuId: Int) {
        val db = writableDatabase
        val whereClause = "$ID_COL = ?"
        val whereArgs = arrayOf(bukuId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }

    fun getAllDataBuku(): List<ModelBuku> {
        val bukuList = mutableListOf<ModelBuku>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))
            val judul = cursor.getString(cursor.getColumnIndexOrThrow(JUDUL))
            val pengarang = cursor.getString(cursor.getColumnIndexOrThrow(PENGARANG))
            val genre = cursor.getString(cursor.getColumnIndexOrThrow(GENRE))
            val buku = ModelBuku(id, judul, pengarang, genre)
            bukuList.add(buku)
        }
        cursor.close()
        db.close()
        return bukuList
    }

    companion object {
        private const val DATABASE_NAME = "DB_BUKU"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "tb_buku"  // Ensure the table name matches
        const val ID_COL = "id"
        const val JUDUL = "judul"
        const val PENGARANG = "pengarang"
        const val GENRE = "genre"
    }
}
