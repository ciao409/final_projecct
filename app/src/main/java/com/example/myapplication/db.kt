package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLiteOpenHelper (context: Context): SQLiteOpenHelper(context, name, null, version) {
    companion object {
        private const val name = "database.db"
        private const val version = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Travel(id text PRIMARY KEY, itemName text NOT NULL , pay text NOT NULL )")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Travel")
        onCreate(db)
    }
}