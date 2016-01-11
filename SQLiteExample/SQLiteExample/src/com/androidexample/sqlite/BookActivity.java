package com.androidexample.sqlite;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidexample.model.Book;
import com.androidexample.persistence.SQLiteDBHelper2;

public class BookActivity extends Activity {
    TextView bookTitle;
    TextView authorName;
    Book selectedBook;
    SQLiteDBHelper2 db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);

        bookTitle = (TextView) findViewById(R.id.title);
        authorName = (TextView) findViewById(R.id.author);

        // get the intent that we have passed from AndroidDatabaseExample
        Intent intent = getIntent();
        int id = intent.getIntExtra("book", -1);

        // open the database of the application context
        db = new SQLiteDBHelper2(getApplicationContext());

        // read the book with "id" from the database
        selectedBook = db.readBook(id);

        initializeViews();
    }

    public void initializeViews() {
        bookTitle.setText(selectedBook.getTitle());
        authorName.setText(selectedBook.getAuthor());
    }

    public void update(View v) {
        Toast.makeText(getApplicationContext(), "This book is updated.", Toast.LENGTH_SHORT).show();
        selectedBook.setTitle(((EditText) findViewById(R.id.titleEdit)).getText().toString());
        selectedBook.setAuthor(((EditText) findViewById(R.id.authorEdit)).getText().toString());

        // update book with changes
        db.updateBook(selectedBook);
        finish();
    }

    public void delete(View v) {
        Toast.makeText(getApplicationContext(), "This book is deleted.", Toast.LENGTH_SHORT).show();

        // delete selected book
        db.deleteBook(selectedBook);
        finish();
    }
}