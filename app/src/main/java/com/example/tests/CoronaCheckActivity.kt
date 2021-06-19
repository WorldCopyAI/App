package com.example.tests

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_corona_check.*

class CoronaCheckActivity: AppCompatActivity() {
    private val backHandler = BackButton(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corona_check)

        Sendbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun checked(view: View) {
        println("HI")
    }

    override fun onBackPressed() {
        backHandler.onBackPressed()
    }

}