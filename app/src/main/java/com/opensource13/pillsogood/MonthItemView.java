package com.opensource13.pillsogood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

//import com.opensource13.pillsogood.SQLite.DataAdapter;
import com.opensource13.pillsogood.SQLite.SQLiteHelper;

public class MonthItemView extends AppCompatTextView {

    private MonthItem item;

    public MonthItemView(Context context) {
        super(context);

        init();
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setBackgroundColor(Color.rgb(255, 251, 244)); //연노랑
    }

   /* private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        SQLiteHelper.createDatabase();
        SQLiteHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        userList = SQLiteHelper.getTableData();

        // db 닫기
        SQLiteHelper.close();
    }
*/
    public MonthItem getItem() {

        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;
        //String result;
        int day = item.getDay();
        if (day != 0) {

            setText(String.valueOf(day));
            setVisibility(View.VISIBLE); // 화면에보임

        } else {
            setText("");
            setVisibility(View.INVISIBLE); // 화면에 안보임
        }

    }
}
