/**
 * @author Suparno Deb
 * PURPOSE: Database Manipulation for objects stored in {@link com.example.hotelbooker.DataAccess}.reservationsList list
 */
package com.example.hotelbooker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;


public class ReservationDB {
    private static final String DATABASE_NAME = "reservation.db";
    private static int DATABASE_VERSION = 1;
    static final String RESERVATION_TABLE = "reservations";
    private static Context context;
    static SQLiteDatabase db;
    private SQLiteStatement insertStmt;

    private static final String INSERT = "insert into " + RESERVATION_TABLE
            + " (email, hotel, people, nights, price, date) values (?,?,?,?,?,?)";

    public ReservationDB(Context context){
        ReservationDB.context = context;
        ReservationDB.OpenHelper openHelper = new OpenHelper(this.context);
        ReservationDB.db = openHelper.getWritableDatabase();
        this.insertStmt = ReservationDB.db.compileStatement(INSERT);
    }

    /**
     * Retrieves customer email, hotel name, number of people, number of nights, price and date of the Reservation objects in the list and stores them in the database
     * @param reservation
     * @return
     */
    public long insert(Reservation reservation){
        String people = Integer.toString(reservation.getPeople());
        String nights = Integer.toString(reservation.getNights());
        String price = Double.toString(reservation.getPrice());

        this.insertStmt.bindString(1, reservation.getCustomer().getEmail());
        this.insertStmt.bindString(2, reservation.getHotel().getName());
        this.insertStmt.bindString(3, people);
        this.insertStmt.bindString(4, nights);
        this.insertStmt.bindString(5, price);
        this.insertStmt.bindString(6, reservation.getDate());
        return this.insertStmt.executeInsert();
    }

    public void deleteAll() {
        db.delete(RESERVATION_TABLE, null, null);
    }

    /**
     * This method will retrieve the customer email, hotel name, number of people, number of nights, price and date  from the database. It will assign them to a newly created Reservation object and store them in the desired list
     */
    public ArrayList<Reservation> selectAll(){
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        Cursor cursor = db.query(RESERVATION_TABLE, new String[]{"email", "hotel", "people", "nights", "price", "date"}, null, null, null, null, "email asc");
        int x = 0;
        if(cursor.moveToFirst()){
            do {
                Reservation res = new Reservation();
                String[] b1 = new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)};
                Customer cus = new Customer();
                Hotel hot = new Hotel();
                cus.setEmail(cursor.getString(0));
                hot.setName(cursor.getString(1));
                int people = Integer.parseInt(cursor.getString(2));
                int nights = Integer.parseInt(cursor.getString(3));
                double price = Double.parseDouble(cursor.getString(4));

                res.setCustomer(cus);
                res.setHotel(hot);
                res.setPeople(people);
                res.setNights(nights);
                res.setPrice(price);
                res.setDate(cursor.getString(5));
                list.add(res);
            } while (cursor.moveToNext());
        }
        if(cursor != null && !cursor.isClosed()) {
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
                    + RESERVATION_TABLE
                    + " (id INTEGER PRIMARY KEY, email TEXT, hotel TEXT, people TEXT, nights TEXT, price TEXT, date TEXT)");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            DATABASE_VERSION = newVersion;
            db.execSQL("DROP TABLE IF EXISTS " + RESERVATION_TABLE);
            onCreate(db);
        }
    }
}
