package com.example.contact_komalsanhotra_c0811169__android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    ListView contactListView;
    List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactListView = findViewById(R.id.contactlistview);
        contactList = new ArrayList<>();

        sqLiteDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE, null);

        loadContact();
    }

    private void loadContact() {
        String sql = "SELECT * FROM contact";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                //getting and adding contacts
                contactList.add(new Contact(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));



            }while (cursor.moveToNext());
            cursor.close();
        }
        // create an adapter to display the employees
        contactAdapter contactAdapter = new contactAdapter(this,R.layout.contact_list,contactList);
        contactListView.setAdapter(contactAdapter);
    }
}