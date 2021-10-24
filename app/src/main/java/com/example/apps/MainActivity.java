package com.example.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnChange;
    Button btnNFC;
    ImageView imageOK;
    ImageView imageNO;
    TextView txtNFC;  // NFC 텍스트 뷰 변수
    Boolean changeView = false;
    
    private NfcAdapter nfcAdapter; // nfcAdapter 변수 선언
    private NdefMessage mNdeMessage; // nfc전송 메시지

    private String userid, userpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChange = (Button)findViewById(R.id.btn1);
        btnNFC = (Button)findViewById(R.id.btnNFC);
        imageNO = (ImageView)findViewById(R.id.imageView2);
        imageOK = (ImageView)findViewById(R.id.imageView3);
        txtNFC = (TextView)findViewById(R.id.txNFC);

        //nfc 지원여부에 따라 null반환
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // mNdeMessage 변수에 NFC 단말기에 보낼 정보를 넣는다.
        if(nfcAdapter != null)
        {
            txtNFC.setText("NFC 단말기를 접촉해주세요\n"+nfcAdapter+"");
        }
        else
        {
            txtNFC.setText("NFC 기능이 꺼져있습니다. 켜주세요\n"+nfcAdapter+"");
        }
        
        
        // 버튼 클릭시 이미지 변경
        // git Test
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!changeView){
                    imageNO.setVisibility(View.INVISIBLE);
                    imageOK.setVisibility(View.VISIBLE);
                    changeView = true;
                }else{
                    imageNO.setVisibility(View.VISIBLE);
                    imageOK.setVisibility(View.INVISIBLE);
                    changeView = false;
                }
            }
        });

        btnNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNFC.setText("NFC 단말기를 접촉해주세요\n"+nfcAdapter+"");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundNdefPush(this, mNdeMessage);
        }
    }

    //액티비티 화면이 종료되면 NFC 데이터 전송을 중단하기 위해 실행되는 메소드.
    @Override
    protected void onPause() {
        super.onPause();
        if(nfcAdapter != null) {
            nfcAdapter.disableForegroundNdefPush(this);
        }
    }
}