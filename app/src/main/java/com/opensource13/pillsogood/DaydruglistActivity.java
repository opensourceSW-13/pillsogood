package com.opensource13.pillsogood;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DaydruglistActivity extends AppCompatActivity {

    private FloatingActionButton btn_back;

    ListView listViewData;
    ArrayAdapter<String> adapter;
    String[] arrayPeliculas = {"약1","약2","약3","약4","약5"}; //배열


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daydruglist);


        //뒤로가기버튼
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaydruglistActivity.this, MainActivity.class);
            }
        });


        //체크리스트
        listViewData = findViewById(R.id.listview_data);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,arrayPeliculas);
        listViewData.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_done) {
            String itemSelected = "Selected items:\n";
            for (int i = 0; i < listViewData.getCount();i++) {
                if (listViewData.isItemChecked(i)) {
                    itemSelected += listViewData.getItemAtPosition(i) + "\n";
                }
            }
            Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);



    }
}

///@Override
    //public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    //    int id = item.getItemId();
    //    if (id == R.id.item_done) {
    //        String itemSelected = "Selected items:\n";
    //        for (int i = 0; i < listViewData.getCount();i++) {
    //            if (listViewData.isItemChecked(i)) {
    //                itemSelected += listViewData.getItemAtPosition(i) + "\n";
    //            }
    //        }
    //        Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
    //    }
    //    return super.onOptionsItemSelected(item);


        //DBHelper
        //DBHelper helper;
        //SQLiteDatabase db;
        //helper = new DBHelper(DaydruglistActivity.this, "newdb.db", null, 1);
        //db = helper.getWritableDatabase();
        //helper.onCreate(db);
        //DB에 정보를 저장 할 때 코드
        //DbHelper dbHelper = new DbHelper(this); DB헬퍼 호출
        //SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase(); 쓰기 모드로 적용
        //sqLiteDatabase.execSQL("INSERT INTO student VALUES(1,'김디비')"); 쿼리문을 실행

