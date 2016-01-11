package com.androidexample.sqlite;

/**
 * Created by SHEKOOFEH on 10/18/2015.
 */


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.androidexample.model.Student;
import com.androidexample.persistence.DataBaseHelper;

public class StudentOperations {

    // Database fields
    private DataBaseHelper dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = {DataBaseHelper.STUDENT_ID, DataBaseHelper.STUDENT_NAME};
    private SQLiteDatabase database;

    public StudentOperations(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Student addStudent(String name) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.STUDENT_NAME, name);

        long studId = database.insert(DataBaseHelper.STUDENTS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseHelper.STUDENTS,
                STUDENT_TABLE_COLUMNS, DataBaseHelper.STUDENT_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        Student newComment = parseStudent(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteStudent(Student comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseHelper.STUDENTS, DataBaseHelper.STUDENT_ID
                + " = " + id, null);
    }

    public List getAllStudents() {
        List students = new ArrayList();

        Cursor cursor = database.query(DataBaseHelper.STUDENTS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }

    private Student parseStudent(Cursor cursor) {
        Student student = new Student();
        student.setId((cursor.getInt(0)));
        student.setName(cursor.getString(1));
        return student;
    }
}