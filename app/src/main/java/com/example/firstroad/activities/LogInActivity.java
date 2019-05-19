package com.example.firstroad.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstroad.R;
import com.example.firstroad.classes.User;
import com.example.firstroad.utils.MyToast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout mAccountLayout;
    private EditText mAccountEdit;

    private TextInputLayout mPasswordLayout;
    private TextInputEditText mPassWordEdit;

    private Button login;
    private TextView logUp;

    private Button cancel;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        iniViews();
        iniAccout();
        iniPassWord();
        iniUIDesign();
    }

    // 绑定控件
    private void iniViews(){
        mAccountLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        mAccountEdit = (EditText) findViewById(R.id.editText);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.textInputLayout2);
        mPassWordEdit = (TextInputEditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.log_in);
        cancel = (Button) findViewById(R.id.cancel_login);
        title = (TextView) findViewById(R.id.title);
        logUp = (TextView) findViewById(R.id.log_up);

        login.setOnClickListener(this);
        cancel.setOnClickListener(this);
        logUp.setOnClickListener(this);
    }

    // 输入账户
    private void iniAccout(){
        mAccountLayout.setHint("请输入手机号");
        mAccountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    mAccountLayout.setError("手机号输入错误");
                    mAccountLayout.setErrorEnabled(true);
                } else {
                    mAccountLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // 输入密码
    private void iniPassWord(){
        mPasswordLayout.setHint("请输入密码");
        mPassWordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 9) {
                    mPasswordLayout.setError("输入密码不合法");
                    mPasswordLayout.setErrorEnabled(true);
                } else {
                    mPasswordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void iniUIDesign(){
        cancel.setText("取消");
        cancel.setTextColor(getResources().getColor(R.color.white));

        title.setText("登录");

        login.setText("开启壹路");
        login.setBackgroundResource(R.color.colorPrimary);
        login.setTextColor(getResources().getColor(R.color.white));

        logUp.setText("快速组册");
    }

    // 活动跳转
    public static void acrionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, LogInActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_in:
                logIn();
                break;

            case R.id.cancel_login:
                finish();
                break;

            case R.id.log_up:
                LogUpActivity.actionStart(this);
                break;

            default:

                break;
        }
    }

    private void logIn(){
        User user = new User();
        user.setUsername(mAccountEdit.getText().toString());
        user.setPassword(mPassWordEdit.getText().toString());
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if(e==null){
                    MyToast.MyToast(LogInActivity.this, "登录成功");
                    finish();
                }else{
                    MyToast.MyToast(LogInActivity.this, "失败 请检查网络");
                }
            }
        });
    }
}
