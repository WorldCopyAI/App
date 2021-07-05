package com.example.tests

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {
    private val backHandler = BackButton(this)
    private var changeView: Boolean = false
    private lateinit var tvResult: TextView // 닉네임 텍스트
    private lateinit var ivProfile: ImageView //프로필 이미지 뷰


    //nfc
    private lateinit var nfcButton : Button
    lateinit var nfcAdapter: NfcAdapter
    private lateinit var txNfc : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nfcButton = findViewById(R.id.btnNFC)
        txNfc =  findViewById(R.id.txNFC)

        // 해당 단말기의 nfc정보를 가져오기
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        // NFC 여부 확인
        if(nfcAdapter == null){
            Toast.makeText(this,"NFC가 없습니다.",Toast.LENGTH_LONG).show()
            finish()
        }

        // 버튼 클릭시 이미지 변경
        btn1.setOnClickListener {
            viewChange()
        }

        nfcButton.setOnClickListener{
            txNfc.text = nfcAdapter.toString()
        }


        val intent: Intent = Intent()
        val nickname: String = intent.getStringExtra("nickName") ?: "" // 첫번째 창에서 닉네임을 전달받음
        val profile: String = intent.getStringExtra("profile") ?: "" // 첫번째 창에서 프로필 사진 Url을 전달받음

        tvResult = findViewById(R.id.result_name)
        tvResult.text = nickname                             // 닉네임 text를 텍스트 뷰로 세팅

        ivProfile = findViewById(R.id.iv_profile)
        Glide.with(this).load(profile).into(iv_profile) // 프로필 url을 이미지뷰에 세팅한 것

    }

    override fun onBackPressed() {
        backHandler.onBackPressed()
    }

    // 이미지 변경 함수화 - 수정시 매개변수 추가해주세요
    fun viewChange() {
        if (!changeView) {
            imageView2.visibility = View.INVISIBLE
            imageView3.visibility = View.VISIBLE
            changeView = true
        } else {
            imageView2.visibility = View.VISIBLE
            imageView3.visibility = View.INVISIBLE
            changeView = false
        }
    }


}
