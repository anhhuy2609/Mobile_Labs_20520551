package com.example.lab2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int idLayout;
    ArrayList<NhanVien> myList;


    public MyArrayAdapter(Activity context, int idLayout, ArrayList<NhanVien> myList) {
        super(context, idLayout, myList);
        this.context = context;
        this.idLayout = idLayout;
        this.myList = myList;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myflacter = context.getLayoutInflater();
        convertView = myflacter.inflate(idLayout, null);

        NhanVien nv = myList.get(position);
        TextView hoTen = convertView.findViewById(R.id.hoTen);
        hoTen.setText(nv.getHoten());
        TextView luongNet = convertView.findViewById(R.id.luongNet);
        if(nv.getLuong()-nv.getLuong()*0.105 <= 11000000){
            luongNet.setText(String.valueOf(Math.round(nv.getLuong()-nv.getLuong()*0.105)));
        }
        else {
            luongNet.setText(String.valueOf(11000000+Math.round(((nv.getLuong() - nv.getLuong()*0.105)-11000000)*0.95)));
        }
        return convertView;
    }
}
