package com.example.apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CoronaCheckActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioGroup ondoGroup;
    private RadioGroup resultGroup;
    private RadioGroup isolGroup;
    private RadioGroup privGroup;
    private Boolean ondo = null;
    private Boolean result = null;
    private Boolean isol = null;
    private Boolean priv = null;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_check);

        final RadioButton ondoyes = findViewById(R.id.ondoyesbutton);
        final RadioButton resultyes = findViewById(R.id.resultyesbutton);
        final RadioButton isolyes = findViewById(R.id.isolyesbutton);
        final RadioButton privyes = findViewById(R.id.privyesbutton);
        final String[] ans = new String[4];
        //라디오 그룹 선언

        ondoGroup = (RadioGroup) findViewById(R.id.ondoradio);
        resultGroup = (RadioGroup) findViewById(R.id.resultradio);
        isolGroup = (RadioGroup) findViewById(R.id.isolradio);
        privGroup = (RadioGroup) findViewById(R.id.privradio);
        ondoGroup.setOnCheckedChangeListener(radioGroupButtonListener);
        resultGroup.setOnCheckedChangeListener(radioGroupButtonListener);
        isolGroup.setOnCheckedChangeListener(radioGroupButtonListener);
        privGroup.setOnCheckedChangeListener(radioGroupButtonListener);


                //제출버튼 누를때 일어나는 곳
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                ans[0] = ondo+"+"+result+"+"+isol+"+"+priv+"||";
                if(ondo == null || result == null || isol == null || priv == null){
                    Toast.makeText(getApplicationContext(), ans[0], Toast.LENGTH_LONG).show();
                    return;
                }
                else if(ondo == false || result == false || isol == false || priv == false){
                    Toast.makeText(getApplicationContext(), "어이 친구 보내주기 어렵겟는데?", Toast.LENGTH_LONG).show();
                    return;
                }
                intent.putExtra("ondo", ondo);
                intent.putExtra("result", result);
                intent.putExtra("isol", isol);
                startActivity(intent);
                finish();
            }

        };
        Button sendBtn = (Button) findViewById(R.id.Sendbtn);
        sendBtn.setOnClickListener(listener);
    }



    RadioGroup.OnCheckedChangeListener radioGroupButtonListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.ondoyesbutton) {
                //Toast.makeText(CoronaCheckActivity.this, "True입니다.", Toast.LENGTH_SHORT).show();
                ondo = true;
            } else if (checkedId == R.id.ondonobutton) {
                //Toast.makeText(CoronaCheckActivity.this, "False입니다.", Toast.LENGTH_SHORT).show();
                ondo = false;
            }

            if (checkedId == R.id.resultyesbutton) {
                result = true;
            } else if (checkedId == R.id.resultnobutton) {
                result = false;
            }
            if(checkedId== R.id.isolyesbutton){
                isol = true;
            }
            else if(checkedId== R.id.isolnobutton){
                isol = false;
            }

            if(checkedId== R.id.privyesbutton){
                priv = true;
            }
        }

    };

}
