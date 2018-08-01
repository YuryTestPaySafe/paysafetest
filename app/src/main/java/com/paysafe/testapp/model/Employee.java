package com.paysafe.testapp.model;

/**
 * @author Abhishek Vadnerkar
 */

public class Employee extends Person {
    private String email;

    public Employee(String name, String phoneNumber, String email) {
        super(name, phoneNumber);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
