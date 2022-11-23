package com.example.listfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://oderfood-7acec-default-rtdb.firebaseio.com");
    Button clear, btnRegister;
    EditText phoneTxT, userSU, passSU, birthSU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        clear = findViewById(R.id.btnclear);
        btnRegister = findViewById(R.id.btnRegister);
        phoneTxT = findViewById(R.id.PhoneSignUp);
        userSU = findViewById(R.id.usernameSignUp);
        passSU = findViewById(R.id.passWordSignUp);
        birthSU = findViewById(R.id.birthSignUp);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneTxT.setText("");
                userSU.setText("");
                passSU.setText("");
                birthSU.setText("");

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = userSU.getText().toString();
                final String phone = phoneTxT.getText().toString();
                final String pass = passSU.getText().toString();
                final String birth = birthSU.getText().toString();
                if (userName.isEmpty() || phone.isEmpty() || pass.isEmpty() || birth.isEmpty()) {
                    Toast.makeText(SignUp.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phone)) {
                                Toast.makeText(SignUp.this, "Phone is already register !! ", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("user").child(phone).child("userName").setValue(userName);
                                databaseReference.child("user").child(phone).child("password").setValue(pass);
                                databaseReference.child("user").child(phone).child("email").setValue(birth);
                                Toast.makeText(SignUp.this, "user register successfully", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                startActivity(new Intent(SignUp.this,Login.class));
            }

        });


    }
}