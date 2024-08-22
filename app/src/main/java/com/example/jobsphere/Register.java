package com.example.jobsphere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jobsphere.doa.UserImpl;
import com.example.jobsphere.model.User;

public class Register extends AppCompatActivity {
    private EditText edtName,edtSurname,edtEmail,edtPassword;
    private CheckBox chkboxWorker;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        chkboxWorker = findViewById(R.id.chkboxWorker);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String surname = edtSurname.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                boolean worker = false;
                if (chkboxWorker.isSelected()) {
                    worker = true;
                }
                User user = new User();
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPassword(password);
                user.setWorker(worker);
                UserImpl userDao = new UserImpl(Register.this);
                if (userDao.register(user))
                openLoginActivity();

            }
        });
    }

    public void openLoginActivity () {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); //prevents coming back to the screen.
    }
}