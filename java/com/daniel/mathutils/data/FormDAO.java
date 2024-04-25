package com.daniel.mathutils.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.daniel.mathutils.datamodels.Form;

import java.util.ArrayList;
import java.util.List;

public class FormDAO {

    private final SQLiteDatabase database;

    public FormDAO(SQLiteDatabase database) {
        this.database = database;
    }

    public long insert(Form form) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", form.getName());
        contentValues.put("expression", form.getExpresions());
        contentValues.put("created", form.getCreated());
        return database.insert("forms", null, contentValues);

    }

    public List<Form> getAll() {
        List<Form> forms = new ArrayList<>();

        String query = "SELECT * FROM forms";
        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String expression = cursor.getString(cursor.getColumnIndexOrThrow("expression"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("created"));

            Form formula = new Form(name, expression);
            formula.setCreated(date);
            forms.add(formula);
        }
        cursor.close();
        return forms;
    }
}
