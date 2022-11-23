package com.example.listfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ListView lsvDish;
    ArrayList<ThuocNam> arr;

    adapter adapter;
    DatabaseReference databaseReference;
    FloatingActionButton createTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        createTxt = findViewById(R.id.createDish);

        createTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List.this, CreateDish.class));
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("thuoc");
        lsvDish = findViewById(R.id.lsvDish);

        arr = new ArrayList<>();

        adapter = new adapter(this, R.layout.subitem, arr);
        lsvDish.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ThuocNam d = snapshot.getValue(ThuocNam.class);
                arr.add(d);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lsvDish.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xoa(i);
                return false;
            }
        });

     //   lsvDish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent =  new Intent(this, DetailListItem.class);
//                intent.putExtra("dish",arr.get(i));
//                startActivity(intent);
//            }
//        });





    }
    public void xoa(int pos){

        AlertDialog.Builder alert  =new AlertDialog.Builder(this);
        alert.setTitle("Thông Báo!! ");
        alert.setMessage("Bạn có muốn xóa không ?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseReference.child("monan").child(arr.get(pos).hinhAnh).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(List.this, "remove", Toast.LENGTH_SHORT)
                                        .show();
                                arr.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(List.this,"Remove fail",Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });
        alert.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();


    }
}