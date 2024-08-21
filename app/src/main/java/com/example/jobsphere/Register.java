package com.example.jobsphere;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    private EditText editTextUserID;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnRegister;

    //private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize views
        editTextUserID = findViewById(R.id.editTextUserID);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set OnClickListener for the register button
        btnRegister.setOnClickListener(v -> registerUser());

        // Apply window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void registerUser() {
        // Get user input
        String userID = editTextUserID.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate input
        if (userID.isEmpty() || name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user data into the database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userID", userID);
        values.put("name", name);
        values.put("surname", surname);
        values.put("email", email);
        values.put("password", password);
        values.put("worker", false); // Assuming default worker value is false

        long newRowId = db.insert("users", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_LONG).show();
            clearInputFields();
        } else {
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void clearInputFields() {
        editTextUserID.setText("");
        editTextName.setText("");
        editTextSurname.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
    }
}
