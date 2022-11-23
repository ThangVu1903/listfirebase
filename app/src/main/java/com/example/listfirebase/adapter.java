package com.example.listfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapter extends BaseAdapter {
    private Context ct;
    private int layout;
    private ArrayList<ThuocNam> arr;

    public adapter(Context ct, int layout, ArrayList<ThuocNam> arr) {
        this.ct = ct;
        this.layout = layout;
        this.arr = arr;
    }

    @Override
    public int getCount() {
       return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater a = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = a.inflate(layout,null );
        }else {

        }
        if(arr.size()>0) {
            ThuocNam d = arr.get(i);
            ImageView imageDish = view.findViewById(R.id.imgDish);
            TextView txvNameDish = view.findViewById(R.id.txvNameDish);
            TextView txvNumItem = view.findViewById(R.id.txvNumItem);
            TextView txtCondung = view.findViewById(R.id.txtCongdung);

            Glide.with(ct).load(d.hinhAnh).into(imageDish);
            txvNameDish.setText(d.tenThuoc);
            txvNumItem.setText(d.tenKhoaHoc);
            txtCondung.setText(d.tacDung);
        }
        return  view;
    }
}
