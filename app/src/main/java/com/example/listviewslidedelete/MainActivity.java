package com.example.listviewslidedelete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<String> contentList=new ArrayList();
    private MyListView myListView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: xxxx");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();

        //获取listview,配置适配器并添加删除监听事件
        myListView = (MyListView) findViewById(R.id.my_list_view);
        myAdapter = new MyAdapter(this, 0, contentList);
        myListView.setAdapter(myAdapter);
        myListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                Log.d(TAG, "onDelete: xxxx");
                //删除数据并刷新数据
                contentList.remove(index);
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 初始化list数据
     */
    private void initList() {
        Log.d(TAG, "initList: xxxx");
        for(int i=1;i<=20;i++){
            contentList.add("Content Item "+i);
        }
    }
}
