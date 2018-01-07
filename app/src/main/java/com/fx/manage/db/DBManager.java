package com.fx.manage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.StringBuilderPrinter;

import com.fx.manage.bean.Collection;

import java.util.ArrayList;

/**
 * Created by 15934 on 2017/12/21.
 */
public class DBManager {

    private static DBManager mDbManager=new DBManager();
    private static SQLiteDatabase mDb;
    private static Context context;
    private  DBManager() {

    }
    public static synchronized DBManager getInstance(Context context){
        DBManager.context = context;
        if(mDb==null){
            MySQL mySQL = new MySQL(context);
            mDb = mySQL.getWritableDatabase();
        }
        return mDbManager;
    }
    interface NewTable{
        String TABLE_NAME="collection";
        String TABLE_COLUMN_bookname="bookname";
        String TABLE_COLUMN_author="author";
        String TABLE_COLUMN_image="image";
        String TABLE_COLUMN_description="description";
    }

    //增
    public void insert(Collection info){
        ContentValues values=new ContentValues();
        values.put(NewTable.TABLE_COLUMN_bookname, info.getBookname());
        values.put(NewTable.TABLE_COLUMN_author, info.getAuthor());
        values.put(NewTable.TABLE_COLUMN_image, info.getImage());
        values.put(NewTable.TABLE_COLUMN_description, info.getDescription());
        Log.w("mDb.insert", ""+info);
        Log.w("mDb.insert",""+ values);
        mDb.insert(NewTable.TABLE_NAME, null, values);
    }
    //删
    public void delete(String bookname) {
        Log.w("delete", bookname);
        mDb.delete(NewTable.TABLE_NAME, NewTable.TABLE_COLUMN_bookname+"=?", new String[]{bookname});
    }
    //改
    public void update(Collection info){

    }
    //查
    public ArrayList<Collection> query(){

        Cursor cursor = mDb.query(NewTable.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Collection> list=new ArrayList<Collection>();
        boolean toFirst = cursor.moveToFirst();
        while (toFirst) {
            String bookname = cursor.getString(cursor.getColumnIndex(NewTable.TABLE_COLUMN_bookname));
            String author = cursor.getString(cursor.getColumnIndex(NewTable.TABLE_COLUMN_author));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(NewTable.TABLE_COLUMN_image));
            String description = cursor.getString(cursor.getColumnIndex(NewTable.TABLE_COLUMN_description));
            Collection collection = new Collection(bookname,author,image,description);
            list.add(collection);
            toFirst=cursor.moveToNext();
        }
        cursor.close();
        return list;

    }


}
