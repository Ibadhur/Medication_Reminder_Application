package com.ibadhur.medicinetime.authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ibadhur.medicinetime.R;

import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etUserNameReset,etPasswordReset, etConfirmPasswordReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        etUserNameReset = findViewById(R.id.etUserNameReset);
        etPasswordReset = findViewById(R.id.etPasswordReset);
        etConfirmPasswordReset = findViewById(R.id.etConfirmPasswordReset);

        Button btnPasswordReset = findViewById(R.id.btnPasswordReset);
        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    // Passwords are valid, proceed with password reset
                }
            }
        });

    }

    private boolean validate() {
        boolean valid = true;

        String username = etUserNameReset.getText().toString();
        String password = etPasswordReset.getText().toString();
        String confirmPassword = etConfirmPasswordReset.getText().toString();


        if (username.isEmpty()) {
            etUserNameReset.setError("User name cannot be empty");
            valid = false;
        }
        if (password.isEmpty()) {
            etPasswordReset.setError("Password field cannot be empty");
            valid = false;
        } else if (password.length() < 8) {
            etPasswordReset.setError("Password must be at least 8 characters long");
            valid = false;
        }

        if (confirmPassword.isEmpty()) {
            etConfirmPasswordReset.setError("Confirm password field cannot be empty");
            valid = false;
        } else if (!password.equals(confirmPassword)) {
            etConfirmPasswordReset.setError("Passwords do not match");
            valid = false;
        }

        return valid;
    }
}
