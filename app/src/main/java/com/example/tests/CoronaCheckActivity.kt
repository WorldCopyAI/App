package com.example.tests

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_corona_check.*
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class CoronaCheckActivity : AppCompatActivity() {
    private val backHandler = BackButton(this)
    private var ondo: Boolean? = null
    private var result: Boolean? = null
    private var isol: Boolean? = null
    private var priv: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corona_check)

        ondoradio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.ondoyesbutton -> ondo = true
                R.id.ondonobutton -> ondo = false
            }
        }
        resultradio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.resultyesbutton -> result= true
                R.id.resultnobutton -> result = false
            }
        }
        isolradio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.isolyesbutton -> isol=true
                R.id.isolnobutton -> isol=false
            }
        }
        privradio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.privyesbutton -> priv = true
            }
        }

        Sendbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            if(ondo === null || result === null || isol === null || priv === null){
                Toast.makeText(getApplicationContext(), "모든 문항을 체크해주세요!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            intent.putExtra("ondo", ondo)
            intent.putExtra("result", result)
            intent.putExtra("isol", isol)
            startActivity(intent)
            finish()
        }
    }



    override fun onBackPressed() {
        backHandler.onBackPressed()
    }

}