package com.example.contact_komalsanhotra_c0811169__android;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class contactAdapter extends ArrayAdapter {
    Context context;
    int layoutRes;
    List<Contact> contactList;
    SQLiteDatabase sqLiteDatabase;

    public contactAdapter(@NonNull Context context, int resource, List<Contact> contacts) {
        super(context, resource, contacts);

        this.context = context;
        this.layoutRes = resource;
        this.contactList = contacts;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutRes, null);
        TextView fnameTV = v.findViewById(R.id.fname_tv);
        TextView lnameTV = v.findViewById(R.id.lname_tv);
        TextView emailTV = v.findViewById(R.id.email_tv);
        TextView phonenumberTV = v.findViewById(R.id.phonenumber_tv);
        TextView addressTV = v.findViewById(R.id.address_tv);

        final Contact contact = contactList.get(position);

        fnameTV.setText(contact.getFname());
        lnameTV.setText(contact.getlName());
        emailTV.setText(contact.getEmail());
        phonenumberTV.setText(contact.getPnumber());
        addressTV.setText(contact.getAddress());

        v.findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateContact(contact);
            }
            private void UpdateContact(final Contact contact) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view = layoutInflater.inflate(R.layout.update_contact, null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        return v;
    }
    @Override
    public int getCount() {
        return contactList.size();
    }
}
