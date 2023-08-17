package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DB.Database;
import com.example.myapplication.UI.ItemTrangChuDocHieuActivity;
import com.example.myapplication.UI.TaiKhoanFragment;

public class Login extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;

    Database db;
    private String maTK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        btnLogin = findViewById(R.id.btnLogin);
        db = new Database(Login.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim().toLowerCase();
                String password = edtPassword.getText().toString().trim().toLowerCase();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Cursor cursor = db.query_hasresult("SELECT * FROM TaiKhoan");
                        maTK = "";
                        if (cursor.getCount() != 0) {
                            while (cursor.moveToNext()) {
                                String ma = cursor.getString(0);
                                String pass = cursor.getString(1);
                                maTK = ma;
                                if (username.equals(ma) && password.equals(pass)) {
                                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtra("maTK", maTK);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                            }
                        } else {
                            maTK = "Không có dữ liệu";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

}