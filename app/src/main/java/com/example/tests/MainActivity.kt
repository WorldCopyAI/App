package com.example.tests

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val backHandler = BackButton(this)
    private var changeView:Boolean = false
    private lateinit var tv_result:TextView // 닉네임 텍스트
    private lateinit var iv_profile:ImageView //프로필 이미지 뷰

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 버튼 클릭시 이미지 변경
        btn1.setOnClickListener{
            viewChange()
        }

        val intent : Intent = getIntent()
        val nickname : String? = intent.getStringExtra("nickName") // 첫번째 창에서 닉네임을 전달받음
        val profile : String? = intent.getStringExtra("profile") // 첫번째 창에서 프로필 사진 Url을 전달받음

        tv_result = findViewById(R.id.result_name)
        tv_result.setText(nickname)                             // 닉네임 text를 텍스트 뷰로 세팅

        iv_profile = findViewById(R.id.iv_profile)
        Glide.with(this).load(profile).into(iv_profile) // 프로필 url을 이미지뷰에 세팅한 것

    }

    override fun onBackPressed() {
        backHandler.onBackPressed()
    }

    // 이미지 변경 함수화 - 수정시 매개변수 추가해주세요
    fun viewChange(){
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