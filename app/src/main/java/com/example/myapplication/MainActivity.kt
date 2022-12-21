package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView１ = findViewById<TextView>(R.id.textView１)
        val btn_typeone = findViewById<Button>(R.id.btn_typeone)
        val btn_typetwo = findViewById<Button>(R.id.btn_typetwo)

        btn_typeone.setOnClickListener(){
            startActivity(Intent(this,MainActivity2::class.java))
        }
        btn_typetwo.setOnClickListener(){
            startActivity(Intent(this,MainActivity7::class.java))
        }
    }
}