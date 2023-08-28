package com.ibadhur.medicinetime.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.ibadhur.medicinetime.R;
import android.database.sqlite.SQLiteDatabase;
import com.ibadhur.medicinetime.data.source.local2.DBHelper;
import com.ibadhur.medicinetime.medicine.MedicineActivity;

import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {


    private EditText etNameDetail;
    private EditText etUserNameDetail;
    private EditText etPasswordDetail;
    private EditText etConfirmPasswordDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);


        etNameDetail = findViewById(R.id.etNameDetail);
        etUserNameDetail = findViewById(R.id.etUserNameDetail);
        etPasswordDetail = findViewById(R.id.etPasswordDetail);
        etConfirmPasswordDetail = findViewById(R.id.etConfirmPasswordDetail);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNameDetail.getText().toString().trim();
                String userName = etUserNameDetail.getText().toString().trim();
                String password = etPasswordDetail.getText().toString().trim();
                String confirmPassword = etConfirmPasswordDetail.getText().toString().trim();

                // Perform validation for name
                if (name.isEmpty()) {
                    etNameDetail.setError("Name is required");
                    return;
                }

                // Perform validation for username
                if (userName.isEmpty()) {
                    etUserNameDetail.setError("Username is required");
                    return;
                }

                // Perform validation for password
                if (password.isEmpty()) {
                    etPasswordDetail.setError("Password is required");
                    return;
                }

                if (password.length() < 8) {
                    etPasswordDetail.setError("Password should be at least 8 characters long");
                    return;
                }

                // Perform validation for confirm password
                if (!confirmPassword.equals(password)) {
                    etConfirmPasswordDetail.setError("Passwords do not match");
                    return;
                }

                // Proceed with sign-up logic
                DBHelper dbHelper = new DBHelper(SignUpActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Insert the user details into the database
                dbHelper.insertUser(name, userName, password);

                // Show a success message to the user
                Toast.makeText(SignUpActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();

                // Close the database connection
                db.close();

                Intent intent = new Intent(SignUpActivity.this, MedicineActivity.class);
                startActivity(intent);
            }
        });

    }
}
