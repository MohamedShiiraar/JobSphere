package com.example.jobsphere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobsphere.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    private Button btnCreateJob;
    private String authenticatedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        btnCreateJob=findViewById(R.id.btnCreateJob);
        authenticatedUser = getIntent().getStringExtra("authenticatedUser");

        btnCreateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddJob();
            }
        });


    }
    public void openAddJob() {
        Intent intent = new Intent(this, CreatePost.class);
        intent.putExtra("authenticatedUser",authenticatedUser);
        startActivity(intent);
    }

    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }


}