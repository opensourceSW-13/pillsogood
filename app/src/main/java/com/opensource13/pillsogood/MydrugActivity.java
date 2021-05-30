package com.opensource13.pillsogood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        //DB에 정보를 저장 할 때 코드
        //DbHelper dbHelper = new DbHelper(this); DB헬퍼 호출
        //SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase(); 쓰기 모드로 적용
        //sqLiteDatabase.execSQL("INSERT INTO student VALUES(1,'김디비')"); 쿼리문을 실행


        //DB에 있는 정보를 불러 올 때 코드
        //DbHelper dbHelper = new DbHelper(this); //Openhelper객체 생성
        //SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase(); //쓰기모드로 사용
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM student",null);
        //쿼리문 실행후 결과값을 cursor객체에 담기
        //while(cursor.moveToNext()){//가져온 결과들의 튜플 수만큼 수행
        //    textView.setText(cursor.getInt(0)+" : "+cursor.getString(1));
        // 현재 가져온 튜플을 띄우도록 함
        //}


    }
}