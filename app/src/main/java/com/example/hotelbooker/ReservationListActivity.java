/**
 * @author Suparno Deb
 * PURPOSE: Handles events in response to the user's interaction with activity_reservation_list Activity
 */
package com.example.hotelbooker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReservationListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    /**
     * The following is executed upon opening the activity:
     * - iterate through all the Reservation objects in the database, store Reservation objects with email matching the email of DataAccess.currentLogged in DataAccess.currentReservation list
     * - populate the RecyclerView with all the Reservation objects stored in DataAccess.currentReservation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        DataAccess.updateCurrentLoggedRes();

        recyclerView = findViewById(R.id.resList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ReservationAdapter(DataAccess.currentReservation);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    /**
     * Clear DataAccess.currentReservation list upon shutting the app to avoid duplication
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        DataAccess.currentReservation.clear();
    }

    /**
     * Clear DataAccess.currentReservation list upon going back to avoid duplication
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DataAccess.currentReservation.clear();
    }

    /**
     activity_main is opened when the following method is called
     DataAccess.currentReservation is cleared to avoid duplication
     */
    public void back(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        DataAccess.currentReservation.clear();
    }

}