package com.example.jacky.assignment_2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    private Child child;
    SQLiteOpenHelper helper;

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
    protected void onResume() {
        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(child.getId())};
        cursor = db.query("CHILDREN", null, where, whereArgs, null, null, "FNAME");
        cursor.moveToFirst();
        // Boolean handling
        boolean isNaughty = cursor.getInt(11) == 1;
        this.child = new Child (
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getFloat(9),
                cursor.getFloat(10),
                isNaughty);
        populateFields(child);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_child_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.menu_item_edit_child:
            {
                Intent intent = new Intent(ChildActivity.this, AddChildActivity.class);
                intent.putExtra("child", child);
                startActivity(intent);
                break;
            }
            case R.id.menu_item_delete_child:
            {
                deleteChild(db, child);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteChild(SQLiteDatabase db, Child child) {
        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(child.getId())};
        db.delete("CHILDREN", where, whereArgs);
    }

    private void populateFields(Child child) {
        helper = new ChildrenDbHelper(this);
        db = helper.getWritableDatabase();

        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(child.getId())};
        cursor = db.query("CHILDREN", null, where, whereArgs, null, null, "FNAME");
        cursor.moveToFirst();
        String date_created = cursor.getString(12);

        TextView text_id = findViewById(R.id.input_id);
        text_id.setText("ID: " + Long.toString(child.getId()));

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

        TextView text_created = findViewById(R.id.input_created);
        text_created.setText("Date Created: " + date_created);
    }

}
