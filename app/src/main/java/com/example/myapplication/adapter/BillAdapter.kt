package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity9
import com.example.myapplication.R
import com.example.myapplication.beam.BillBean
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * 成員適配器
 * */
class BillAdapter(private val data: MutableList<BillBean>, private val context: Context) :
    BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v: View
        val holder: MyViewHolder
        if (convertView == null) {
            v = View.inflate(context, R.layout.layout_bill, null)
            holder = MyViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as MyViewHolder
        }

        val bean = data[position]
        holder.remake.text = bean.remake
        holder.time.text = "創建時間${longToTime(bean.time)}"
        holder.root.setOnClickListener {
            // activity 傳遞數據
            val intent = Intent(context, MainActivity9::class.java)
            intent.putExtra("id", bean.id.toString())
            (context as AppCompatActivity).startActivity(intent)
        }
        return v

    }

    private fun longToTime(time: Long): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date1 = Date(time)
        return simpleDateFormat.format(date1)
    }

    class MyViewHolder(viewItem: View) {
        // 備註
        var remake: TextView = viewItem.findViewById(R.id.tv_remake)

        // 創建時間
        var time: TextView = viewItem.findViewById(R.id.tv_time)

        // 父佈局
        var root: LinearLayout = viewItem.findViewById(R.id.item)

    }

}