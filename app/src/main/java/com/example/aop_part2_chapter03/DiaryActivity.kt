package com.example.aop_part2_chapter03

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class DiaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialy)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)
        val diaryEdt = findViewById<EditText>(R.id.diaryEdt)

        diaryEdt.setText(detailPreferences.getString("detail",""))
    }
}