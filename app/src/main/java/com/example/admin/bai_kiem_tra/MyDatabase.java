package com.example.admin.bai_kiem_tra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by admin on 5/7/2017.
 */

public class MyDatabase {
    public static final String TABLE_NAME="sinhvien";
    public static final String ID="ID";
    public static final String NAME="NAME";
    public static final String CLASSNAME="CLASSNAME";
    public static final String SCORE="SCORE";
    //Dinh nghia duong dan luu file vao bo nho trong
    public static final String PATH= Environment.getDataDirectory().getPath()
            +"/data/com.example.admin.bai_kiem_tra/databases/ql_sinhvien.sqlite";
    private Context context;
    private SQLiteDatabase database;
    public MyDatabase(Context context) {
        this.context = context;
        copyFile();
    }

    private void copyFile() {
        try {
            File file=new File(PATH);
            if (file.exists()==false) {
                 File parent = file.getParentFile();
                parent.mkdirs();
                file.createNewFile();
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                InputStream inputStream=context.getAssets().open("ql_sinhvien.sqlite");
                byte [] b=new byte[1024];
                int count=inputStream.read(b);
                while (count!=-1) {
                    fileOutputStream.write(b, 0, count);
                    count=inputStream.read(b);
                }
                fileOutputStream.close();
                inputStream.close();
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Truy van co so du lieu
        private void openDatabase() {
            database=context.openOrCreateDatabase(PATH, context.MODE_PRIVATE, null);
        }

        private void closeDatabase() {
            database.close();
        }

        public ArrayList<SinhVien> getData() {
            ArrayList<SinhVien> arr=new ArrayList<>();
            openDatabase();
            //Cursor la con tro vao tung dong cua bang tu dong dau den dong cuoi, query: tra ve dang bang
            Cursor cursor=database.query(TABLE_NAME, null, null, null, null, null, null);
            //Doc du lieu
            cursor.moveToFirst();
            int indexId=cursor.getColumnIndex(ID);
            int indexName=cursor.getColumnIndex(NAME);
            int indexClassName=cursor.getColumnIndex(CLASSNAME);
            int indexScore=cursor.getColumnIndex(SCORE);
            while (cursor.isAfterLast()==false) {
                int id=cursor.getInt(indexId);
                String name=cursor.getString(indexName);
                String className=cursor.getString(indexClassName);
                float score=cursor.getFloat(indexScore);
                SinhVien sinhVien=new SinhVien(name, className, score, id);
                arr.add(sinhVien);
                cursor.moveToNext();
            }
            closeDatabase();
            return arr;
        }

        public long insert(SinhVien sinhVien) {
            openDatabase();
            ContentValues values=new ContentValues();
            values.put(NAME, sinhVien.getFullname());
            values.put(CLASSNAME, sinhVien.getClasses());
            values.put(SCORE, sinhVien.getScore());
            long newId=database.insert(TABLE_NAME, null, values);
            closeDatabase();
            return newId;
        }

        public int update(SinhVien sinhVien) {
            openDatabase();
            ContentValues values=new ContentValues();
            values.put(NAME, sinhVien.getFullname());
            values.put(CLASSNAME, sinhVien.getClasses());
            values.put(SCORE, sinhVien.getScore());
            String selection=ID+"=?";
            String[] selectionAgs={sinhVien.getId()+""};
            int rows = database.update(TABLE_NAME, values, selection, selectionAgs);
            closeDatabase();
            return rows;
        }

        public int delete(int id) {
            openDatabase();
            String selection=ID+"=?";
            String[] selectionAgs={id+""};
            int rows = database.delete(TABLE_NAME,selection, selectionAgs);
            closeDatabase();
            return rows;
        }
    }


