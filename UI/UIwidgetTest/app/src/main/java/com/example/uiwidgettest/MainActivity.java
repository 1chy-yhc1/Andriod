package com.example.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置按键监听器
        Button button = findViewById(R.id.button);
        EditText editText= findViewById(R.id.edit_text);
        ImageView imageView = findViewById(R.id.image_view);
        ProgressBar progressBar = findViewById(R.id.progress_bar);


        // 把按钮 和 文本输入框结合起来使用
        button.setOnClickListener(view -> {
            String inputText = editText.getText().toString(); // 得到内容并转化为字符串

            // 按键按下时 显示提示信息
            Toast.makeText(this, inputText, Toast.LENGTH_LONG).show();

            // 按键按下时 变换图片
            imageView.setImageResource(R.drawable.img2);

            // 设置进度条一点点增加
            int progress = progressBar.getProgress();
            progress = progress + 10;
            progressBar.setProgress(progress);

            // 按键按下时  进度条消失
            if (progressBar.getVisibility() == View.GONE) {
                progressBar.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
            }


            //  AlterDialog.Builder类   设置一个优先级最高的窗口
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this); // 创建窗口对象
            dialog.setTitle("这是一个警告");
            dialog.setMessage("这里出了一些问题");
            dialog.setCancelable(false);   // 不可以点击窗口之外的东西从而关掉窗口
            dialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();
        });

        //
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("这是一个加载窗口");
        progressDialog.setMessage("这是加载信息");
        progressDialog.setCancelable(true);  // 可以点击窗口之外的东西让窗口处于后台
        progressDialog.show();

    }
}

//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //设置按键监听器
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(this);
//
//    }
//
//    @Override // 这是一个抽象方法  继承 View.OnclickListener 接口时必须使用
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.button:
//                Toast.makeText(this, "可以添加逻辑 及添加按键事件", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//    }
//}