package com.opensource13.pillsogood.SQLite;

public class Mydrugitem {


    private String name;        //약이름
    private String day;         //복용 요일
    private String time;         //복용 시간
    private String memo;

    public Mydrugitem() {
    }



    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getMemo() {
        return memo;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}

