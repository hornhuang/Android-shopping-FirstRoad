package com.example.firstroad.activities;

import android.content.Intent;
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

public class LogUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout mAccountLayout;
    private EditText mAccountEdit;

    private TextInputLayout mPasswordLayout;
    private TextInputEditText mPassWordEdit;

    private TextInputLayout mPasswordAgainLayout;
    private TextInputEditText mPassWordAgainEdit;

    private Button cancel;
    private TextView title;

    private TextView logUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        iniViews();
        iniAccout();
        iniPassWord();
        iniPassWordAgain();
        iniUIDesign();
    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, LogUpActivity.class);
        activity.startActivity(intent);
    }

    // 绑定控件
    private void iniViews(){
        mAccountLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        mAccountEdit = (EditText) findViewById(R.id.editText);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.textInputLayout2);
        mPassWordEdit = (TextInputEditText) findViewById(R.id.editText2);
        mPasswordAgainLayout = (TextInputLayout) findViewById(R.id.textInputLayout3);
        mPassWordAgainEdit = (TextInputEditText) findViewById(R.id.editText3);
        logUp = (TextView) findViewById(R.id.log_up);
        cancel = (Button) findViewById(R.id.cancel_logup);
        title = (TextView) findViewById(R.id.title);

        logUp.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void iniUIDesign(){
        cancel.setText("取消");
        cancel.setTextColor(getResources().getColor(R.color.white));

        title.setText("登录");

        logUp.setText("开启壹路");
        logUp.setBackgroundResource(R.color.colorPrimary);
        logUp.setTextColor(getResources().getColor(R.color.white));

        logUp.setText("快速组册");
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
                if (charSequence.length() > 11) {
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

    // 再次输入密码
    private void iniPassWordAgain(){
        mPasswordAgainLayout.setHint("请再次输入密码");
        mPassWordAgainEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 9) {
                    mPasswordAgainLayout.setError("输入密码不一致");
                    mPasswordAgainLayout.setErrorEnabled(true);
                } else {
                    mPasswordAgainLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_up:
                if (mPassWordEdit.getText().toString().equals(mPassWordAgainEdit.getText().toString())){
                    logUp();
                }else {
                    MyToast.MyToast(LogUpActivity.this, "请输入相同密码");
                }
                break;

            case R.id.cancel_logup:
                finish();
                break;

            default:

                break;
        }
    }

    private void logUp(){
        User user = new User();
        user.setUsername(mAccountEdit.getText().toString());
        user.setPassword(mPassWordAgainEdit.getText().toString());
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    MyToast.MyToast(LogUpActivity.this, "注册成功");
                    finish();
                }else{
                    MyToast.MyToast(LogUpActivity.this, e.getMessage()+"失败 请检查网络");
                }
            }
        });
    }
}
