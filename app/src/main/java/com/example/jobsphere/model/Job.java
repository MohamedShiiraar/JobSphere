package com.example.jobsphere.model;

public class Job
{
    private int jobID;
    private String title;
    private String description;
    private double salary;
    private String location;

    // Default constructor
    public Job() {}

    // Parameterized constructor
    public Job(int jobID, String title, String description, double salary, String location)
    {
        this.jobID = jobID;
        this.title = title;
        this.description = description;
        this.salary = salary;
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
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
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
                ", salary=" + salary +
                ", location='" + location + '\'' +
                '}';
    }
}
