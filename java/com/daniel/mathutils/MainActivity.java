package com.daniel.mathutils;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daniel.mathutils.connection.DatabaseHelper;
import com.daniel.mathutils.data.FormDAO;
import com.daniel.mathutils.datamodels.Form;
import com.daniel.mathutils.screens.CreateFormActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private List<Form> forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHelper helper = new DatabaseHelper(this);
        this.database = helper.getWritableDatabase();

        FormDAO dao = new FormDAO(database);

         forms = dao.getAll();

        LinearLayout layout = findViewById(R.id.layout_container);

         for (Form form : forms) {

             View formView = getLayoutInflater().inflate(R.layout.layout_item, null);

             TextView textView = formView.findViewById(R.id.textViewName);
             textView.setText(form.getName());

             TextView textView1 = formView.findViewById(R.id.textViewFormula);
             textView1.setText(form.getExpresions());

             TextView textView2 = formView.findViewById(R.id.textViewDate);
             textView2.setText("Criado: " + form.getCreated());

             LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                     LinearLayout.LayoutParams.MATCH_PARENT,
                     LinearLayout.LayoutParams.WRAP_CONTENT
             );
             layoutParams.setMargins(0, 0, 0, 20);
             formView.setLayoutParams(layoutParams);

             layout.addView(formView);

         }


        FloatingActionButton button = findViewById(R.id.addForm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateFormActivity.class);
                startActivity(intent);
            }
        });
    }
    

}