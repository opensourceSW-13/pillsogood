package com.opensource13.pillsogood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;
import java.util.List;

public class MydrugActivity extends AppCompatActivity {
    Button button_add;
    Button button_back;



    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydrug);

        list=(ListView)findViewById(R.id.list);

        List<String> data = new ArrayList<>(); //data에 다른 변경 값 넣을 수 있음

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);

        data.add("하현준");
        data.add("오픈소스");
        adapter.notifyDataSetChanged();

        button_add = (Button)findViewById(R.id.button_add);
        button_back =(Button)findViewById(R.id.button_back);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 화면 이동하는 동작 입력
                Intent intent = new Intent(MydrugActivity.this,SetalarmActivity.class);
                startActivity(intent); //화면 4로 이동(알람 설정)
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 화면 이동하는 동작 입력
                Intent intent = new Intent(MydrugActivity.this,MainActivity.class);
                startActivity(intent);//화면 1로 이동
            }
        });
    }
}