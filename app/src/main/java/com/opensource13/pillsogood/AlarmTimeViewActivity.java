package com.opensource13.pillsogood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AlarmTimeViewActivity extends AppCompatActivity {

    TextView alarmSetTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_time_view);

        alarmSetTime=findViewById(R.id.tv_alarm_set_day_time);

        Intent intent=getIntent();
        int setDay=intent.getIntExtra("setDay",0);
        String day="";
        switch (setDay){
            case 0:
                day="err";
                break;
            case 1:
                day="일요일";
                break;
            case 2:
                day="월요일";
                break;
            case 3:
                day="화요일";
                break;
            case 4:
                day="수요일";
                break;
            case 5:
                day="목요일";
                break;
            case 6:
                day="금요일";
                break;
            case 7:
                day="토요일";
                break;
            default:
                day="err";
                break;
        }

        String setTime=day+" "+intent.getStringExtra("setTime")+"\n"+"약을 복용하세요!";
        alarmSetTime.setText(setTime);
    }// onCreate()..
}// AlarmTimeViewActivity class..