package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<NhanVien> myList;
    EditText txtHoten;
    EditText txtLuong;
    MyArrayAdapter myAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoten = findViewById(R.id.txtHoten);
        txtLuong = findViewById(R.id.txtLuong);
        Button btn_TinhLuong = findViewById(R.id.btn_Tinh);
        listView = findViewById(R.id.listView);
        myList = new ArrayList<>();
//        myList.add(new NhanVien("HUY", 10));
//        myList.add(new NhanVien("DUC", 10));
//        myAdapter = new MyArrayAdapter(MainActivity.this,R.layout.layout_person, myList);
//        listView.setAdapter(myAdapter);
        //Xử lý button
        btn_TinhLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myList.add(new NhanVien(txtHoten.getText().toString(), Double.parseDouble(txtLuong.getText().toString())));
                myAdapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_person, myList);
                listView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
        });

    }
}