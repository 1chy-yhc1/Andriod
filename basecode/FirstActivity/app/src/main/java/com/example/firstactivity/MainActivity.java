package com.example.firstactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("FirstActivity",this.toString());

        setContentView(R.layout.first_activity);

        Button button_1= findViewById(R.id.button_1);   //设置点击事件
        button_1.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this,"你点击了按钮1",Toast.LENGTH_SHORT).show(); // 联系＋语言＋时间长短

            // 默认启动模式  standard   在自己活动的基础上  继续启动自己
            Intent intent  = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);


//            //显式跳转
//            Intent intent  = new Intent(MainActivity.this, MainActivity2.class);
//            startActivity(intent);
//
//            //隐式跳转
//            Intent intent1 = new Intent(Intent.ACTION_VIEW);
//            intent1.setData(Uri.parse("http://www.baidu.com"));
//            startActivity(intent1);

//            // 传递信息给MainActivity2
//            String data = "Hello MainActivity2";
//            Intent intent3 = new Intent(this,MainActivity2.class);
//            intent3.putExtra("extra_data",data);
//            startActivity(intent3);


//            //接收从下一级互动返回的数据
//            Intent intent4 = new Intent(this,MainActivity2.class);
//            startActivityForResult(intent4,1);

           // finish();  //销毁一个活动
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (requestCode == RESULT_OK) {
                    String returnedDAta = data.getStringExtra("data_return");
                    Log.d("MainActivity:", returnedDAta);
                }
                break;
            default:
        }
    }

    // 菜单的显示
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    // 菜单的分选项
    @SuppressLint({"NonConstantResourceId", "ShowToast"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"你点了增加",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"你点击了删除",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}