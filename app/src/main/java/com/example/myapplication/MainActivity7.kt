package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.myapplication.adapter.BillAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity7 : AppCompatActivity() {

    private lateinit var billOpenHelper: BillOpenHelper
    private lateinit var lv: ListView
    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        // 初始化數據庫工具類
        billOpenHelper = BillOpenHelper(this, BillOpenHelper.dbName, null, 1)
        initView()
    }


    /**
     *
     * 初始化佈局
     * */
    private fun initView() {
        // 數據列表
        lv = findViewById(R.id.lv)
        // 搜索輸入框
        etSearch = findViewById(R.id.ed_search)

        // 搜索按鈕
        findViewById<Button>(R.id.btn_search).setOnClickListener() {
            setData()
        }

        // 新增按鈕
        findViewById<Button>(R.id.btn_in).setOnClickListener() {
            startActivity(Intent(this, MainActivity8::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        val key = etSearch.text.toString()
        val data = billOpenHelper.queryDataByRemake(key)
        // 數據按照入庫時間降序
        data.sortByDescending { billBean -> billBean.time }
        val adapter = BillAdapter(data, this)
        lv.adapter = adapter
    }
}