package com.paysafe.testapp.network;

import com.paysafe.testapp.model.Customer;
import com.paysafe.testapp.model.Employee;

import android.support.annotation.Nullable;

/**
 * @author Abhishek Vadnerkar
 */

public class ApiCaller {

    @Nullable
    public static Customer[] getCustomers() {
        try {
            //Simulates a delayed response from server
            Thread.sleep(2000);
            return new Customer[] {
                    new Customer("Diane", "5140000000"),
                    new Customer("Adam", "5141111111"),
                    new Customer("Bob", "5142222222")
            };
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Nullable
    public static Employee[] getEmployees() {
        //Simulates a delayed response from server
        try {
            Thread.sleep(2000);
            return new Employee[] {
                    new Employee("Edward", "5143333333", "x@y.com"),
                    new Employee("Catherine", "5144444444", "a@b.com")
            };
        } catch (InterruptedException e) {
            return null;
        }
    }
}
