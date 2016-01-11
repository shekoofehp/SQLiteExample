package com.androidexample.sqlite;

import java.util.List;

import com.androidexample.model.UserData;
import com.androidexample.persistence.DBAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class AndroidSqliteExample extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/******************* Intialize Database *************/
		DBAdapter.init(this);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_sqlite_example);
		 // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        DBAdapter.addUserData(new UserData("Shubham", "9999999999"));
        DBAdapter.addUserData(new UserData("Deny", "8888888888"));
        DBAdapter.addUserData(new UserData("Shanker", "22222222222"));
        DBAdapter.addUserData(new UserData("Sam", "11111111111"));
 
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<UserData> data = DBAdapter.getAllUserData();      

        String log="";
        for (UserData dt : data) {
                log += "\nId: " + dt.getID() + " ,Name: " + dt.getName() + " ,Phone: " + dt.getEmail();
        }
                // Writing Contacts to log
        TextView records = (TextView) findViewById(R.id.records);
        records.setText(log);
        Log.d("Name: ", log);
	}
    public  void   goToBook(View v){
            Intent i= new Intent(this, AndroidSqliteExample2.class);
            startActivity(i);

    }

}
