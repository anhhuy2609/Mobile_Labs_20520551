package com.example.lab4;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scottyab.aescrypt.AESCrypt;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtfullname, edtusername, edtsdt, edtpassword;
    private Button btn_Register;
    private TextView dangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtfullname = findViewById(R.id.regis_fullname);
        edtusername = findViewById(R.id.regis_username);
        edtsdt = findViewById(R.id.regis_sdt);
        edtpassword = findViewById(R.id.regis_password);
        dangnhap = findViewById(R.id.txt_dangnhap);
        btn_Register = findViewById(R.id.btn_register);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = edtfullname.getText().toString();
                String username = edtusername.getText().toString();
                String sdt = edtsdt.getText().toString();
                String password = edtpassword.getText().toString();

                Map<String, Object> user = new HashMap<>();
                user.put("Fullname", fullname);
                user.put("Username", username);
                user.put("SDT", sdt);
                try {
                    user.put("Password", AESCrypt.encrypt("encryptpassword", password));
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }

                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent switchActivityIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(switchActivityIntent);
                                finishAffinity();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(switchActivityIntent);
                finishAffinity();
            }
        });
    }
}