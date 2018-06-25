package com.example.admin.bai_kiem_tra;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admin on 4/17/2017.
 */

public class StudentAdapter extends ArrayAdapter<SinhVien> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<SinhVien> students;
    public StudentAdapter(@NonNull Context context, @NonNull ArrayList<SinhVien> students) {
        super(context,android.R.layout.simple_list_item_1, students);
        this.students=students;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (v==null) {

            viewHolder=new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);

            v = inflater.inflate(R.layout.item_student,parent,false);
            viewHolder.tvName= (TextView) v.findViewById(R.id.tvName);
            viewHolder.tvFullName= (TextView) v.findViewById(R.id.tvFullName);
            viewHolder.tvM= (TextView) v.findViewById(R.id.tvM);
            viewHolder.tvScore= (TextView) v.findViewById(R.id.tvScore);
            v.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) v.getTag();
        }
        Random random=new Random();
        SinhVien sinhVien=students.get(position);
        String str = String.valueOf(sinhVien.getFullname().charAt(0));

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        viewHolder.tvName.setText(str);
        GradientDrawable shapeDrawable = (GradientDrawable ) viewHolder.tvName.getBackground().getCurrent();
        shapeDrawable.setColor(Color.rgb(r,g,b));
        viewHolder.tvFullName.setText(sinhVien.getFullname());
        viewHolder.tvM.setText(sinhVien.getClasses());
        viewHolder.tvScore.setText(sinhVien.getScore()+"");

        return v;
    }



    class ViewHolder {
        TextView tvName;
        TextView tvFullName;
        TextView tvM;
        TextView tvScore;
    }

}
