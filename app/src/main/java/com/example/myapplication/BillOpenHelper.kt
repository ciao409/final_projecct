package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.beam.BillBean

/**
 *
 * 數據庫工具類
 * */
class BillOpenHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        val dbName = "Bill.db"
    }

    private val tableName = "tb_bill"


    override fun onCreate(db: SQLiteDatabase) {
        // 備註 內容 時間
        db.execSQL("create table $tableName(_id integer primary key autoincrement, remake String, data String, time Long)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    /**
     *
     * 根據備註搜索內容
     * */
    fun queryDataByRemake(key : String) : MutableList<BillBean> {
        val list = mutableListOf<BillBean>()
        val database = this.writableDatabase

        val cursor = database.query(
            tableName, null,
            "remake like '%${key}%'", null, null, null, null
        )
        while (cursor.moveToNext()) {
            @SuppressLint("Range") val id = cursor.getInt(cursor.getColumnIndex("_id"))
            @SuppressLint("Range") val remake = cursor.getString(cursor.getColumnIndex("remake"))
            @SuppressLint("Range") val data = cursor.getString(cursor.getColumnIndex("data"))
            @SuppressLint("Range") val time = cursor.getLong(cursor.getColumnIndex("time"))

            list.add(BillBean(id, remake, data, time))
        }

        cursor.close()
        return list
    }

    /**
     *
     * 根據ID搜索內容
     * */
    fun queryDataById(key : String) : BillBean? {
        var bean : BillBean? = null
        val database = this.writableDatabase

        val cursor = database.query(
            tableName, null,
            "_id like ?", arrayOf(key), null, null, null
        )
        while (cursor.moveToNext()) {
            @SuppressLint("Range") val id = cursor.getInt(cursor.getColumnIndex("_id"))
            @SuppressLint("Range") val remake = cursor.getString(cursor.getColumnIndex("remake"))
            @SuppressLint("Range") val data = cursor.getString(cursor.getColumnIndex("data"))
            @SuppressLint("Range") val time = cursor.getLong(cursor.getColumnIndex("time"))

            bean = BillBean(id, remake, data, time)
        }
        cursor.close()
        return bean
    }

    /**
     *
     * 新增數據
     * @return -1 代表未成功 其餘代表成功
     * */
    fun add(
        remake: String,
        data: String,
        time: Long,
    ): Long {
        val database = this.writableDatabase
        val values = ContentValues()
        values.put("remake", remake)
        values.put("data", data)
        values.put("time", time)
        return database.insert(tableName, null, values)
    }
}