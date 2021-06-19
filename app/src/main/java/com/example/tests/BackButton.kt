package com.example.tests

import android.app.Activity
import android.widget.Toast

class BackButton(val activity: Activity) {
    private var backKeyClickTime: Long = 0

    fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyClickTime + BACK_DELAY) {
            backKeyClickTime = System.currentTimeMillis()
            Toast.makeText(activity, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }else{
            activity.finish()
        }
    }

    companion object{
        const val BACK_DELAY = 2000
    }
}