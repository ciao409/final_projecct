package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import com.google.gson.Gson
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.adapter.MemberAdapter
import com.example.myapplication.beam.MemberBean
import java.lang.Exception
import java.util.*

class MainActivity8 : AppCompatActivity() {
    private lateinit var lv: ListView
    private val data = mutableListOf<MemberBean>()
    private lateinit var billOpenHelper: BillOpenHelper
    private lateinit var activity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)
        activity = this;
        // 初始化數據庫工具類
        billOpenHelper = BillOpenHelper(this, BillOpenHelper.dbName, null, 1)
        initView()
    }

    private fun initView() {
        // 設置返回鍵
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        // 成員列表
        lv = findViewById(R.id.lv)

        val etName: EditText = findViewById(R.id.ed_name)
        val etPart: EditText = findViewById(R.id.ed_part)
        val etMoney: EditText = findViewById(R.id.ed_money)
        val etRemake: EditText = findViewById(R.id.ed_travelName)
        val tvTotal: TextView = findViewById(R.id.tv_total)
        tvTotal.text = "總額：0"

        // 新增按鈕
        findViewById<Button>(R.id.btn_add).setOnClickListener {
            val name = etName.text.toString()
            val part = etPart.text.toString()
            val money = etMoney.text.toString()

            if (name.isNullOrEmpty() || part.isNullOrEmpty() || money.isNullOrEmpty()) {
                Toast.makeText(this, "請完善所有信息", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            try {
                var isAlive = false
                // 判斷輸入的用戶是否有過支付
                for (bean in data) {
                    if (bean.name == name) {
                        isAlive = true
                        bean.money = (bean.money.toBigDecimal() + money.toFloat().toBigDecimal()).toFloat()
                        bean.part = bean.part + "-" + part;
                        break
                    }
                }
                if (!isAlive) {
                    data.add(0, MemberBean(name, part, money.toFloat()))
                }
            } catch (e: Exception) {
                Toast.makeText(this, "輸入類型錯誤 error${e}", Toast.LENGTH_LONG).show()
            }

            var total = 0f
            for (bean in data) {
                total = (total.toBigDecimal() + bean.money.toBigDecimal()).toFloat()
            }
            val payMoney = total.toBigDecimal().div(data.size.toBigDecimal()).toFloat()
            tvTotal.text = "總額：${total} 每人應支付${payMoney}"
            val adapter = MemberAdapter(data, this)
            adapter.setMoney(payMoney)
            lv.adapter = adapter

            etName.setText("")
            etPart.setText("")
            etMoney.setText("")
        }

        // 保存按鈕
        findViewById<Button>(R.id.btn_save).setOnClickListener {
            val remake = etRemake.text.toString()
            if (remake.isNullOrEmpty() || data.isEmpty()) {
                Toast.makeText(this, "請完善備註/新增成員", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val dialog = AlertDialog.Builder(this).apply {
                setTitle("提示")
                setMessage("保存後會關閉此頁面")
                setPositiveButton("確定"
                ) { dialog, which ->
                    if (billOpenHelper.add(remake, Gson().toJson(data), Date().time) != -1L) {
                        Toast.makeText(activity, "保存成功", Toast.LENGTH_LONG).show()
                        activity.finish()
                    } else {
                        Toast.makeText(activity, "保存失敗", Toast.LENGTH_LONG).show()
                    }
                }

                setNeutralButton("取消"
                ) { dialog, which ->

                }
            }.create()
            dialog.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}