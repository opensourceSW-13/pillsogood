package com.opensource13.pillsogood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.opensource13.pillsogood.SQLite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MydrugActivity extends AppCompatActivity {
    Button button_add;
    Button button_back;
    Button button_delete;
    Button button_information;


    ListView mydruglist;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydrug);

    ///// 리스트 뷰 작업

        // 빈 데이터 리스트 생성.
     //   items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
      //  final ArrayAdapter adapter = new ArrayAdapter(this,
      //          android.R.layout.simple_list_item_single_choice, items) ;

        // delete button에 대한 이벤트 처리.
     //   Button button_delete = (Button)findViewById(R.id.button_delete) ;
     //   button_delete.setOnClickListener(new Button.OnClickListener() {
     //       public void onClick(View v) {
     //           db Delete= new db();


     //       }
     //   }) ;


    ///// 리스트 뷰 작업 end

        mydruglist = (ListView)findViewById(R.id.mydrug_list);
        button_add = (Button)findViewById(R.id.button_add);
        button_back = (Button)findViewById(R.id.button_back);
        button_delete = (Button)findViewById(R.id.button_delete);
        button_information = (Button)findViewById(R.id.button_information);
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
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db Delete = new db();
                Delete.db.execSQL("delete from MYDRUG ");

            }
        });
        button_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"약 추가 버튼: 약을 추가하는 화면으로 이동\n 약 삭제 버튼: 약 리스트를 삭제",Toast.LENGTH_SHORT).show();
            }
        });
     /*   button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 리스트뷰 아이템 중 가장 밑에 있는 아이템 삭제하는 동작 입력

                    int count, checked ;
                    count = mydrugadapter.getCount() ;

                    if (count > 0) {
                        // 현재 선택된 아이템의 position 획득.
                        checked = listview.getCheckedItemPosition();

                        if (checked > -1 && checked < count) {
                            // 아이템 삭제
                            items.remove(checked) ;

                            // listview 선택 초기화.
                            listview.clearChoices();

                            // listview 갱신.
                            adapter.notifyDataSetChanged();
                        }
                    }
            }
        });*/

        displayList();


    }
    void displayList(){
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM MYDRUG",null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        mydrugadapter adapter = new mydrugadapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(cursor.getString(0),cursor.getString(1),cursor.getString(2));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        mydruglist.setAdapter(adapter);

    }
}