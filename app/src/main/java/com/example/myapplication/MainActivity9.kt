package com.example.myapplication


import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.adapter.MemberAdapter
import com.example.myapplication.beam.MemberBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity9 : AppCompatActivity() {
    private lateinit var billOpenHelper: BillOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)
        //初始化數據庫工具類
        billOpenHelper = BillOpenHelper(this, BillOpenHelper.dbName, null, 1)
        initView()
        getData()
    }
    private fun initView(){
        //設置返回鍵
        if(supportActionBar!=null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getData(){
        //獲取帳單的ID 從數據庫找到此數據並展示
        val id =intent.getStringExtra("id")
        val data=id?.let{billOpenHelper.queryDataById(it)}?:return
        //解析json 數據
        val type=object : TypeToken<MutableList<MemberBean>>() {}.type
        val newList= Gson().fromJson<MutableList<MemberBean>>(data.data,type)

        var total=0f
        for(bean in newList){
            total=(total.toBigDecimal() + bean.money.toBigDecimal()).toFloat()
        }
        val payMoney=total.toBigDecimal().div(newList.size.toBigDecimal()).toFloat()

        val tvRemake: TextView = findViewById(R.id.tv_remake)
        val tvTotal: TextView = findViewById(R.id.tv_total)
        val lv: ListView = findViewById(R.id.lv)

        // 設置詳情數據
        tvRemake.text = data.remake
        tvTotal.text = "總額：${total} 每人應支付${payMoney}"

        val adapter = MemberAdapter(newList, this)
        adapter.setMoney(payMoney)
        lv.adapter = adapter
    }


}