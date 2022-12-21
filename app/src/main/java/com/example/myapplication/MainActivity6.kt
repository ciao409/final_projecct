package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity6 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        var ed_max = findViewById<EditText>(R.id.ed_max)
        val btn_start = findViewById<Button>(R.id.btn_start)
        val tv_num = findViewById<TextView>(R.id.tv_num)

        btn_start.setOnClickListener {
            var max = ed_max.text.toString().toInt()
            var num = (Math.random()*max).toInt()+1
            tv_num.setText(num.toString())
        }
    }
}