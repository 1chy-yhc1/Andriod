package com.example.fragmentbestpractis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.inputmethod.InputMethodManager;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        String newsTitle = getIntent().getStringExtra("news_title");  // 获取传入的新闻标题
        String newsContent = getIntent().getStringExtra("news_content"); // 获取传入的新闻内容

        NewsContentFrament newsContentFrament = (NewsContentFrament) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment); // 强制转换，父类方法子类用
        newsContentFrament.refresh(newsTitle,newsContent);    // 刷新NewsContentFragment界面
    }

    public static void actionStart(Context context, String newsTitile,String newsContent) {
        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitile);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }

}