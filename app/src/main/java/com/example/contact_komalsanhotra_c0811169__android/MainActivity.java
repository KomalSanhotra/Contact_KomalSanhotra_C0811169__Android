package com.example.contact_komalsanhotra_c0811169__android;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Name of Database
    public static final String DATABASE_NAME = "contact_db";

    //instance of database of sql
    SQLiteDatabase sqLiteDatabase;


    EditText FName_et, LName_et, EMail_et, Number_et, Address_et;

    Button add_btn, Show_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //getting EditText
        FName_et = findViewById(R.id.fname);
        LName_et = findViewById(R.id.lname);
        EMail_et = findViewById(R.id.email);
        Number_et = findViewById(R.id.phoneNumber);
        Address_et = findViewById(R.id.Address);

        //Buttons
        findViewById(R.id.add_btn).setOnClickListener(this);
        findViewById(R.id.show_btn).setOnClickListener(this);


        //initialize the sqlite db
        sqLiteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();

    }

    private void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS contact (" +
                "id INTEGER NOT NULL CONSTRAINT contact_pk PRIMARY KEY AUTOINCREMENT," +
                "FirstName VARCHAR(25) NOT NULL, " +
                "LastName VARCHAR(25) NOT NULL, " +
                "Email VARCHAR (25) NOT NULL, " +
                "PhoneNumber Int NOT NULL, " +
                "Address VARCHAR(255) NOT NULL, " +
                "Contact_Date DATETIME NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_btn:
                //Add Contact
                addContact();
                break;
            case R.id.show_btn:
                //Navigate to another activity
                startActivity(new Intent(this, ContactActivity.class));
                break;


        }
    }

    private void addContact() {
        String firstname = FName_et.getText().toString().trim();
        String lastname = LName_et.getText().toString().trim();
        String email = EMail_et.getText().toString().trim();
        String phonenumber = Number_et.getText().toString().trim();
        String address = Address_et.getText().toString().trim();

        // getting the current time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm:ss", Locale.CANADA);
        String contactDate = sdf.format(cal.getTime());

        if (firstname.isEmpty()) {
            FName_et.setError("First name field cannot be empty");
            FName_et.requestFocus();
            return;
        }

        if (lastname.isEmpty()) {
            LName_et.setError("Last name cannot be empty");
            LName_et.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            EMail_et.setError("Email field cannot be empty");
            EMail_et.requestFocus();
            return;
        }

        if (phonenumber.isEmpty()) {
            Number_et.setError("Phone Number cannot be empty");
            Number_et.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            Address_et.setError("Address cannot be empty");
            Address_et.requestFocus();
            return;
        }

        String sql = "INSERT INTO contact (FirstName,LastName,Email,PhoneNumber,Address,Contact_Date)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        sqLiteDatabase.execSQL(sql, new String[]{firstname, lastname, email, phonenumber, address, contactDate});

        clearFields();

    }

    private void clearFields() {

        FName_et.setText("");
        LName_et.setText("");
        EMail_et.setText("");
        Number_et.setText("");
        Address_et.setText("");
        //clear focus
        FName_et.clearFocus();
        LName_et.clearFocus();
        EMail_et.clearFocus();
        Number_et.clearFocus();
        Address_et.clearFocus();

    }
}