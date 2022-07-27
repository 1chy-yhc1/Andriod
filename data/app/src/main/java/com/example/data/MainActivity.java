package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;


import com.example.data.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private load load = new load();
    private save save = new save();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.exit);

        String inputText = load.load();
        if (!TextUtils.isEmpty(inputText)) {
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();
        }
    }


    // 要摧毁的时候 保存数据
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        save.save(inputText);
    }


//    public void save(String inputText){
//        String data = inputText;
//        FileOutputStream fileOutputStream = null;
//        BufferedWriter writer = null;
//
//        try {
//            fileOutputStream = openFileOutput("data", Context.MODE_PRIVATE);
//            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//            writer.write(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (writer != null) {
//                    writer.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public String load() {
//        FileInputStream in = null;                    // 打开文件
//        BufferedReader reader = null;                 // 拿到数据
//        StringBuilder content = new StringBuilder();   // 储存数据
//
//        try {
//            in = openFileInput("data");
//            reader = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                content.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return content.toString();
//    }
}