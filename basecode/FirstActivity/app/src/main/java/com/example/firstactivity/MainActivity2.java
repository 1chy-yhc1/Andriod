package com.example.firstactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


//创建的一个副活动  显式intent
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

//        // 接收信息 用日志文件数据的形式表示出来
//        Intent intent3 = getIntent();
//        String data = intent3.getStringExtra("extra_data");
//        Log.i("MainActivity2:",data);

        //返回数据给MainActivity
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(view -> {
            Intent intent4 = new Intent();
            intent4.putExtra("data_return","Hello MainAtivity");
            setResult(RESULT_OK,intent4);  // 专门返回信息  向上一个活动返回处理结果  +  把带有数据的intent传递回去
            finish();
        });
    }
}