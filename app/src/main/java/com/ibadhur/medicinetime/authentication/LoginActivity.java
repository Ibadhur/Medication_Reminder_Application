package com.ibadhur.medicinetime.authentication;


import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.ibadhur.medicinetime.data.source.local2.DBHelper;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import com.ibadhur.medicinetime.R;
import com.ibadhur.medicinetime.medicine.MedicineActivity;


import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        signUpPage();
        forgotPasswordPage();

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                if (userName.isEmpty()) {
                    etUserName.setError("Please enter your username");
                    etUserName.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    etPassword.setError("Please enter your password");
                    etPassword.requestFocus();
                    return;
                }

                if (password.length() < 8) {
                    etPassword.setError("Password should be at least 8 characters long");
                    return;
                }

                // Open a connection to the database
                DBHelper dbHelper = new DBHelper(LoginActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Query the database to check if the user exists
                Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{userName, password});
                if (cursor.moveToFirst()) {
                    // The user exists, perform login here
                    Toast.makeText(LoginActivity.this, "Welcome to Medicine Reminder app", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MedicineActivity.class);
                    startActivity(intent);
                } else {
                    // The user does not exist, show an error message
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

                // Close the cursor and the database connection
                cursor.close();
                db.close();
            }
        });
    }

    public void signUpPage() {
        Button signUpButton = findViewById(R.id.signUpPageButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void forgotPasswordPage() {
        Button PasswordResetButton = findViewById(R.id.forgotPassword);
        PasswordResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
}
