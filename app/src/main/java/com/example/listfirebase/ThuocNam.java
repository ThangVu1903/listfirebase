package com.example.listfirebase;

import java.util.ArrayList;

public class ThuocNam {
    public String tenThuoc, tenKhoaHoc,tacDung, hinhAnh;

    public ThuocNam(String tenThuoc, String tenKhoaHoc,  String tacDung, String hinhAnh) {
        this.tenThuoc = tenThuoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.tacDung = tacDung;
        this.hinhAnh = hinhAnh;
    }

    public ThuocNam(List list, int subitem, ArrayList<ThuocNam> arr) {
    }
}
