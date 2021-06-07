package com.opensource13.pillsogood;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.opensource13.pillsogood.SQLite.SQLiteHelper;

import java.security.PublicKey;
import java.util.Calendar;

public class SetalarmActivity extends AppCompatActivity {

    TimePickerDialog picker;

    // Edit Text가 아닌 영역을 터치하는 경우, 키보드 내림
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

        SQLiteHelper helper = new SQLiteHelper(this); // 헬퍼 선언


        // 복용 요일 체크박스
        final RadioButton cb_Mon = (RadioButton) findViewById(R.id.cb_Mon);
        final RadioButton cb_Tue = (RadioButton) findViewById(R.id.cb_Tue);
        final RadioButton cb_Wed = (RadioButton) findViewById(R.id.cb_Wed);
        final RadioButton cb_Thu = (RadioButton) findViewById(R.id.cb_Thu);
        final RadioButton cb_Fri = (RadioButton) findViewById(R.id.cb_Fri);
        final RadioButton cb_Sat = (RadioButton) findViewById(R.id.cb_Sat);
        final RadioButton cb_Sun = (RadioButton) findViewById(R.id.cb_Sun);

        // 복용 시간 입력값 가져오기 (time picker 띄우기)
        TextView pillTime = (TextView) findViewById(R.id.pillTime);
        pillTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minute = cldr.get(Calendar.MINUTE);

                // Time picker Dialog

                TimePickerDialog picker = new TimePickerDialog(SetalarmActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        //if (selectedHour > 12) {
                        //    selectedHour -= 12;
                        //    state = "PM";
                        //}

                        String hourString;
                        String minuteString;

                        if (selectedHour <10) {
                            hourString = "0" + selectedHour;
                        } else {
                            hourString = String.valueOf(selectedHour);
                        }

                        if (selectedMinute < 10) {
                            minuteString = "0" + selectedMinute;
                        } else {
                            minuteString = String.valueOf(selectedMinute);
                        }

                        String timeString = hourString + ":" + minuteString;

                        // EditText에 출력할 형식 지정
                        pillTime.setText(timeString);
                    }
                }, hour, minute, true); // true의 경우 24시간 형식의 TimePicker 출현
                picker.getWindow().setBackgroundDrawableResource((android.R.color.transparent));
                picker.show();
            }
        });


        // 이전 버튼 클릭 시 화면3으로 액티비티 전환
        Button setAlarmPrevious = (Button) findViewById(R.id.setAlarmPrevious);
        setAlarmPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MydrugActivity.class);
                startActivity(intent);
            }
        });

        // 저장 버튼 클릭 시 DB에 내용 추가 + 액티비티 전환
        Button setAlarmSave = (Button) findViewById(R.id.setAlarmSave);
        setAlarmSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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
                if (cb_Mon.isChecked() == true) day = cb_Mon.getText().toString();
                if (cb_Tue.isChecked() == true) day = cb_Tue.getText().toString();
                if (cb_Wed.isChecked() == true) day = cb_Wed.getText().toString();
                if (cb_Thu.isChecked() == true) day = cb_Thu.getText().toString();
                if (cb_Fri.isChecked() == true) day = cb_Fri.getText().toString();
                if (cb_Sat.isChecked() == true) day = cb_Sat.getText().toString();
                if (cb_Sun.isChecked() == true) day = cb_Sun.getText().toString();


                // 복용 시간 입력값 가져오기
                String time =  pillTime.getText().toString();


                // 메모 입력값 가져오기
                EditText pillMemo = (EditText)findViewById(R.id.pillMemo);
                String memo = pillMemo.getText().toString();

                // DB에 항목 추가
                SQLiteDatabase db;

                db = helper.getWritableDatabase();
                db.execSQL("insert into MYDRUG values('" + name + "', '" + day + "', '" + time + "', '" + memo + "')");

                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_LONG).show();

                // 화면 3으로 액티비티 전환
                Intent intent = new Intent(getApplicationContext(), MydrugActivity.class);
                startActivity(intent);

            }


        });

    }
}