package com.example.aop_part2_chapter03

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialy)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)
        val detailTitlePreferences = getSharedPreferences("title", Context.MODE_PRIVATE)
        val diaryEdt = findViewById<EditText>(R.id.diaryEdt)
        val diaryTitleEdt = findViewById<EditText>(R.id.diaryTitleEdt)

        diaryEdt.setText(detailPreferences.getString("detail", ""))
        diaryTitleEdt.setText(detailTitlePreferences.getString("titleName", ""))

        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("detail", diaryEdt.text.toString())
            }

            Log.d("DiaryActivity", "SAVE!! ${diaryEdt.text}")
        }

        val runnable1 = Runnable {

            getSharedPreferences("title", Context.MODE_PRIVATE).edit {
                putString("titleName", diaryTitleEdt.text.toString())
            }
        }

        diaryEdt.addTextChangedListener {

            Log.d("DiaryActivity", "TextChanged :: $it")

            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)

        }

        diaryTitleEdt.addTextChangedListener {

            handler.removeCallbacks(runnable1)
            handler.postDelayed(runnable1, 500)
        }
    }
}