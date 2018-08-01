package com.paysafe.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paysafe.test.R;
import com.paysafe.test.adapter.PersonAdapter;
import com.paysafe.test.model.Customer;
import com.paysafe.test.model.Employee;
import com.paysafe.test.model.Person;
import com.paysafe.test.network.ApiCaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {
    @BindView(R.id.rvPersons)
    RecyclerView rvPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        final List<Person> persons = new ArrayList<>();
        final Customer[] customers;
        final Employee[] employees;

        customers = ApiCaller.getCustomers();
        employees = ApiCaller.getEmployees();
        persons.addAll(Arrays.asList(customers));
        persons.addAll(Arrays.asList(employees));
        setupList(persons);
    }

    private void setupList(final List<Person> persons) {
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        rvPersons.setAdapter(new PersonAdapter(this, persons));
    }
}
