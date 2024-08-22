package com.example.jobsphere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jobsphere.doa.UserImpl;

public class Login extends AppCompatActivity {

    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;
    private Button btnLogin;
    private UserImpl userImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        userImpl = new UserImpl(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();
                if (isValidEmail(email) && isValidPassword(password)) {
                    // Proceed to the next activity or main screen
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    openHome();
                } else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openHome() {
        String email = editTextTextEmailAddress.getText().toString().trim();
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("authenticatedUser", email);
        startActivity(intent);
    }

    public boolean isValidEmail(String email) {
        String regexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regexPattern);
    }

    public boolean isValidPassword(String password) {
        return password.length() >= 8;
    }
}

