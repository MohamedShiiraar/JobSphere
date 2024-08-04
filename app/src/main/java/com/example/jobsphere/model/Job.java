package com.example.jobsphere.model;

public class Job
{
    private int jobID;
    private String title;
    private String description;
    private double costperhour;
    private String location;

    // Default constructor
    public Job() {}

    // Parameterized constructor
    public Job(int jobID, String title, String description, double costperhour, String location)
    {
        this.jobID = jobID;
        this.title = title;
        this.description = description;
        this.costperhour = costperhour;
        this.location = location;
    }

    // Getters and Setters
    public int getJobID()
    {
        return jobID;
    }

    public void setJobID(int jobID)
    {
        this.jobID = jobID;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getSalary()
    {
        return costperhour;
    }

    public void setSalary(double salary)
    {
        this.costperhour = salary;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "Job{" +
                "jobID=" + jobID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + costperhour +
                ", location='" + location + '\'' +
                '}';
    }
}
