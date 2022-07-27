package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


// 不知道为什么书上总喜欢继承这个接口做事情
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        Button button1 = findViewById(R.id.left_back);
        button1.setOnClickListener(this);

        replaceFragment(new RightFragment());    //
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                replaceFragment(new AnotherRightFragment());   // 创建待添加的碎片实例
                Toast.makeText(this, "你切换了右侧界面", Toast.LENGTH_SHORT).show();
                break;

            case R.id.left_back:
                finish();
                break;

            default:
                break;
        }
    }

    private  void replaceFragment (Fragment fragment)  {
        FragmentManager fragmentManager = getSupportFragmentManager(); // 获取FragmentManager
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.right_layout,fragment);  // 添加或替换碎片

        fragmentTransaction.addToBackStack(null); //  将一个事物放在返回栈中  以便返回到上一个事务

        fragmentTransaction.commit();       //  提交事物
    }

//    RightFragment rightFragment = (RightFragment) getFragmentManager().findFragmentById(R.id.right_layout);
//
//    MainActivity activity = (MainActivity) getActivity();

}