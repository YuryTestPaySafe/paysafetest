package com.paysafe.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.paysafe.test.R;
import com.paysafe.test.adapter.PersonAdapter;
import com.paysafe.test.model.Customer;
import com.paysafe.test.model.Employee;
import com.paysafe.test.model.Person;
import com.paysafe.test.network.ApiCaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {
    private final static int BACK_PRESS_DELAY = 5 * 1000;

    @BindView(R.id.rvPersons)
    RecyclerView rvPersons;

    private long back_pressed;

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

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1 == null && o2 != null)
                    return -1;

                if (o1 != null && o2 == null)
                    return 1;

                if (o1 == null && o2 == null)
                    return 0;

                if (o1.getName() == null && o2.getName() != null)
                    return -1;

                if (o1.getName() != null && o2.getName() == null)
                    return 1;

                if (o1.getName() == null && o2.getName() == null)
                    return 0;

                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        setupList(persons);
    }

    private void setupList(final List<Person> persons) {
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        rvPersons.setAdapter(new PersonAdapter(this, persons));
    }


    @Override
    public void onBackPressed() {
        if (back_pressed + BACK_PRESS_DELAY > System.currentTimeMillis())
            finish();
        else
            Toast.makeText(getBaseContext(), getString(R.string.infoActivity_confirmation), Toast.LENGTH_SHORT).show();

        back_pressed = System.currentTimeMillis();
    }
}
