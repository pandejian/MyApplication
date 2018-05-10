package com.pdj.bw.demo1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class SelectActivity extends AppCompatActivity {

    private EditText sPhone;
    private EditText sPwd;
    private Button zhuce;
    private String pwdstr;
    private String loginstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        initView();
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwdText = sPwd.getText().toString();
                String  phoneText = sPhone.getText().toString();
                pwdstr="http://120.27.23.105/user/reg?mobile="+phoneText+"&password="+pwdText;
                loginstr="http://120.27.23.105/user/login?mobile="+phoneText+"&password="+pwdText;
                new MyAsyncTask().execute(pwdstr);
            }
        });
    }
    class MyAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String s = new NetJson().getNetJson(strings[0]);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            PwdData data = gson.fromJson(s, PwdData.class);
            String code = data.getCode();
            String msg = data.getMsg();
            Toast.makeText(SelectActivity.this,msg,Toast.LENGTH_SHORT).show();
            if(code.equals("0")){
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
    private void initView() {
        sPhone = (EditText) findViewById(R.id.s_phone);
        sPwd = (EditText) findViewById(R.id.s_pwd);
        zhuce = (Button) findViewById(R.id.zhuce);
    }
}
