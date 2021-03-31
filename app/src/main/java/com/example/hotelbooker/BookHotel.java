/**
 * @author Suparno Deb
 * PURPOSE: Handles events in response to the user's interaction with aactivity_book_hotel Activity
 */
package com.example.hotelbooker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BookHotel extends AppCompatActivity {
    /**
     * Following variables/objects are created to handle the RecyclerView that lists all instances of Hotel objects stored in {@link DataAccess} hotelList list
     */
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String rating;
    public static boolean search = false;

    /**
    Method is executed upon opening the Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);

        Button backBtn = (Button)findViewById(R.id.backBtn);
        Button searchBtn = (Button)findViewById(R.id.searchBtn);
        Button resetBtn = (Button)findViewById(R.id.resetBtn);

        /*
        IF
        - A filter has not been applied
        THEN
        - List all the Hotel objects in the hotelList
        - Make the Reset button invisible
        ELSE
        - Make the Search and Back button invisible
        - Adapt the RecyclerView to only list the Hotel objects stored in the searchList
         */
        if (!search) {
            DataAccess.updateHotelList();
            recyclerView = findViewById(R.id.hotelList);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            adapter = new HotelAdapter(DataAccess.hotelList);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            View btn = resetBtn;

            btn.setVisibility(View.INVISIBLE);
            btn.setVisibility(View.INVISIBLE);
        } else if(search){
            recyclerView = findViewById(R.id.hotelList);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            adapter = new HotelAdapter(DataAccess.searchList);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            View btn = searchBtn;
            View back = backBtn;

            btn.setVisibility(View.INVISIBLE);
            back.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Clear the hotelList from duplication upon shutting the app
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!search) {
            DataAccess.hotelList.clear();
        }
    }

    /**
     * Clear the hotelList from duplication upon going back
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!search) {
            DataAccess.hotelList.clear();
        }
    }

    /**
    activity_search_hotel is opened when the following method is called
     */
    public void search(View v) {
        Intent i = new Intent(this, SearchHotel.class);
        startActivity(i);
    }

    /**
    Upon calling this method, searchList and hotelList will be cleared to avoid necessary population of objects. It will then refresh the current Activity in order to apply changes.
     */
    public void reset(View v){
        Intent i = new Intent(this, BookHotel.class);
        DataAccess.searchList.clear();
        DataAccess.hotelList.clear();
        search = false;
        startActivity(i);
    }

    /**
    activity_main is opened when the following method is called
     */
    public void back(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        DataAccess.hotelList.clear();
    }

    /**
     * activity_reserve_hotel is opened when the following method is called
     * @param v
     */
    public void cartBtn(View v){
        Intent i = new Intent(this, ReserveHotel.class);
        startActivity(i);
    }
}