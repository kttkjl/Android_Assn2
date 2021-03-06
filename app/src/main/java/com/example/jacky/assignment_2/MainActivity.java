package com.example.jacky.assignment_2;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChildListListener{

    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView lv_list_children;

    public boolean isTablet;
    public ArrayList<Child> children;

    public ArrayList<Child> getChildren() {
        // Create SQLite Database
        SQLiteOpenHelper helper = new ChildrenDbHelper(this);
        ArrayList<Child> children = new ArrayList<Child>();
        try {
            db = helper.getReadableDatabase();
            cursor = db.rawQuery("select * from CHILDREN", null);
            if (cursor.moveToFirst()) {
                do {
                    // Boolean handling
                    boolean isNaughty = cursor.getInt(11) == 1;
                    children.add(new Child (
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
                            isNaughty));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException sqlex) {
            String msg = "[MainActivity / getChildren] DB unavailable";
            msg += "\n\n" + sqlex.toString();

            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
        }
        return children;
    }

    public ArrayList<Child> findChildByName(String name) {
        // Use temp arrayList here
        ArrayList<Child> temp_children = new ArrayList<Child>();
        String [] queryString = name.split(" ");
        String where;
        String[] whereArgs;
        try {
            switch (queryString.length){
                case 1:
                    // If name was a single string with no delimiters
                    where = "FNAME=? OR LNAME=?";
                    whereArgs = new String[] {name, name};
                    break;
                case 2:
                    // If they gave full name
                    where = "FNAME=? AND LNAME=?";
                    whereArgs = new String[] {queryString[0], queryString[1]};
                    break;
                default:
                    where = "";
                    whereArgs = new String[0];
                    break;
            }
            // If user query'd something
            if (whereArgs.length != 0) {
                cursor = db.query("CHILDREN", null, where, whereArgs, null, null, "FNAME");
            }
            // Loop
            if (cursor.moveToFirst()) {
                do {
                    temp_children.add(createChildWithCursor(cursor));
                    System.out.println("temp_child add, size: " + temp_children.size());
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException sqlex){
            String msg = "[MainActivity / findChildByName] DB unavailable";
            msg += "\n\n" + sqlex.toString();
            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
        }
        return temp_children;
    }

    private Child createChildWithCursor(Cursor c) {
        // Boolean handling
        boolean isNaughty = c.getInt(11) == 1;
        return new Child (
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getFloat(9),
                cursor.getFloat(10),
                isNaughty);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add toolbar to this
        Toolbar toolbar = findViewById(R.id.appbar_id);
        setSupportActionBar(toolbar);

        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            isTablet = true;
        }

        this.children = getChildren();

    }

    @Override
    public void itemClicked(Child child) {
        if (isTablet) {
            ChildDetailFragment details = new ChildDetailFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            details.setChild(child);
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent(MainActivity.this, ChildActivity.class);
            intent.putExtra("child", child);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Add buttons to said toolbar
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // For full control
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item_find_child).getActionView();
        int options = searchView.getImeOptions();
        searchView.setImeOptions(options|EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Child> resultList = findChildByName(query);

                updateListView(resultList);
                Toast.makeText(MainActivity.this, "query: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // Grab the cached version if they cancel
                if (query.isEmpty()) {
                    children = getChildren();
                    updateListView(children);
                }
                return true;
            }
        });
        return true;
    }

    public ArrayList<Child> findNaughtyChildren(boolean isNaughty) {
        // Use temp arrayList here
        ArrayList<Child> temp_children = new ArrayList<Child>();
        String where;
        String[] whereArgs;
        try {
            if (isNaughty) {
                // If finding naughty children
                where = "ISNAUGHTY=?";
                whereArgs = new String[]{"1"};
            } else {
                // If finding nice children
                where = "ISNAUGHTY=?";
                whereArgs = new String[]{"0"};
            }
            cursor = db.query("CHILDREN", null, where, whereArgs, null, null, "FNAME");
            // Loop
            if (cursor.moveToFirst()) {
                do {
                    temp_children.add(createChildWithCursor(cursor));
                    System.out.println("temp_child add, size: " + temp_children.size());
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException sqlex){
            String msg = "[MainActivity / findNaughtyChildren] DB unavailable";
            msg += "\n\n" + sqlex.toString();
            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
        }
        return temp_children;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.menu_item_add_child:
            {
                Intent intent = new Intent(MainActivity.this, AddChildActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.overflow_view_naughty:
            {
                ArrayList<Child> resultList = findNaughtyChildren(true);
                updateListView(resultList);
                break;
            }
            case R.id.overflow_view_nice:
            {
                ArrayList<Child> resultList = findNaughtyChildren(false);
                updateListView(resultList);
                break;
            }
            case R.id.overflow_view_all:
            {
                this.children = getChildren();
                updateListView(children);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (cursor != null)
            cursor.close();

        if (db != null)
            db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update the WHOLE list
        this.children = getChildren();
        updateListView(this.children);
    }

    /**
     * Helper to update current home list_view to a list
     * @param children - array of Child objects to be displayed
     */
    protected void updateListView(ArrayList<Child> children) {
        this.children = children;
        // Get ListFragment
        ChildListFragment listFragment = (ChildListFragment) getSupportFragmentManager().findFragmentById(R.id.list_frag);
        listFragment.updateListFragment(children);
    }
}
