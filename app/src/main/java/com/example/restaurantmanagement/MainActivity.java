package com.example.restaurantmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.restaurantmanagement.TableManagement.TableActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ImageButton imgSignUp;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button btnDangNhap;
    FirebaseAuth mAuthen;
    private SharedPreferences preferences;
    private static final String PREFS_NAME = "PresFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuthen = FirebaseAuth.getInstance();
        AnhXa();
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        getPreferencesData();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email hoặc mật khẩu đã bị bỏ trống ", Toast.LENGTH_SHORT).show();
                } else
                    DangNhap();
            }
        });


        imgSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
    }

    private void getPreferencesData() {

        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (sp.contains("email")) {
            String u = sp.getString("email", "");
            mEmailField.setText(u.toString());
        }
        if (sp.contains("password")) {
            String p = sp.getString("password", "");
            mPasswordField.setText(p.toString());
        }
//            if(sp.contains("check")){
//                Boolean b = sp.getBoolean("check",false);
//                remember.setChecked(b);
//            }

    }

    private void AnhXa() {
        imgSignUp = findViewById(R.id.btnQuaDangKy);
        mEmailField = findViewById(R.id.edtEmailDangNhap);
        mPasswordField = findViewById(R.id.edtPasswordDangNhap);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }

    public void DangNhap() {
        final String email = mEmailField.getText().toString();
        final String password = mPasswordField.getText().toString();
        mAuthen.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, TableActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Toast.makeText(MainActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "onComplete: Failed=" + task.getException().getMessage());
                            String errors = task.getException().getMessage();
                            switch (errors) {
                                case "There is no user record corresponding to this identifier. The user may have been deleted.":
                                    Toast.makeText(MainActivity.this, "Địa chỉ email không chính xác", Toast.LENGTH_SHORT).show();
                                    break;
                                case "The password is invalid or the user does not have a password.":
                                    Toast.makeText(MainActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }
                });
    }
}
