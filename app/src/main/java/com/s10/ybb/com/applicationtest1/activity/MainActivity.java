package com.s10.ybb.com.applicationtest1.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.s10.ybb.com.applicationtest1.R;
import com.s10.ybb.com.applicationtest1.util.HttpCallbackListener;
import com.s10.ybb.com.applicationtest1.util.HttpUtil;
import com.s10.ybb.com.applicationtest1.util.JsonUtil;
import com.s10.ybb.com.applicationtest1.util.MyApplication;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private static final String APP1 = "App1";
    private EditText login_name; //登录名
    private EditText login_pwd;  //登录密码
    private Button login_btn;    //登录按钮

    public Handler header = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int loginType = JsonUtil.User_Login((String)msg.obj,login_pwd.getText().toString());
            switch (loginType){
                case 0:
                    Intent intent = new Intent(MainActivity.this,IndexActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    Toast.makeText(MyApplication.getContext(),"用户名不存在",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(MyApplication.getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(MyApplication.getContext(),"帐号已冻结",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = (Button)findViewById(R.id.bnLogin);
        login_name = (EditText)findViewById(R.id.userNameText);
        login_pwd = (EditText)findViewById(R.id.passwdText);
        login_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bnLogin:
                String username= login_name.getText().toString();
                final String password= login_pwd.getText().toString();
                Log.d(APP1,username);
                Log.d(APP1,password);
                if("".equals(username)) Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
                if("".equals(password)) Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                String address = "";
                String selectByUsername = getResources().getString(R.string.selectByUsername);
                address = selectByUsername;
                HttpCallbackListener listener = new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Message message = new Message();
                        message.what=1;
                        message.obj = response;
                        header.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("App1","erroer"+e.toString());
                        Log.d("App1","ThreadID3="+Thread.currentThread().getId());
                       // Toast.makeText(MyApplication.getContext(),"CUOWU",Toast.LENGTH_SHORT).show();
                    }
                };
                Log.d("App1","ThreadID1="+Thread.currentThread().getId());
                HttpUtil.sendHttpRequest(address,listener,"username="+username);
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
