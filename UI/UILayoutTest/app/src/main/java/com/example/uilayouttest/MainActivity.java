package com.example.uilayouttest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐藏自带标题栏
        ActionBar actionBar=getSupportActionBar();
        //通过getSupportActionBar方法获得ActionBar的实例
        if (actionBar!=null){
            actionBar.hide();
        }
    }
}