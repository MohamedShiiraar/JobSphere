package com.example.jobsphere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jobsphere.doa.JobImpl;
import com.example.jobsphere.model.Job;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobsphere.databinding.ActivityCreatePostBinding;

public class CreatePost extends AppCompatActivity {
    private EditText edtTitle;
    private EditText edtDescription;
    private EditText edtSalary;
    private EditText edtLocation;
    private Button btnPost;

    private JobImpl jobImpl;

    private long userId;
    private String authenticatedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);
        edtSalary = findViewById(R.id.edtSalary);
        edtLocation = findViewById(R.id.edtLocation);
        btnPost = findViewById(R.id.btnPostListing);

        jobImpl = new JobImpl(this);
        authenticatedUser = getIntent().getStringExtra("authenticatedUser");
        userId = getIntent().getLongExtra("userId", -999);


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobTitle = edtTitle.getText().toString();
                String jobDescription = edtDescription.getText().toString();
                String jobSalary = "R" + edtSalary.getText().toString() + " Per a hour";
                String jobLocation = edtLocation.getText().toString();

                Job jobs = new Job();
                jobs.setTitle(jobTitle);
                jobs.setDescription(jobDescription);
                jobs.setSalary(Double.parseDouble(jobSalary));
                jobs.setLocation(jobLocation);

                JobImpl jobDAO = new JobImpl(CreatePost.this);
                //User user = new User();
                if (jobDAO.addJob(jobs,authenticatedUser)) {
                    openHomeActivity();
                }

            }
        });
    }
    public void openHomeActivity() {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
        finish();

    }
    private void moveToIntent(Intent intent) {
        intent.putExtra("authenticatedUser", authenticatedUser);
        startActivity(intent);
    }

    }