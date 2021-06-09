package com.opensource13.pillsogood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opensource13.pillsogood.SQLite.SQLiteHelper;

import java.util.ArrayList;
import java.util.Calendar;


public class DaydruglistActivity extends AppCompatActivity {

    ListView mydruglist;
    ArrayList<String> items;
    int dayofweek;
    String result;   //해당 요일


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daydruglist);
        mydruglist = (ListView)findViewById(R.id.listview_data);

        Intent intent = getIntent();
        dayofweek = intent.getIntExtra("dayofweek",0);
        Button setAlarmPrevious = (Button) findViewById(R.id.setAlarmPrevious);
        setAlarmPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        change();



        displayList();



    }

    void change(){

        if (dayofweek == 0) {
            result = "일";
        } else if (dayofweek == 1) {
            result = "월";
        } else if (dayofweek == 2) {
            result = "화";
        } else if (dayofweek == 3) {
            result = "수";
        } else if (dayofweek == 4) {
            result = "목";
        } else if (dayofweek == 5) {
            result = "금";
        } else if (dayofweek == 6) {
            result = "토";
        }

    }

    void displayList(){
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM MYDRUG WHERE day = '"+ result+ "'",null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        daydrugadapter adapter = new daydrugadapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        mydruglist.setAdapter(adapter);

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

