package com.daniel.mathutils.screens;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daniel.mathutils.MainActivity;
import com.daniel.mathutils.R;
import com.daniel.mathutils.connection.DatabaseHelper;
import com.daniel.mathutils.data.FormDAO;
import com.daniel.mathutils.datamodels.Form;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateFormActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private FormDAO formDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_forms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHelper helper = new DatabaseHelper(this);
        this.database = helper.getWritableDatabase();

        formDAO = new FormDAO(database);

        FloatingActionButton button = findViewById(R.id.buttonDone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inputName = findViewById(R.id.inputName);
                EditText inputExpressions = findViewById(R.id.inputExpressions);

                String name = inputName.getText().toString();
                String expression = inputExpressions.getText().toString();

                Form form = new Form(name, expression);

                long formID = formDAO.insert(form);

                if (formID != -1) {
                    Intent intent = new Intent(CreateFormActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                };
            }
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateFormActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button next = findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateFormActivity.this, CreateVariables.class);
                startActivity(intent);
            }
        });

    }
}