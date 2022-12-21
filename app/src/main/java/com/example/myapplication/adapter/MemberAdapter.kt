package com.example.myapplication.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.beam.MemberBean
import kotlin.math.absoluteValue

/**
 *
 * 成員適配器
 * */

class MemberAdapter (private val data: MutableList<MemberBean>, private val context: Context) :
    BaseAdapter() {

    private var money = 0f

    fun setMoney(money: Float) {
        this.money = money
    }

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
            v = View.inflate(context, R.layout.layout_member, null)
            holder = MyViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as MyViewHolder
        }

        val bean = data[position]
        holder.name.text = bean.name
        holder.part.text = bean.part
        holder.money.text = "${bean.money}"
        holder.getMoney.text =
            getMoneyText((bean.money.toBigDecimal() - this.money.toBigDecimal()).toFloat())
        return v

    }

    private fun getMoneyText(money: Float): String {
        return when {
            money > 0 ->  "拿回${money}"
            money == 0f ->   "-"
            else -> "多付${money.absoluteValue}"
        }
    }

    class MyViewHolder(viewItem: View) {
        // 成員姓名
        var name: TextView = viewItem.findViewById(R.id.tv_name)

        // 所附項目
        var part: TextView = viewItem.findViewById(R.id.tv_part)

        // 所附金額
        var money: TextView = viewItem.findViewById(R.id.tv_money)

        // 應付或者得到的錢
        var getMoney: TextView = viewItem.findViewById(R.id.tv_getMoney)
    }

}