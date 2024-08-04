package com.example.jobsphere.model;

import java.util.Objects;

public class Payment {
    private String paymentID;
    private String paymentMethod;
    private String paymentDescription;
    private String userID;

    public Payment() {
    }

    public Payment(String paymentID, String paymentMethod, String paymentDescription, String userID) {
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.paymentDescription = paymentDescription;
        this.userID = userID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentID, payment.paymentID) && Objects.equals(paymentMethod, payment.paymentMethod) && Objects.equals(paymentDescription, payment.paymentDescription) && Objects.equals(userID, payment.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentID, paymentMethod, paymentDescription, userID);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID='" + paymentID + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDescription='" + paymentDescription + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
