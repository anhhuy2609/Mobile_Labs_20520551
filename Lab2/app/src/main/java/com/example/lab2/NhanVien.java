package com.example.lab2;

public class NhanVien {
    private String hoten;
    private double luong;

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
    //

    public NhanVien(String hoten, double luong) {
        this.hoten = hoten;
        this.luong = luong;
    }
}
