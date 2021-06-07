package com.opensource13.pillsogood.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;
import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "MYDRUG.db";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS MYDRUG (name TEXT PRIMARY KEY NOT NULL, day TEXT NOT NULL, time TEXT NOT NULL, memo TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String drop_query = "drop table " + DB_NAME + ";";
        db.execSQL(drop_query);

        onCreate(db);

    }

    /*public String getNameResult(SQLiteDatabase db) {
        // 읽기가 가능하게 DB 열기
        db = getReadableDatabase();
        String nameResult = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MYDRUG", null);
        while (cursor.moveToNext()) {
            nameResult += cursor.getString(0)        //약이름
                       + "\n";
        }

        return nameResult;

    }

    public String getDayResult(SQLiteDatabase db) {
        // 읽기가 가능하게 DB 열기
        db = getReadableDatabase();
        String dayResult = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MYDRUG", null);
        while (cursor.moveToNext()) {
            dayResult += cursor.getString(1)        //요일 (column 판별용)
                      + "\n";
        }

        return dayResult;

    }

   */



//    public ArrayList<Mydrugitem> getdruglist(){
//        ArrayList<Mydrugitem> mydrugitems = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM MYDRUG", null);
//        if(cursor.getCount() != 0){
//            while (cursor.moveToNext()){
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                String day = cursor.getString(cursor.getColumnIndex("day"));
//                String time = cursor.getString(cursor.getColumnIndex("time"));
//
//                Mydrugitem mydrugitem = new Mydrugitem();
//                mydrugitem.setName(name);
//                mydrugitem.setDay(day);
//                mydrugitem.setTime(time);
//                mydrugitems.add(mydrugitem);
//
//            }
//
//        }
//        cursor.close();
//        return mydrugitems;
//    }
//
//    //DB 목록 추가
//    public void Insertdrug(String _name, String _day, String _time) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("INSERT INTO MYDRUG (name, day, time) VALUES('" + _name + "', '" + _day + "', '" + _time + "');");
//    }
//
//    public void Updatedrug(String _name, String _day, String _time){
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("UPDATE MTDRUG SET name='" +_name +"', day='" +_day +"', time='" +_time +"' WHERE '" +_name +"' in name ");
//    }
//
//    public void Deletedrug(String _name){
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM MYDRUG WHERE id = '"+_name+"'");
//    }



}
