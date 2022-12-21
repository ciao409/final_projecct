package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btn_commit = findViewById<Button>(R.id.btn_commit)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val radioButton1 = findViewById<RadioButton>(R.id.radioButton1)
        val radioButton2 = findViewById<RadioButton>(R.id.radioButton2)
        val radioButton3 = findViewById<RadioButton>(R.id.radioButton3)
        val radioButton4 = findViewById<RadioButton>(R.id.radioButton4)
        val radioGroup1 = findViewById<RadioGroup>(R.id.radioGroup1)
        val radioButton5 = findViewById<RadioButton>(R.id.radioButton5)
        val radioButton6 = findViewById<RadioButton>(R.id.radioButton6)
        val radioButton7 = findViewById<RadioButton>(R.id.radioButton7)

        btn_commit.setOnClickListener(){
           when {
               radioButton1.isChecked && radioButton5.isChecked ->{
                   Toast.makeText(this, "建議每人600元", Toast.LENGTH_SHORT).show()
               }
               radioButton1.isChecked && radioButton6.isChecked ->{
                   Toast.makeText(this, "建議每人3000元", Toast.LENGTH_SHORT).show()
               }
               radioButton1.isChecked && radioButton7.isChecked ->{
                   Toast.makeText(this, "建議每人5000元", Toast.LENGTH_SHORT).show()
               }
               radioButton2.isChecked && radioButton5.isChecked ->{
                   Toast.makeText(this, "建議每人600元", Toast.LENGTH_SHORT).show()
               }
               radioButton2.isChecked && radioButton6.isChecked ->{
                   Toast.makeText(this, "建議每人3000元", Toast.LENGTH_SHORT).show()
               }
               radioButton2.isChecked && radioButton7.isChecked ->{
                   Toast.makeText(this, "建議每人5000元", Toast.LENGTH_SHORT).show()
               }
               radioButton3.isChecked && radioButton5.isChecked ->{
                   Toast.makeText(this, "建議每人600元", Toast.LENGTH_SHORT).show()
               }
               radioButton3.isChecked && radioButton6.isChecked ->{
                   Toast.makeText(this, "建議每人3000元", Toast.LENGTH_SHORT).show()
               }
               radioButton3.isChecked && radioButton7.isChecked ->{
                   Toast.makeText(this, "建議每人5000元", Toast.LENGTH_SHORT).show()
               }
               radioButton4.isChecked && radioButton5.isChecked ->{
                   Toast.makeText(this, "建議每人600元", Toast.LENGTH_SHORT).show()
               }
               radioButton4.isChecked && radioButton6.isChecked ->{
                   Toast.makeText(this, "建議每人3000元", Toast.LENGTH_SHORT).show()
               }
               radioButton4.isChecked && radioButton7.isChecked ->{
                   Toast.makeText(this, "建議每人5000元", Toast.LENGTH_SHORT).show()
               }


           }
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}