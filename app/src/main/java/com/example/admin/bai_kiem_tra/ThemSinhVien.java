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

public class ThemSinhVien extends Activity implements View.OnClickListener {
    private EditText edtTen;
    private EditText edtMon;
    private EditText edtDiem;
    private TextView tvThem;
    private TextView tvHuyBo;
    private Animation animation;
    private MyDatabase myDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_sinhvien);
        myDatabase=new MyDatabase(this);
        initView();
        initAnimation();
    }

    private void initAnimation() {
        animation= AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
    }

    private void initView() {
        edtTen= (EditText) findViewById(R.id.edtTen);
        edtMon= (EditText) findViewById(R.id.edtMon);
        edtDiem= (EditText) findViewById(R.id.edtDiem);
        tvThem= (TextView) findViewById(R.id.tvThem);
        tvHuyBo= (TextView) findViewById(R.id.tvHuyBo);
        tvThem.setOnClickListener(this);
        tvHuyBo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvThem:
                tvThem.startAnimation(animation);
                String ten=edtTen.getText().toString();
                String lop=edtMon.getText().toString();
                String diemso= edtDiem.getText().toString();
                int id = 0;
                if (ten.isEmpty()||lop.isEmpty()||(diemso).isEmpty()) {
                    Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Intent data=new Intent();
//                data.putExtra(MainActivity.HO_TEN, ten);
//                data.putExtra(MainActivity.MON_HOC, lop);
//                data.putExtra(MainActivity.DIEM, Float.parseFloat(diemso));
//                setResult(RESULT_OK, data);
//                finish();
                SinhVien sinhVien=new SinhVien(ten, lop, Float.parseFloat(diemso), id);
                long newRow=myDatabase.insert(sinhVien);
                if (newRow>-1) {
                    setResult(RESULT_OK);
                    finish();
                }else {
                    Toast.makeText(this, "Insert student", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvHuyBo:
                tvHuyBo.startAnimation(animation);
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
