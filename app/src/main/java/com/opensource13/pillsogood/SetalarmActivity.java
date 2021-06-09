package com.opensource13.pillsogood;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.opensource13.pillsogood.SQLite.SQLiteHelper;

import java.security.PublicKey;
import java.util.Calendar;
import java.util.Date;

public class SetalarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private AlarmManager alarmManager;
    SQLiteHelper helper = new SQLiteHelper(this);
    SQLiteDatabase db;

    private int hour, minute;
    CheckBox cbSun, cbMon, cbTue, cbWed, cbThu, cbFri, cbSat;

    static String TAG="setalarm";
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);

        timePicker=findViewById(R.id.tp_timepicker);
        alarmManager= (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        cbSun=findViewById(R.id.cb_sun);
        cbMon=findViewById(R.id.cb_mon);
        cbTue=findViewById(R.id.cb_tue);
        cbWed=findViewById(R.id.cb_wed);
        cbThu=findViewById(R.id.cb_thu);
        cbFri=findViewById(R.id.cb_fri);
        cbSat=findViewById(R.id.cb_sat);

        Button setAlarmPrevious = (Button) findViewById(R.id.setAlarmPrevious);
        setAlarmPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MydrugActivity.class);
                startActivity(intent);
            }
        });

        Button setAlarmSave = (Button) findViewById(R.id.setAlarmSave);
        setAlarmSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                regist(v);

                // 약 이름 입력값 가져오기
                EditText pillName = (EditText) findViewById(R.id.pillName);
                String name = pillName.getText().toString();


                if (TextUtils.isEmpty(pillName.getText())) { // 공백이면
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }


                // 복용 요일 입력값 가져오기
                String day = "";
                // 다중선택 가능하게 할 경우 -> 체크박스로 바꾼 후
                // if (cb_Mon.isChecked() == true) day += cb_Mon.getText().toString() + " ";
                if (cbMon.isChecked() == true) day = cbMon.getText().toString();
                if (cbTue.isChecked() == true) day = cbTue.getText().toString();
                if (cbWed.isChecked() == true) day = cbWed.getText().toString();
                if (cbThu.isChecked() == true) day = cbThu.getText().toString();
                if (cbFri.isChecked() == true) day = cbFri.getText().toString();
                if (cbSat.isChecked() == true) day = cbSat.getText().toString();
                if (cbSun.isChecked() == true) day = cbSun.getText().toString();





                // 메모 입력값 가져오기
                EditText pillMemo = (EditText)findViewById(R.id.pillMemo);
                String memo = pillMemo.getText().toString();

                // DB에 항목 추가
                SQLiteDatabase db;

                db = helper.getWritableDatabase();
                db.execSQL("insert into MYDRUG values('" + name + "', '" + day + "', '" + hour+":"+minute + "', '" + memo + "')");

                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_LONG).show();

                // 화면 3으로 액티비티 전환
                Intent intent = new Intent(getApplicationContext(), MydrugActivity.class);
                startActivity(intent);

            }


        });

    }// onCreate()..

    public void regist(View view) {


        boolean[] week = { false, cbSun.isChecked(), cbMon.isChecked(), cbTue.isChecked(), cbWed.isChecked(),
                cbThu.isChecked(), cbFri.isChecked(), cbSat.isChecked() }; // cbSun을 1번부터 사용하기 위해 배열 0번은 false로 고정

        if(!cbSun.isChecked() &&  !cbMon.isChecked() &&  !cbTue.isChecked() && !cbWed.isChecked() &&  !cbThu.isChecked() && !cbFri.isChecked() && !cbSat.isChecked()){
            Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour=timePicker.getHour();
            minute=timePicker.getMinute();
        }else{
            Toast.makeText(this, "버전을 확인해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, Alarm.class);
        intent.putExtra("weekday", week);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0,intent, 0); //PendingIntent.FLAG_UPDATE_CURRENT

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = new Date();
        long intervalDay = 24 * 60 * 60 * 1000;// 24시간

        long selectTime=calendar.getTimeInMillis();
        long currenTime=System.currentTimeMillis();

        //만약 설정한 시간이, 현재 시간보다 작다면 알람이 부정확하게 울리기 때문에 다음날 울리게 설정
        if(currenTime>selectTime){
            selectTime += intervalDay;
        }

        Log.e(TAG,"등록 버튼을 누른 시간 : "+today+"  설정한 시간 : "+calendar.getTime());

        Log.d(TAG,"calendar.getTimeInMillis()  : "+calendar.getTimeInMillis());

        // 지정한 시간에 매일 알림
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, selectTime,  intervalDay, pIntent);

    }// regist()..

    public void unregist(View view) {
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pIntent);
    }// unregist()..
}