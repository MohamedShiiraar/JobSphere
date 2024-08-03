package com.example.jobsphere.model;

public class User {

        private String Title;
        private String Company;
        private String Location;
        private double Salary;
        private String Description;


        public User(String Title, String Company, String Location, double Salary, String Description) {
            this.Title = Title;
            this.Company = Company;
            this.Location = Location;
            this.Salary = Salary;
            this.Description = Description;
        }


        public String displayDetails() {
            return String.format(
                    "Job Title: %s\nCompany: %s\nLocation: %s\nSalary: $%.2f\nDescription: %s",
                    Title, Company, Location, Salary, Description
            ) ;

        }


        public String updateSalary(double newSalary) {
            double oldSalary = this.Salary;
            this.Salary = newSalary;
            return String.format("salary has been updated from $%.2f to $%.2f.", oldSalary, newSalary);
        }


        @Override
        public String toString() {
            return String.format("%s at %s in %s ($%.2f)", Title, Company, Location, Salary);
        }



        }



