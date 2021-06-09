package com.opensource13.pillsogood;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Browser;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

//import com.opensource13.pillsogood.SQLite.DataAdapter;
import com.opensource13.pillsogood.SQLite.SQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    GridView monthView;

    MonthAdapter monthViewAdapter;

    TextView monthText;

    int curYear;

    int curMonth;

    String result;

    int dayofweek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteHelper helper;
        SQLiteDatabase db;
        helper = new SQLiteHelper(MainActivity.this);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        //db.execSQL("insert into MYDRUG values('" + "감기약" + "','" + "수" + "','" + "12:00" + "')");
        //db.execSQL("insert into MYDRUG values('" + "항생제" + "','" + "월" + "','" + "13:00" + "')"); //db insert code
       // Cursor cursor = db.rawQuery("SELECT * FROM MYDRUG", null);
       // result = "";
       // while (cursor.moveToNext()) {
            //String temp = cursor.getString(0);
            //titleView.setText(cursor.getString(0));

            //   Log.d("MainActivity", "drug_name : " + temp);
          //   }

            // 월별 캘린더 뷰 객체 참조
            monthView = (GridView) findViewById(R.id.monthView);
            monthViewAdapter = new MonthAdapter(getApplicationContext());
            monthView.setAdapter(monthViewAdapter);


            // 리스너 설정
            monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // 현재 선택한 일자 정보 표시
                    MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                    int day = curItem.getDay();
                    dayofweek=

                    Log.d("MainActivity", "Selected : " + day);

                }
            });

            // 날짜 누르면 DaydruglistActivity로 넘어가는 이벤트 처리
            monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent = new Intent(MainActivity.this, DaydruglistActivity.class);
                    int result = position%7;
                    intent.putExtra("dayofweek",result); // put image data in Intent
                    Log.d("칼럼값", String.valueOf(monthViewAdapter.columnIndex));
                    startActivity(intent); // start Intent
                }
            });

            monthText = (TextView) findViewById(R.id.monthText);
            setMonthText();

            // 이전 월로 넘어가는 이벤트 처리
            Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
            monthPrevious.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    monthViewAdapter.setPreviousMonth();
                    monthViewAdapter.notifyDataSetChanged();

                    setMonthText();
                }
            });

            // 다음 월로 넘어가는 이벤트 처리
            Button monthNext = (Button) findViewById(R.id.monthNext);
            monthNext.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    monthViewAdapter.setNextMonth();
                    monthViewAdapter.notifyDataSetChanged();
                    setMonthText();
                }
            });

            // "내 약" 버튼 누르면 MydrugActivity로 넘어가는 이벤트 처리
            Button myPeel = (Button) findViewById(R.id.myPill);
            myPeel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MydrugActivity.class);
                    startActivity(intent);
                }
            });
        }

        //월 표시 텍스트 설정
        private void setMonthText() {
            curYear = monthViewAdapter.getCurYear();
            curMonth = monthViewAdapter.getCurMonth();

            monthText.setText(curYear + "년 " + (curMonth + 1) + "월");
        }

         /* void displayList() {
            SQLiteHelper helper = new SQLiteHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM MYDRUG", null);

            MonthAdapter adapter = new MonthAdapter();

            while(cursor.moveToNext()){
                //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
                adapter.addItemToList(cursor.getString(0),cursor.getString(1));
            }
        }

        */

}