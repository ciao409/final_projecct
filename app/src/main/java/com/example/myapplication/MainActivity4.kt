package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.*
import android.annotation.SuppressLint
import android.app.Activity
import android.database.sqlite.SQLiteDatabase



class MainActivity4 : AppCompatActivity() {
    private lateinit var dbrw: SQLiteDatabase

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val ed_itemName = findViewById<EditText>(R.id.ed_itemName)
        val ed_pay = findViewById<EditText>(R.id.ed_pay)
        val ed_id = findViewById<TextView>(R.id.ed_id)
        val textView7 = findViewById<TextView>(R.id.textView7)
        val btn_delete = findViewById<Button>(R.id.btn_delete)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView4 = findViewById<TextView>(R.id.textView4)
        val textView5 = findViewById<TextView>(R.id.textView5)
        val btn_commit2 = findViewById<Button>(R.id.btn_commit2)
        val btn_total = findViewById<Button>(R.id.btn_total)
        val btn_query = findViewById<Button>(R.id.btn_query)
        val listView = findViewById<ListView>(R.id.listView)
        var people = intent.getStringExtra("people")
        var money = intent.getStringExtra("money")
        var peoplenum = Integer.parseInt(people)
        var moneynum = Integer.parseInt(money)
        var total = peoplenum*moneynum
        var paynum=0
        textView4.text = "可用金額：${total } "



        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
        //取得資料庫實體
        dbrw = MySQLiteOpenHelper(this).writableDatabase


        btn_query.setOnClickListener {
            val c = dbrw.rawQuery(
                if (ed_pay.length()<1) "SELECT * FROM Travel"
                else "SELECT * FROM Travel WHERE id LIKE '${ed_id.text}'", null)
            //從第一筆開始輸出
            c.moveToFirst()
            //清空舊資料
            items.clear()
            showToast("共有${c.count}筆資料")
            for (i in 0 until c.count) {
                items.add("編號:${c.getString(0)}\n項目:${c.getString(1)}\n金額:${c.getString(2)}")
                //移動到下一張
                c.moveToNext()
            }
            //更新列表資料
            adapter.notifyDataSetChanged()
            //關閉Cursor
            c.close()
        }



        var overnum=0
        var num=0

        btn_commit2.setOnClickListener {
            if (ed_id.length()<1 )
                showToast("請輸入必要欄位")
            else
                try {
                    dbrw.execSQL("INSERT INTO Travel(id, itemName, pay) VALUES(?,?,?)",
                        arrayOf<Any?>(ed_id.text.toString(), ed_itemName.text.toString(), ed_pay.text.toString()))
                    paynum = Integer.parseInt(ed_pay.text.toString())
                    overnum = (total-paynum)
                    total = total-paynum
                    paynum=num+paynum
                    num = paynum
                    textView5.text = "剩餘金額: ${overnum}"
                    showToast("編號${ed_id.text} ")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("新增失敗:$e")
                }

        }


        btn_total.setOnClickListener() {
            if (ed_pay.length() != 0)
                showToast("請輸入")
            else
                try {
                    val j = Intent(this, MainActivity5::class.java)
                    j.putExtra("overnum", money.toString())
                    j.putExtra("people", people.toString())
                    j.putExtra("paynum",paynum.toString())
                    startActivity(j)
                }catch (e:Exception){
                    showToast("跳轉失敗:$e")
                }
        }



        btn_delete.setOnClickListener {
            if (ed_id.length()<1)
                showToast("請輸入編號")
            else
                try {
                    dbrw.execSQL("DELETE FROM Travel WHERE id LIKE '${ed_id.text}'")
                    showToast("刪除編號${ed_id.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }
        }


    }
    private fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    private fun cleanEditText() {
        val ed_pay = findViewById<EditText>(R.id.ed_pay)
        val ed_itemName = findViewById<EditText>(R.id.ed_itemName)
        val ed_id = findViewById<EditText>(R.id.ed_id)

        ed_pay.setText("")
        ed_itemName.setText("")
        ed_id.setText("")

    }

}





