package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 自己创建的数据库操作类
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        dpHelper = new MyDatabaseHelper(this,"BookSrtore.db",null,1);
        // 升级 数据库
        dbHelper = new MyDatabaseHelper(this,"BookSrtore.db",null,2);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(this);

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // insert()方法的第三个参数需要这个类的对象  有很多put()方法的重载
                ContentValues values = new ContentValues();
                // 开始组装数据
                values.put("name","The Da Vinvi Code");
                values.put("author","Dan Brown");
                values.put("pages",465);
//                values.put("price",16.96);
                values.put("id",1);
                db.insert("Book",null,values); // 插入第一条数据

                // 组装第二条数据
                values.put("name","The Da Code");
                values.put("author","Dan Bwn");
                values.put("pages",45);
                values.put("id",2);
//                values.put("price",166);
                db.insert("Book",null,values); // 插入第二条数据

                values.put("name","The Da Code");
                values.put("author","Dan Bwn");
                values.put("pages",45);
                values.put("id",4);
//                values.put("price",166);
                db.insert("Book",null,values); // 插入第3条数据

                values.put("name","The Da Code");
                values.put("author","Dan Bwn");
                values.put("pages",45);
                values.put("id",1);
//                values.put("price",166);
                db.insert("Book",null,values); // 插入第4条数据

            }
        });

        Button updateDate = findViewById(R.id.update_data);
        updateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("id",9);
                db.update("Book",values,"id = ?",new String[]{"1"});
            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","id = ?",new String[]{"2"});
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // 查询表中所有的数据
                Cursor cursor = db.query("Book",null,null,null,null,null,null);

                if (cursor.moveToFirst()) {
                    do {
                        // 便利Cursor对象，取出数据并打印
//                        String id = cursor.getString(cursor.getColumnIndex("id"));
//                        String author = cursor.getString(cursor.getColumnIndex("author"));
//                        String pages = cursor.getString(cursor.getColumnIndex("pages"));
//                        String name = cursor.getString(cursor.getColumnIndex("name"));
//
//                        Log.d("ning",id);
//                        Log.d("ning",author);
//                        Log.d("ning",pages);
//                        Log.d("ning",name);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });



    }


    @Override
    public void onClick(View v) {
        dbHelper.getWritableDatabase();

    }
}