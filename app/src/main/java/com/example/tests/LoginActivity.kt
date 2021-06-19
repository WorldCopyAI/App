package com.example.tests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val backHandler = BackButton(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginBtn.setOnClickListener {
            val intent = Intent(this, CoronaCheckActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        backHandler.onBackPressed()
    }

}
