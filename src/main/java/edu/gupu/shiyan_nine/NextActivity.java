package edu.gupu.shiyan_nine;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {

    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个是获取布局文件的，这里是你下一个页面的布局文件
        setContentView(R.layout.activity_main_one);
        initMsgs();
        inputText=(EditText)findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.send);
        msgRecyclerView =(RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
        Button button=findViewById(R.id.photo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //监听按钮，如果点击，就跳转
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(NextActivity.this,PhotoActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initMsgs(){
        Msg msg1=new Msg("       Hello.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("   hello,are you?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("       Fine.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Msg msg4=new Msg("       And you?",Msg.TYPE_RECEIVED);
        msgList.add(msg4);
    }
}
