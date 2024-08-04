package com.example.jobsphere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginorRegister extends AppCompatActivity {

    private Button btnRegisterMain;
    private Button btnLoginMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginor_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        btnLoginMain = findViewById(R.id.btnLoginMain);
        btnRegisterMain = findViewById(R.id.btnRegisterMain);

        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        btnRegisterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

    }
    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    public void openRegister() {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

}