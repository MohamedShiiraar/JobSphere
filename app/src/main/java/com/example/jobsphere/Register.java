import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsCompat.Insets;

public class Register extends AppCompatActivity {

    private EditText editTextUserID;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Find views by ID
        editTextUserID = findViewById(R.id.editTextUserID);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Apply window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set click listener for the register button
        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        // Get user input from EditText fields
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

        // Create a new User object
        User newUser = new User(userID, name, surname, email, password, false);

        // For now, we simply display the user information
        // In a real app, you'd save the user data to a database or send it to a server
        Toast.makeText(this, "User Registered: " + newUser.toString(), Toast.LENGTH_LONG).show();

        // Optionally, clear the input fields
        clearInputFields();
    }

    private void clearInputFields() {
        editTextUserID.setText("");
        editTextName.setText("");
        editTextSurname.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
    }
}
