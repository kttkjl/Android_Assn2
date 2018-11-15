package com.example.jacky.assignment_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    private Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Toolbar toolbar = findViewById(R.id.appbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Child Detail");

        child = (Child)getIntent().getSerializableExtra("child");
        populateFields(child);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_child_detail, menu);

        return true;
    }

    private void populateFields(Child child) {
        TextView text_name = findViewById(R.id.input_fname);
        text_name.setText(child.getFname() + " " + child.getLname());

        TextView text_naughty = findViewById(R.id.input_isNaughty);
        String isNaughty = child.isNaughty() ? "Naughty" : "Nice";
        text_naughty.setText(isNaughty);

        TextView text_bdate = findViewById(R.id.input_bdate);
        text_bdate.setText("Birth Date: " + child.getBdate());

        TextView text_street = findViewById(R.id.input_street);
        text_street.setText("Street: " + child.getStreet());

        TextView text_city = findViewById(R.id.input_city);
        text_city.setText("City: " + child.getCity());

        TextView text_prov = findViewById(R.id.input_province);
        text_prov.setText("Province: " + child.getProvince());

        TextView text_postal = findViewById(R.id.input_postalCode);
        text_postal.setText("Postal Code: " + child.getPostalCode());

        TextView text_country = findViewById(R.id.input_country);
        text_country.setText("Country: " + child.getCountry());

        TextView text_lat = findViewById(R.id.input_lat);
        text_lat.setText("Latitude: " + Float.toString(child.getLat()));

        TextView text_lng = findViewById(R.id.input_lng);
        text_lng.setText("Longitude: " + Float.toString(child.getLng()));

    }

}
