package com.example.fragmentbestpractis;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

/**
 * 这个类创建于 news_content_frag.xml 创建之后
 * news_content_frag.xml 这个文件是主要的布局文件
 */

public class NewsContentFrament extends Fragment {

    private View view;

    @Nullable
    @Override
//LayoutInflater inflater：作用类似于findViewById（），findViewById（）用来寻找xml布局下的具体的控件（Button、TextView等），LayoutInflater inflater（）用来找res/layout/下的xml布局文件
//ViewGroup container：表示容器，View放在里面（还不理解）
//Bundle savedInstanceState：保存当前的状态，在活动的生命周期中，只要离开了可见阶段，活动很可能就会被进程终止，这种机制能保存当时的状态

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }

    // 刷新布局
    public void refresh(String newsTitle,String newsContent) {

        View visibilityLayout = view.findViewById(R.id.visibility_layout); // 给View创建一个对象 并具体化对象
        visibilityLayout.setVisibility(View.VISIBLE);  // 设置控件可见

        TextView newsTitleText = view.findViewById(R.id.news_title);   // 新闻标题
        TextView newsContentText = view.findViewById(R.id.news_content);  // 新闻内容

        newsContentText.setText(newsContent);
        newsTitleText.setText(newsTitle);
    }
}
