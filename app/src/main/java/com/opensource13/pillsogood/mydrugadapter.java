package com.opensource13.pillsogood;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.TextView;


import com.opensource13.pillsogood.SQLite.Mydrugitem;


import java.util.ArrayList;

public class mydrugadapter extends BaseAdapter {

    ArrayList<Mydrugitem> list = new ArrayList<Mydrugitem>();

    @Override
    public int getCount() {
        return list.size(); //그냥 배열의 크기를 반환하면 됨
    }

    @Override
    public Object getItem(int i) {
        return list.get(i); //배열에 아이템을 현재 위치값을 넣어 가져옴
    }

    @Override
    public long getItemId(int i) {
        return i; //그냥 위치값을 반환해도 되지만 원한다면 아이템의 num 을 반환해도 된다.
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Context context = viewGroup.getContext();

        //리스트뷰에 아이템이 인플레이트 되어있는지 확인한후
        //아이템이 없다면 아래처럼 아이템 레이아웃을 인플레이트 하고 view객체에 담는다.
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.forlistview,viewGroup,false);
        }

        //이제 아이템에 존재하는 텍스트뷰 객체들을 view객체에서 찾아 가져온다
        TextView drugname = (TextView)view.findViewById(R.id.drug_name);
        TextView drugday = (TextView)view.findViewById(R.id.drug_day);
        TextView drugtime = (TextView)view.findViewById(R.id.drug_time);

        //현재 포지션에 해당하는 아이템에 글자를 적용하기 위해 list배열에서 객체를 가져온다.
        Mydrugitem listdata = list.get(i);

        //가져온 객체안에 있는 글자들을 각 뷰에 적용한다
        drugname.setText(listdata.getName());
        drugday.setText(listdata.getDay());
        drugtime.setText(listdata.getTime());

        return view;
    }
    public void addItemToList(String name,String day, String time){
        Mydrugitem listdata = new Mydrugitem();

        listdata.setName(name);
        listdata.setDay(day);
        listdata.setTime(time);

        //값들의 조립이 완성된 listdata객체 한개를 list배열에 추가
        list.add(listdata);

    }

}
