package com.example.logininterface;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logininterface.uitl.ViewUtil;
import com.example.logininterface.xiaochuan.xiaochuan;
import com.example.logininterface.xiaochuan.xioachuan2;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private TextView tv_input_passwordnumber;
    private Button bt_intent_forgetpassword;
    private EditText et_input_password;
    private EditText et_input_phone;
    private RadioButton rg_text_valadition;
    private RadioButton rg_text_password;

    private String mPassword = "111111";
    private String mVerifyCode;

    private ActivityResultLauncher<Intent> register;
    private CheckBox remeber;
    private SharedPreferences pref;           // 读取数据
    private SharedPreferences.Editor editor;  // 写入数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  单选按钮的设定
        RadioGroup rg_group = findViewById(R.id.rg_grpup);
        rg_text_password = findViewById(R.id.rg_text_password);
        rg_text_valadition = findViewById(R.id.rg_text_valadition);


        // 涉及到的组件
        tv_input_passwordnumber = findViewById(R.id.input_passwordnumber);  // 登录密码
        et_input_phone = findViewById(R.id.et_input_phone);                 // 请输入手机号码:
        et_input_password = findViewById(R.id.et_input_password);           // 请输入密码:
        bt_intent_forgetpassword = findViewById(R.id.intent_forgetpassword);// 忘记密码 ， 获取验证码


        // 给单选按钮设置事件监听
        // 因为此按钮为持续选中按钮，所以要使用 setOnCheckedChangeLinstener()
        rg_group.setOnCheckedChangeListener(this);


        // 给et_input_phone 和 et_input_password
        et_input_phone.addTextChangedListener(new HideTextWatch(et_input_phone, 11));
        et_input_password.addTextChangedListener(new HideTextWatch(et_input_password, 6));


        // 设置 登录 和 忘记密码 按钮的点击事件
        findViewById(R.id.intent_forgetpassword).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.xiochuan).setOnClickListener(this);


        //  设置记住密码
        load();



        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                if (intent != null && result.getResultCode() == Activity.RESULT_OK) {
                    // 用户密码已改为新密码，故更新密码变量
                    mPassword = intent.getStringExtra("new_password");
                }
            }
        });

    }


    // 实现记住密码
    private void load() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        remeber = findViewById(R.id.remeber);
        boolean isRemeber = pref.getBoolean("remeber_password", false);
        if (isRemeber) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            et_input_phone.setText(account);
            et_input_password.setText(password);
            remeber.setChecked(true);
        }
    }

    // 实现单选按钮的监听事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rg_text_password:
                tv_input_passwordnumber.setText("登录密码:");
                et_input_password.setHint("请输入密码:");
                bt_intent_forgetpassword.setText("忘记密码");
                break;
            case R.id.rg_text_valadition:
                tv_input_passwordnumber.setText("验证码:");
                et_input_password.setHint("请输入验证码:");
                bt_intent_forgetpassword.setText("获取验证码");
                break;
        }
    }

    @Override
    public void onClick(View v) {

        // 先判断输入的数字是否合法
        String phone = et_input_phone.getText().toString();
        if (phone.length() < 11) {
            Toast.makeText(this, "你输入的电话长度有误，请你检查一下", Toast.LENGTH_SHORT).show();
            return;
        }


        switch (v.getId()) {
            case R.id.intent_forgetpassword:

                // 两种情况  一种是忘记密码  另一种获取验证码
                if (rg_text_password.isChecked()) {
                    Intent intent = new Intent(this,MainActivityForget.class);
                    intent.putExtra("phone",phone);
                    register.launch(intent);
                } else if (rg_text_valadition.isChecked()){
                    // 随机生成六位验证码
                    mVerifyCode = String.format("%06d",new Random().nextInt(999999));

                    // 弹出对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("请记住验证码");
                    builder.setMessage("手机号" + phone + ",本次验证码是" + mVerifyCode + ",请输入验证码");
                    builder.setPositiveButton("好的",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;

            case R.id.login:
                if (rg_text_password.isChecked()) {
                    if (!mPassword.equals(et_input_password.getText().toString())) {

                        // 记住密码相关
                        editor = pref.edit();
                        if (remeber.isChecked()) {
                            editor.putBoolean("remeber_password",true);
                            editor.putString("account",phone);
                            editor.putString("password",et_input_password.getText().toString());
                        } else {
                            editor.clear();
                        }
                        editor.apply();


                        Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    loginSuccess();
                }else if (rg_text_valadition.isChecked()) {
                    // 验证码登录
                    if (!mVerifyCode.equals(et_input_password.getText().toString())) {
                        Toast.makeText(this,"验证码错误",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                loginSuccess();
                break;

            case R.id.xiochuan:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("小川专属");
                builder.setMessage("你是憨包吗？");
                builder.setPositiveButton("是",(dialog, which) -> {
//                    Intent  intent = new Intent(this, xiaochuan.class);
//                    register.launch(intent);

                    String phoneNumber = "13167888482";
                    Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                });
                builder.setNegativeButton("应该是",(dialog, which) -> {
//                    Intent  intent = new Intent(this, xioachuan2.class);
//                    register.launch(intent);

                    String phoneNumber = "13167888482";
                    Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                });
                AlertDialog dialog = builder.create();
                dialog.show();
        }
    }


    private void loginSuccess() {
        String desc = String.format("您的手机号码是%s，恭喜你通过登录验证，点击“确定”按钮返回上个页面",
                et_input_phone.getText().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定返回",(dialog, which) -> {
           // 结束界面
            finish();
            Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + et_input_phone.getText().toString()));//跳转到拨号界面，同时传递电话号码
            startActivity(dialIntent);
        });
        builder.setNegativeButton("我再看看",null);
        AlertDialog dialog = builder.create();
        dialog.show();


    }


    // 控制输入
    private class HideTextWatch implements TextWatcher {
        private EditText mView;
        private int mMaxLength;

        public HideTextWatch (EditText v,int maxLength){
            mView = v;
            mMaxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            // 隐藏软键盘
            if (s.toString().length() == mMaxLength) {
                ViewUtil.hideOneInputMethod(MainActivity.this,mView);
            }
        }
    }
}