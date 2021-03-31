/**
 * @author Suparno Deb
 * PURPOSE: Database Manipulation for objects stored in {@link com.example.hotelbooker.DataAccess}.customerList list
 */
package com.example.hotelbooker;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class CustomerDB {
    private static final String DATABASE_NAME = "hotelbooker.db";
    private static int DATABASE_VERSION = 1;
    static final String CUSTOMER_TABLE = "customer";
    private static Context context;
    static SQLiteDatabase db;
    private SQLiteStatement insertStmt;

    private static final String INSERT = "insert into " + CUSTOMER_TABLE
            + " (forename, surname, email, password) values (?,?,?,?)";

    public CustomerDB(Context context){
        CustomerDB.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        CustomerDB.db = openHelper.getWritableDatabase();
        this.insertStmt = CustomerDB.db.compileStatement(INSERT);
    }

    /**
     * Retrieve the forename, surname, email and password of the Customer objects in the list and stores them in the database
     */
    public long insert(Customer customer) {
        this.insertStmt.bindString(1, customer.getForename());
        this.insertStmt.bindString(2, customer.getSurname());
        this.insertStmt.bindString(3, customer.getEmail());
        this.insertStmt.bindString(4, customer.getPassword());
        return this.insertStmt.executeInsert();
    }

    public void deleteAll() {
        db.delete(CUSTOMER_TABLE, null, null);
    }

    /**
     * This method will retrieve the forename, surname, email and password from the database. It will assign them to a newly created Customer object and store them in the desired list
     */
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> list = new ArrayList<Customer>();
        Cursor cursor = db.query(CUSTOMER_TABLE, new String[]{"forename", "surname", "email", "password"}, null, null, null, null, "forename asc");
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                Customer cus = new Customer();
                String[] b1 = new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)};
                cus.setForename(cursor.getString(0));
                cus.setSurname(cursor.getString(1));
                cus.setEmail(cursor.getString(2));
                cus.setPassword(cursor.getString(3));
                list.add(cus);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return list;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "
                    + CUSTOMER_TABLE
                    + " (id INTEGER PRIMARY KEY, name TEXT, number TEXT, email TEXT, address TEXT)");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            DATABASE_VERSION = newVersion;
            db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
            onCreate(db);
        }
    }

}
