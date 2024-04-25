package com.daniel.mathutils.datamodels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Form {

    private String name;
    private String expresions;
    private String created;

    public Form(String name, String expresions) {
        this.name = name;
        this.expresions = expresions;
        this.created = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public String getExpresions() {
        return expresions;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }
}
