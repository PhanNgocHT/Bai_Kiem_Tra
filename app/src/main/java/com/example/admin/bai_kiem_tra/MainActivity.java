package com.example.admin.bai_kiem_tra;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener{

    public static final String HO_TEN="ho ten";
    public static final String MON_HOC="mon hoc";
    public static final String DIEM="diem";
    public static final String ID="id";

    private static final int REQUEST_A = 1;
    private static final int REQUEST_B = 2;
    private ListView lvStudent;
    private MyDatabase myDatabase;

    private int edit;
    private StudentAdapter studentAdapter;
    private ArrayList<SinhVien> students=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase=new MyDatabase(this);
        initView();
        initData();

    }

    private void initData() {
//        students.add(new SinhVien("Biology", "Android", 2, 5));
//        students.add(new SinhVien("History", "Php", 5));
//        students.add(new SinhVien( "English", "Java", 9));
//        students.add(new SinhVien( "Geography", "Php", 3));
//        students.add(new SinhVien( "Chemistry", "Jsp", 4));
//        students.add(new SinhVien( "Informatic", "Ios", 6));
//        students.add(new SinhVien( "Math", "C#", 2));
//        students.add(new SinhVien( "Sport", "Android", 7));
//        students.add(new SinhVien("Biology", "Android", 4));
//        students.add(new SinhVien("History", "Php", 7));
//        students.add(new SinhVien( "English", "Java", 3));
//        students.add(new SinhVien( "Geography", "Php", 5));
//        students.add(new SinhVien( "Chemistry", "Jsp", 5));
//        students.add(new SinhVien( "Informatic", "Ios", 5));
//        students.add(new SinhVien( "Math", "C#", 3));
//        students.add(new SinhVien( "Sport", "Android", 2));
        students.clear();
        students.addAll(myDatabase.getData());
    }

    private void initView() {
        lvStudent = (ListView) findViewById(R.id.lvStudent);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fLoat);
        studentAdapter = new StudentAdapter(this, students);
        lvStudent.setAdapter(studentAdapter);
        lvStudent.setOnItemClickListener(this);
        lvStudent.setOnItemLongClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this, SuaSinhVien.class);
        edit=position;
        intent.putExtra(HO_TEN, students.get(position).getFullname());
        intent.putExtra(MON_HOC, students.get(position).getClasses());
        intent.putExtra(DIEM, students.get(position).getScore());
        intent.putExtra(ID, students.get(position).getId());
        startActivityForResult(intent, REQUEST_A);

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Confirm");
        alertDialog.setMessage("Are you sure you want delete this?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.delete(students.get(position).getId());
                initData();
                alertDialog.dismiss();
//                students.remove(position);
//                studentAdapter.notifyDataSetChanged();
            }
        });
        alertDialog.show();
        initData();
        studentAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_A) {
            if (resultCode==RESULT_OK) {
//                String ten = data.getStringExtra(HO_TEN);
//                String mon = data.getStringExtra(MON_HOC);
//                float diem = data.getFloatExtra(DIEM, 0);
//                students.get(edit).setFullname(ten);
//                students.get(edit).setClasses(mon);
//                students.get(edit).setScore(diem);

                initData();
                studentAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode==REQUEST_B) {
            if (resultCode==RESULT_OK) {
//                String t = data.getStringExtra(HO_TEN);
//                String m = data.getStringExtra(MON_HOC);
//                float d = data.getFloatExtra(DIEM, 0);
//                students.add(new SinhVien(t, m, d, 0));
                initData();
                studentAdapter.notifyDataSetChanged();
            }
        }


    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this, ThemSinhVien.class);
        startActivityForResult(intent, REQUEST_B);
    }
}
