package com.example.aop_part2_chapter03

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openBtn.setOnClickListener{

            if (changePasswordMode){
                Toast.makeText(this, "비밀번호 변경 중 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser = "${numberPicker1.value}${numberPicker3.value}${numberPicker3.value}"

            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
//                패스워드 성공

//    TODO 다이어리 페이지 작성 후에 넘겨주어야함
//                startActivity()
                }else{
//                    패스워드 실패

                    AlertDialog.Builder(this)
                        .setTitle("오류")
                        .setMessage("비밀번호가 잘못되었습니다.")
                        .setPositiveButton("확인") { _, _ -> }
                        .show()
            }

            changePasswordBtn.setOnClickListener{

                if(changePasswordMode){
//                    번호를 저장하는 기능

                }else{
//                    changePasswordMode가 활성화 -> 비밀번호가 맞는지 확인

                    val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

                    val passwordFromUser = "${numberPicker1.value}${numberPicker3.value}${numberPicker3.value}"

                    if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {

                        changePasswordMode = true

                        Toast.makeText(this, "변경할 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()

                        changePasswordBtn.setBackgroundColor(Color.RED)
                    }else{
//                    패스워드 실패

                        AlertDialog.Builder(this)
                            .setTitle("오류")
                            .setMessage("비밀번호가 잘못되었습니다.")
                            .setPositiveButton("확인") { _, _ -> }
                            .show()
                    }
                }
            }
        }
    }
}