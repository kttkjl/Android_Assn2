package com.example.jacky.assignment_2;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ChildrenDbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "Children.db";
    private static final int DB_VERSION = 2;
    private Context context;

    public ChildrenDbHelper(Context context) {
        // The 3'rd parameter (null) is an advanced feature relating to cursors
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDatabase(sqLiteDatabase, i, i1);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            if (oldVersion < 1) {
                db.execSQL(getCreateChildrenTableSql());
                for (Child c : Child.CHILDREN) {
                    insertChild(db, c);
                }
            }
            if (oldVersion < 2){
//                db.execSQL("ALTER TABLE CHILDREN ADD COLUMN POPULATION NUMERIC;");
            }
        } catch (SQLException sqle) {
            String msg = "[MyPlanetDbHelper/updateMyDatabase/insertCountry] DB unavailable";
            msg += "\n\n" + sqle.toString();
            Toast t = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            t.show();
        }
    }

    private String getCreateChildrenTableSql() {
        String sql = "";
        sql += "CREATE TABLE CHILDREN (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "FNAME TEXT, ";
        sql += "LNAME TEXT, ";
        sql += "BDATE DATE, ";
        sql += "STREET TEXT, ";
        sql += "CITY TEXT, ";
        sql += "PROVINCE TEXT, ";
        sql += "POSTAL_CODE TEXT, ";
        sql += "COUNTRY TEXT, ";
        sql += "LAT REAL, ";
        sql += "LNG REAL, ";
        sql += "ISNAUGHTY BOOLEAN, ";
        sql += "DATE_CREATED DATETIME default CURRENT_TIMESTAMP);";
        return sql;
    }

    private void insertChild(SQLiteDatabase db, Child child) {
        ContentValues values = new ContentValues();
        values.put("FNAME", child.getFname());
        values.put("LNAME", child.getLname());
        values.put("BDATE", child.getBdate());
        values.put("STREET", child.getStreet());
        values.put("CITY", child.getCity());
        values.put("PROVINCE", child.getProvince());
        values.put("POSTAL_CODE", child.getPostalCode());
        values.put("COUNTRY", child.getCountry());
        values.put("LAT", child.getLat());
        values.put("LNG", child.getLng());
        values.put("ISNAUGHTY", child.isNaughty());
        db.insert("CHILDREN", null, values);
    }

}
