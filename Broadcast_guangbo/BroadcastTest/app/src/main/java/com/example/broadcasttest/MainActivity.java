package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  广播监视器
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        networkChangeReceiver = new NetworkChangeReceiver();

        registerReceiver(networkChangeReceiver,intentFilter);

    }

    
    //  动态广播需要取消注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();

            // 系统服务类 专门管理网络
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);

            // 用这个是为了能使用 里面的 isAvailable()方法
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

            //  isAvailable() 判断是否有网络
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context,"network  isavailable",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }

        }
    }
}