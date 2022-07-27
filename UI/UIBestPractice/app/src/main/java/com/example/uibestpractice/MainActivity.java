package com.example.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以为RecyclerView初始化一些数据
 */
public class MainActivity extends AppCompatActivity {

    //消息List
    private List<Msg> msgList=new ArrayList<>();

    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据
        initMsgs();

        //获取控件EditText Button RecyclerView
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);


        //this--->MainActivity
        //context：上下文，初始化时，用于构造方法内部加载资源
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        msgRecyclerView.setLayoutManager(layoutManager);

        adapter=new MsgAdapter(msgList);

        msgRecyclerView.setAdapter(adapter);

        //注册点击事件
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取在EditText输入的内容
                String content=inputText.getText().toString();

                //如果内容不为空
                if(!"".equals(content)){

                    //将消息初始化为发送消息
                    Msg msg=new Msg(content,Msg.TYPE_SEND);

                    //添加数据
                    msgList.add(msg);

                    //当有新消息时，刷新Lis†View中的显示
                    adapter.notifyItemInserted(msgList.size()-1);

                    //将ListView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size()-1);

                    //清空输入框的内容
                    inputText.setText("");
                }
            }
        });


    }


    /*
    初始化几条数据，用于在RecyclerView中显示
     */
    private void initMsgs() {

        Msg msg1=new Msg("Hello guy.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);

        Msg msg2=new Msg("Hello. Who is that?",Msg.TYPE_SEND);
        msgList.add(msg2);

        Msg msg3=new Msg("This is Tom. Nice talking to you.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);

    }

}