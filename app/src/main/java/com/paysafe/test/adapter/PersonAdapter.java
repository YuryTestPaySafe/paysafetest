package com.paysafe.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paysafe.test.R;
import com.paysafe.test.model.Customer;
import com.paysafe.test.model.Employee;
import com.paysafe.test.model.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CUSTOMER = 0;
    private static final int TYPE_EMPLOYEE = 1;
    private Context context;
    private final List<Person> persons;


    public PersonAdapter(final Context context, final List<Person> persons) {
        this.context = context;
        this.persons = persons;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == TYPE_EMPLOYEE) {
            final View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent,false);

            return new EmployeeViewHolder(layoutView);
        } else {
            final View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);

            return new CustomerViewHolder(layoutView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder h, final int position) {
        if (h instanceof EmployeeViewHolder) {
            final EmployeeViewHolder holder = (EmployeeViewHolder) h;
            final Employee employee = (Employee) persons.get(position);

            holder.txtName.setText(context.getString(R.string.employee_name, employee.getName()));
            holder.txtEmail.setText(employee.getEmail());
            holder.txtPhone.setText(employee.getPhoneNumber());
        } else {
            final CustomerViewHolder holder = (CustomerViewHolder) h;
            final Customer customer = (Customer) persons.get(position);

            holder.txtName.setText(customer.getName());
            holder.txtPhone.setText(customer.getPhoneNumber());
        }
    }

    @Override
    public int getItemViewType(final int position) {
        if (persons.get(position) instanceof Employee)
            return TYPE_EMPLOYEE;

        return TYPE_CUSTOMER;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtEmail)
        TextView txtEmail;

        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtPhone)
        TextView txtPhone;

        public EmployeeViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtPhone)
        TextView txtPhone;

        public CustomerViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
