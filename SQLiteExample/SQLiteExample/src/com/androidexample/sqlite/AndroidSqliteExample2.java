package com.androidexample.sqlite;


import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.androidexample.model.Book;
import com.androidexample.persistence.SQLiteDBHelper2;

public class AndroidSqliteExample2 extends ListActivity implements OnItemClickListener {
    SQLiteDBHelper2 db = new SQLiteDBHelper2(this);
    List list;
    ArrayAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        // drop this database if already exists
        db.onUpgrade(db.getWritableDatabase(), 1, 2);

        db.createBook(new Book("The Great Party", "F. Scott Fitzgerald"));
        db.createBook(new Book("Desiree", "Anne Marie Selinko"));
        db.createBook(new Book("Rebeca", "Defne Demourier"));
        db.createBook(new Book("Invisible Man", "Ralph Ellison"));
        db.createBook(new Book("Gone with the Wind", "Margaret Mitchell"));
        db.createBook(new Book("Pride and Prejudice", "Jane Austen"));
        db.createBook(new Book("Sense and Sensibility", "Jane Austen"));
        db.createBook(new Book("Mansfield Park", "Jane Austen"));
        db.createBook(new Book("The Color Purple", "Alice Walker"));
        db.createBook(new Book("Sandithon", "Jane Austine"));
        db.createBook(new Book("The waves", "Virginia Woolf"));
        db.createBook(new Book("Harry Potter", "J.K. Rollings"));
        db.createBook(new Book("War and Peace", "Leo Tolstoy"));

        // get all books
        list = db.getAllBooks();
        List listTitle = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, ((Book) list.get(i)).getTitle());
        }

        myAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }

    @Override
    public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
        // start BookActivity with extras the book id
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("book", ((Book) list.get(arg2)).getId());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get all books again, because something changed
        list = db.getAllBooks();

        List listTitle = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, ((Book)list.get(i)).getTitle());
        }

        myAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }
}