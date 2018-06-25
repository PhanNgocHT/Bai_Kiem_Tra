package com.example.admin.bai_kiem_tra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin on 4/17/2017.
 */

public class SuaSinhVien extends Activity implements View.OnClickListener {

    private EditText hoten;
    private EditText monhoc;
    private EditText diem;
    private TextView tvCapNhat;
    private TextView tvHuy;
    private Animation animation;
    private MyDatabase myDatabase;
    private String ten, lop, diemso;
    private int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_sinhvien);
        myDatabase=new MyDatabase(this);
        initView();
        initAnimation();
    }

    private void initAnimation() {
        animation= AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
    }

    private void initView() {
        hoten= (EditText) findViewById(R.id.hoten);
        monhoc= (EditText) findViewById(R.id.monhoc);
        diem= (EditText) findViewById(R.id.diem);
        tvCapNhat= (TextView) findViewById(R.id.tvCapNhat);
        tvHuy= (TextView) findViewById(R.id.tvHuy);
        tvCapNhat.setOnClickListener(this);
        tvHuy.setOnClickListener(this);
        ten=getIntent().getStringExtra(MainActivity.HO_TEN);
        lop = getIntent().getStringExtra(MainActivity.MON_HOC);
        diemso = getIntent().getFloatExtra(MainActivity.DIEM, 0) + "";
        id = getIntent().getIntExtra(MainActivity.ID, 0);
        hoten.setText(ten);
        monhoc.setText(lop);
        diem.setText(diemso);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCapNhat:
                tvCapNhat.startAnimation(animation);
                String ten=hoten.getText().toString();
                String lop=monhoc.getText().toString();
                String diemso= diem.getText().toString();
                if (ten.isEmpty()||lop.isEmpty()||(diemso).isEmpty()) {
                    Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Intent data=new Intent();
//                data.putExtra(MainActivity.HO_TEN, ten);
//                data.putExtra(MainActivity.MON_HOC, lop);
//                data.putExtra(MainActivity.DIEM, Float.parseFloat(diemso));

//                setResult(RESULT_OK, data);

                SinhVien sinhVien=new SinhVien(ten, lop, Float.parseFloat(diemso), id);
                int rows=myDatabase.update(sinhVien);
                if (rows>0) {
                    setResult(RESULT_OK);
                    finish();
                }else {
                    Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvHuy:
                tvHuy.startAnimation(animation);
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
