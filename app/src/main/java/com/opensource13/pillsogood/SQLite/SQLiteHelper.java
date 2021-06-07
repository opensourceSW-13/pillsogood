package com.opensource13.pillsogood.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.opensource13.pillsogood.MainActivity;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "MYDRUG.db"; //저장한 디비의 이름

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS MYDRUG (name TEXT PRIMARY KEY NOT NULL, day TEXT NOT NULL, time TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String drop_query = "drop table " + DB_NAME + ";";
        db.execSQL(drop_query);

        onCreate(db);

    }
//
//    public ArrayList getdruglist(){
//
//        String SELECT_QUERY = "SELECT * FROM MYDRUG";
//
//        ArrayList Mydrugitem = new ArrayList<Mydrugitem>();
//
//        Cursor cur= getWritableDatabase().rawQuery(SELECT_QUERY, null);
//        if(cur!=null && cur.moveToFirst()){
//            do{
//                Mydrugitem.add(new Mydrugitem(cur.getString(0), cur.getString(1), cur.getString(2)));
//            }while(cur.moveToNext());
//        }
//        return Mydrugitem;
//    }


//    public ArrayList<Mydrugitem> getdruglist(){
//        ArrayList<Mydrugitem> mydrugitems = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM MYDRUG");
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
