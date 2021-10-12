package com.example.tests

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val backHandler = BackButton(this)
    private var changeView:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 버튼 클릭시 이미지 변경
        btn1.setOnClickListener{
            if(!changeView){

                imageView2.visibility = View.INVISIBLE
                imageView3.visibility = View.VISIBLE
                changeView = true
            }
            else{
                imageView2.visibility = View.VISIBLE
                imageView3.visibility = View.INVISIBLE
                changeView = false
            }
        }
    }

    override fun onBackPressed() {
        backHandler.onBackPressed()
    }



}