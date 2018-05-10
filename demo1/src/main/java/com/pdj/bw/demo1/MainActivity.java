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

public class MainActivity extends AppCompatActivity {
    private EditText phone;
    private EditText pwd;
    private Button login;
    private Button select;
    private String pwdstr="http://120.27.23.105/user/reg?mobile="+phone+"&password="+pwd;
    private String loginstr="http://120.27.23.105/user/login?mobile="+phone+"&password="+pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwdText = pwd.getText().toString();
                String  phoneText = phone.getText().toString();
                pwdstr="http://120.27.23.105/user/reg?mobile="+phoneText+"&password="+pwdText;
                loginstr="http://120.27.23.105/user/login?mobile="+phoneText+"&password="+pwdText;
                if(pwdText ==null){
                    Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    new MyAsyncTask().execute(loginstr);
                }
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });

    }
    class MyAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String s = new NetJson().getNetJson(strings[0]);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            JsonData data = gson.fromJson(s, JsonData.class);
            String code = data.getCode();
            String msg = data.getMsg();
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            if(code.equals("0")){
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        }
    }
    private void initView() {
        phone = (EditText) findViewById(R.id.phone);
        pwd = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);
        select = (Button) findViewById(R.id.select);
    }
}
