package com.example.fragmenttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeftFragment extends Fragment {

    @Nullable
    @Override

    // 加载刚才的布局
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 1.resource 布局的资源id 2.root 填充的根视图 3.attachToRoot 是否将载入的视图绑定到根视图中
        View view = inflater.inflate(R.layout.left_fragment,container,false);
        return view;
    }
}
