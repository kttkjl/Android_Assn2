package com.example.jacky.assignment_2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ChildActivity extends AppCompatActivity {

    // Mode 0 if new child, mode 1 if update child
    private int mode;
    private Child child;
    SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        // Create the DB helper
        helper = new ChildrenDbHelper(this);
        db = helper.getWritableDatabase();

        child = (Child)getIntent().getSerializableExtra("child");
        // If provided child object - populate
        if(child != null) {
            mode = 1;
            // Populate the fields
            populateFields(child);
        } else {
            mode = 0;
        }
    }

    private boolean updateChild(){
        return true;
    }

    public void onClickCancel(View v) {
        finish();
    }

    public void onClickSubmit(View v) {
        if (mode == 1){
            // If update
            long id = updateChild(db);
            Toast.makeText(this, "submit clicked: " + mode + " id: " + id, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // If new child
            long id = insertChild(db);
            Toast.makeText(this, "submit clicked: " + mode + " id: " + id, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void populateFields(Child child) {
        EditText edit_first = findViewById(R.id.input_fname);
        edit_first.setText(child.getFname());

        EditText edit_last = findViewById(R.id.input_lname);
        edit_last.setText(child.getLname());

        EditText edit_bdate = findViewById(R.id.input_bdate);
        edit_bdate.setText(child.getBdate());

        EditText edit_street = findViewById(R.id.input_street);
        edit_street.setText(child.getStreet());

        EditText edit_city = findViewById(R.id.input_city);
        edit_city.setText(child.getCity());

        EditText edit_prov = findViewById(R.id.input_province);
        edit_prov.setText(child.getProvince());

        EditText edit_postal = findViewById(R.id.input_postalCode);
        edit_postal.setText(child.getPostalCode());

        EditText edit_country = findViewById(R.id.input_country);
        edit_country.setText(child.getCountry());

        EditText edit_lat = findViewById(R.id.input_lat);
        edit_lat.setText(Float.toString(child.getLat()));

        EditText edit_lng = findViewById(R.id.input_lng);
        edit_lng.setText(Float.toString(child.getLng()));

        CheckBox edit_naughty = findViewById(R.id.input_isNaughty);
        edit_naughty.setChecked(child.isNaughty());
    }

    private long insertChild(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("FNAME", ((EditText)findViewById(R.id.input_fname)).getText().toString());
        values.put("LNAME", ((EditText)findViewById(R.id.input_lname)).getText().toString());
        values.put("BDATE", ((EditText)findViewById(R.id.input_bdate)).getText().toString());
        values.put("STREET", ((EditText)findViewById(R.id.input_street)).getText().toString());
        values.put("CITY", ((EditText)findViewById(R.id.input_city)).getText().toString());
        values.put("PROVINCE", ((EditText)findViewById(R.id.input_province)).getText().toString());
        values.put("POSTAL_CODE", ((EditText)findViewById(R.id.input_postalCode)).getText().toString());
        values.put("COUNTRY", ((EditText)findViewById(R.id.input_country)).getText().toString());
        values.put("LAT", Float.parseFloat(((EditText)findViewById(R.id.input_lat)).getText().toString()));
        values.put("LNG", Float.parseFloat(((EditText)findViewById(R.id.input_lng)).getText().toString()));
        int naughty = ((CheckBox)findViewById(R.id.input_isNaughty)).isChecked() ? 1 : 0;
        values.put("ISNAUGHTY", naughty);
        return db.insert("CHILDREN", null, values);
    }

    private long updateChild(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("FNAME", ((EditText)findViewById(R.id.input_fname)).getText().toString());
        values.put("LNAME", ((EditText)findViewById(R.id.input_lname)).getText().toString());
        values.put("BDATE", ((EditText)findViewById(R.id.input_bdate)).getText().toString());
        values.put("STREET", ((EditText)findViewById(R.id.input_street)).getText().toString());
        values.put("CITY", ((EditText)findViewById(R.id.input_city)).getText().toString());
        values.put("PROVINCE", ((EditText)findViewById(R.id.input_province)).getText().toString());
        values.put("POSTAL_CODE", ((EditText)findViewById(R.id.input_postalCode)).getText().toString());
        values.put("COUNTRY", ((EditText)findViewById(R.id.input_country)).getText().toString());
        values.put("LAT", Float.parseFloat(((EditText)findViewById(R.id.input_lat)).getText().toString()));
        values.put("LNG", Float.parseFloat(((EditText)findViewById(R.id.input_lng)).getText().toString()));
        int naughty = ((CheckBox)findViewById(R.id.input_isNaughty)).isChecked() ? 1 : 0;
        values.put("ISNAUGHTY", naughty);
        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(child.getId())};
        return db.update("CHILDREN", values, where, whereArgs);
    }

}
