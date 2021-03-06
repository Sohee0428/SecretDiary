package com.example.aop_part2_chapter03

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openBtn: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openBtn)
    }

    private val changePasswordBtn: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordBtn)
    }

    private var changePasswordMode = false

    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("오류")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인") { _, _ -> }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openBtn.setOnClickListener {

            if (changePasswordMode) {
                Toast.makeText(this, "비밀번호 변경 중 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
//                패스워드 성공
                startActivity(Intent(this, DiaryActivity::class.java))

            } else {
//                    패스워드 실패

                showErrorAlertDialog()
            }

        }
        changePasswordBtn.setOnClickListener {

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser =
                "${numberPicker1.value}${numberPicker3.value}${numberPicker3.value}"

            if (changePasswordMode) {

//                    val editor = passwordPreferences.edit()
//                    editor.putString()
//                    기존에는 이렇게 작성해야하지만 코틀린은 아래처럼 작성하면 됨

                passwordPreferences.edit(true) {
                    putString("password", passwordFromUser)
                }

                changePasswordMode = false
                changePasswordBtn.setBackgroundColor(Color.BLACK)

            } else {
//                    changePasswordMode가 활성화 -> 비밀번호가 맞는지 확인

                if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {

                    changePasswordMode = true

                    Toast.makeText(this, "변경할 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()

                    changePasswordBtn.setBackgroundColor(Color.RED)
                } else {
//                    패스워드 실패

                    showErrorAlertDialog()

                }
            }
        }
    }
}