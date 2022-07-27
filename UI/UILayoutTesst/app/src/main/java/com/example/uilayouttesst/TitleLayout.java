package com.example.uilayouttesst;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.jar.Attributes;

// 自定义控件

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);  // from() 构建出一个LayoutInflater对象  inflate(加载的布局文件 + 父类布局) ： 动态加载一个布局文件

        Button button_back = findViewById(R.id.title_back);
        Button button_edit = findViewById(R.id.title_edit);

        button_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

        button_edit.setOnClickListener(view -> {
            Toast.makeText(context, "你点击了下一步", Toast.LENGTH_SHORT).show();
        });

    }
}

