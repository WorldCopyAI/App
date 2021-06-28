package com.example.tests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private val backHandler = BackButton(this)
    private lateinit var btnGoogle: SignInButton                  // 구글 로그인용 버튼
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()   // 파이어베이스 인증용 객체
    private var googleApiClient: GoogleApiClient? = null          // 구글 API 클라이언트 객체

    companion object {
        const val REQ_SING_GOOGLE: Int = 100 // 구글 로그인 결과 코드
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginBtn.setOnClickListener {
            val intent = Intent(this, CoronaCheckActivity::class.java)
            startActivity(intent)
            finish()
        }

        // btn_google 사용시 옵션세팅을 해주는 것 버튼클릭시에도 변하지 않는것으로 봐서는 이곳이 문제인것 같은데 도대체 뭐가 문제일지 아직은 잘모르겠음
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.firebase_web_client_id))
            .requestEmail()
            .build()

        var googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        btnGoogle = findViewById(R.id.btn_google)
        btnGoogle.setOnClickListener {

            var signinIntent:Intent = googleSignInClient.signInIntent
            //intent.putExtra()
            startActivityForResult(signinIntent, REQ_SING_GOOGLE)

            startActivity(intent)
            val intent = Intent(this, MainActivity::class.java)
        }

    }

    override fun onBackPressed() {
        backHandler.onBackPressed()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }



    private fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

}


