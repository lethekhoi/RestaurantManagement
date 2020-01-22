package com.example.restaurantmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    Button mBtnDangKi;
    ImageButton imgBack;
    EditText mEmailDangKi, mPassDangKi, mRetypePass;
    FirebaseAuth mAuthen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuthen = FirebaseAuth.getInstance();
        AnhXa();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        mBtnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailDangKi.getText().toString();
                String password = mPassDangKi.getText().toString();
                String retype = mRetypePass.getText().toString();


                if (email.isEmpty() || password.isEmpty() || retype.isEmpty()) {
                    Toast.makeText(SignUp.this, "Email hoặc mật khẩu bị bỏ trống", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(retype)) {
                    Toast.makeText(SignUp.this, "Xác nhận mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();

                } else {
                    DangKi();
                }
            }
        });

    }

    private void DangKi() {
        String email = mEmailDangKi.getText().toString();
        String password = mPassDangKi.getText().toString();
        mAuthen.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUp.this, "Thành Công", Toast.LENGTH_SHORT).show();

                        } else {
// If sign in fails, display a message to the user.
                            //Log.d("TAG", "onComplete: Failed=" + task.getException().getMessage());
                            String errors = task.getException().getMessage();
                            switch (errors) {
                                case "The email address is already in use by another account.":
                                    Toast.makeText(SignUp.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                    break;
                                case "The email address is badly formatted.":
                                    Toast.makeText(SignUp.this, "Địa chỉ email không đúng định dạng", Toast.LENGTH_SHORT).show();
                                    break;
                                case "The given password is invalid.[Password should be at least 6 characters]":
                                    Toast.makeText(SignUp.this, "Mật khẩu có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        }
                    }
                });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.backBtn);
        mEmailDangKi = findViewById(R.id.edtEmailDangKy);
        mPassDangKi = findViewById(R.id.edtPasswordDangKy);
        mRetypePass = findViewById(R.id.edtRetypePass);
        mBtnDangKi = findViewById(R.id.btnDangKy);
    }
}
