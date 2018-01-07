package com.fx.manage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 15934 on 2017/12/21.
 */
public class MySQL extends SQLiteOpenHelper{

    private static final String name = "lib"; //数据库名称
    private static final int version = 1; //数据库版本
    //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类

    public MySQL(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE collection (_id INTEGER PRIMARY KEY AUTOINCREMENT,bookname VARCHAR(200),author VARCHAR(100),image BLOB,d" +
                "escription VARCHAR(1000))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
