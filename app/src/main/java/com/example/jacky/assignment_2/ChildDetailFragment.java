package com.example.jacky.assignment_2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildDetailFragment extends Fragment {

    private SQLiteDatabase db;
    private Cursor cursor;
    private Child child;
    SQLiteOpenHelper helper;

    public ChildDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState !=null) {
            child = (Child) savedInstanceState.getSerializable("childObj");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View v = getView();
        if (v != null) {
            populateFields(child, v);
        }
    }

    private void populateFields(Child child, View v) {
        helper = new ChildrenDbHelper(getActivity());
        db = helper.getWritableDatabase();

        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(child.getId())};
        cursor = db.query("CHILDREN", null, where, whereArgs, null, null, "FNAME");
        cursor.moveToFirst();
        String date_created = cursor.getString(12);

        TextView text_id = v.findViewById(R.id.input_id);
        text_id.setText("ID: " + Long.toString(child.getId()));

        TextView text_name = v.findViewById(R.id.input_fname);
        text_name.setText(child.getFname() + " " + child.getLname());

        TextView text_naughty = v.findViewById(R.id.input_isNaughty);
        String isNaughty = child.isNaughty() ? "Naughty" : "Nice";
        text_naughty.setText(isNaughty);

        TextView text_bdate = v.findViewById(R.id.input_bdate);
        text_bdate.setText("Birth Date: " + child.getBdate());

        TextView text_street = v.findViewById(R.id.input_street);
        text_street.setText("Street: " + child.getStreet());

        TextView text_city = v.findViewById(R.id.input_city);
        text_city.setText("City: " + child.getCity());

        TextView text_prov = v.findViewById(R.id.input_province);
        text_prov.setText("Province: " + child.getProvince());

        TextView text_postal = v.findViewById(R.id.input_postalCode);
        text_postal.setText("Postal Code: " + child.getPostalCode());

        TextView text_country = v.findViewById(R.id.input_country);
        text_country.setText("Country: " + child.getCountry());

        TextView text_lat = v.findViewById(R.id.input_lat);
        text_lat.setText("Latitude: " + Float.toString(child.getLat()));

        TextView text_lng = v.findViewById(R.id.input_lng);
        text_lng.setText("Longitude: " + Float.toString(child.getLng()));

        TextView text_created = v.findViewById(R.id.input_created);
        text_created.setText("Date Created: " + date_created);
    }

    private void deleteChild(SQLiteDatabase db, Child child) {
        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(child.getId())};
        db.delete("CHILDREN", where, whereArgs);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("childObj", child);
    }

    public void setChild(Child child) {
        this.child = child;
    }

}
