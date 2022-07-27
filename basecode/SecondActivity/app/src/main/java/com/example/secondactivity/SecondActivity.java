package com.example.secondactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {     //创建活动
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startNormalActivity = findViewById(R.id.button_1);
        Button startDiglogActivity = findViewById(R.id.button_2);

        startNormalActivity.setOnClickListener(view -> {
            Intent intent =new Intent(this,normal_layout.class);
            startActivity(intent);
        });

        startDiglogActivity.setOnClickListener(view -> {
            Intent intent2 = new Intent(this,DialogActivity.class);
            startActivity(intent2);
        });

        // 恢复数据
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG,tempData);
        }
    }

    @Override
    protected void onStart() {     //又不可变变为可变时调用
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {    // 互动位于返回栈栈顶  并且处于运行状态  准备和用户交互时使用
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {     // 准备去启动或者恢复另一个活动时调用
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {     //  活动完全不可见时 调用
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {    //  活动被销毁时调用
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {     //  停止变为运行时使用
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    // 保存数据
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("day_key",tempData);
    }
}