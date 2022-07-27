package com.example.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  //  活动被创建必须有的方法
        //Log.d("helloworldActivity","hello_world_layout"); //日志文件 tag：信息过滤 msg：具体的内容
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.first_layout);
    }
}