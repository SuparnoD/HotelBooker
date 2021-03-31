/**
 * @author Suparno Deb
 * PURPOSE: Handles events in response to the user's interaction with activity_reserve_hotel Activity
 */
package com.example.hotelbooker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ReserveHotel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String day, month, year;
    private int heads;
    private int nights;
    private ReservationDB resDB;

    /**
     * The following is executed upon opening the Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_hotel);

        Button bookBtn = (Button)findViewById(R.id.reserveBtn);

        if(DataAccess.basketHotel == null){
            bookBtn.setEnabled(false);
        }

        /*
        Instantiation of daySpinner
         */
        Spinner daySpinner = findViewById(R.id.daySpinner);
        daySpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> dayAD = ArrayAdapter.createFromResource(this, R.array.day_spinner, android.R.layout.simple_spinner_item);
        dayAD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAD);
        daySpinner.setOnItemSelectedListener(this);

        /*
        Instantiation of monthSpinner
         */
        Spinner monthSpinner = findViewById(R.id.monthSpinner);
        monthSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> monthAD = ArrayAdapter.createFromResource(this, R.array.month_spinner, android.R.layout.simple_spinner_item);
        monthAD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAD);
        monthSpinner.setOnItemSelectedListener(this);

        /*
        Instantiation of yearSpinner
         */
        Spinner yearSpinner = findViewById(R.id.yearSpinner);
        yearSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> yearAD = ArrayAdapter.createFromResource(this, R.array.year_spinner, android.R.layout.simple_spinner_item);
        yearAD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAD);
        yearSpinner.setOnItemSelectedListener(this);

        /*
        Instantiation of nightSpinner
         */
        Spinner nightSpinner = findViewById(R.id.nightSpinner);
        nightSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> nightAD = ArrayAdapter.createFromResource(this, R.array.night_spinner, android.R.layout.simple_spinner_item);
        nightAD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nightSpinner.setAdapter(nightAD);
        nightSpinner.setOnItemSelectedListener(this);

        /*
        Instantiation of headSpinner
         */
        Spinner headSpinner = findViewById(R.id.headSpinner);
        headSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> headAD = ArrayAdapter.createFromResource(this, R.array.head_spinner, android.R.layout.simple_spinner_item);
        headAD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        headSpinner.setAdapter(headAD);
        headSpinner.setOnItemSelectedListener(this);

        this.resDB = new ReservationDB(this);

        TextView hotelDesc = findViewById(R.id.hotelDesc);
        /*
        IF
        - DataAccess.basketHotel is null
        THEN
        - Display "Nothing Selected"
        ELSE
        - Display the details of the selected hotel in the basket
         */
        if(DataAccess.basketHotel == null){
            hotelDesc.setText("Nothing Selected!");
        } else {
            hotelDesc.setText(DataAccess.basketHotel.getName() + "\n" +
                    DataAccess.basketHotel.getAddress() + "\n" +
                    DataAccess.basketHotel.getCity() + "\n" +
                    DataAccess.basketHotel.getRating() + " Star Rating");
        }
    }

    /*
    Upon clicking the 'BOOK' Button:
    - Instantiate a Reservation object
    - Set the properties of the Reservation object to the appropriate Spinner values
    - Trigger a confirmation dialog with the details of the reservation along with the calculated price
    - IF user clicked 'Confirm' - add the Reservation object to the DataAccess.reservationsList, aswell as insert the data into the database
    - ELSE close the dialog
     */
    public void book(View v){
        Intent i = new Intent(this, MainActivity.class);
        Reservation confirmReservation = new Reservation();

        confirmReservation.setCustomer(DataAccess.currentLogged.get(0));
        confirmReservation.setDate(day+"/"+month+"/"+year);
        confirmReservation.setHotel(DataAccess.basketHotel);
        confirmReservation.setNights(nights);
        confirmReservation.setPeople(heads);
        confirmReservation.setPrice(calculatePrice(confirmReservation));

        String dialog = confirmReservation.getHotel().getName() + "\n" +
                confirmReservation.getHotel().getAddress() + ", " + confirmReservation.getHotel().getCity() + "\n" +
                "Nights: " + confirmReservation.getNights() + "\n" +
                "Heads: " + confirmReservation.getPeople() + "\n \n" +
                "TOTAL PRICE: £" + confirmReservation.getPrice();
        AlertDialog.Builder builder = new AlertDialog.Builder(ReserveHotel.this);
        builder.setMessage(dialog)
        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataAccess.reservationsList.add(confirmReservation);
                resDB.insert(confirmReservation);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Booking Successfully Processed", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancel", null);

        AlertDialog alert = builder.create();
        alert.show();

    }

    /**
     * Price Calculation Algorithm
     * Takes in a parameter of type Reservation and calculates a price accordingly
     * @param res
     * @return
     */
    public double calculatePrice(Reservation res){
        double addPerHead = res.getPeople() * 0.60;
        double addPerNight = res.getNights() * 0.85;

        double price = (res.getHotel().getPrice() * addPerHead) + (res.getHotel().getPrice() * addPerNight);

        return price;
    }

    /**
     * Initialisation of variables to the values set in the Activity Spinner's
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.daySpinner:
                day = parent.getSelectedItem().toString();
                break;
            case R.id.monthSpinner:
                month = parent.getSelectedItem().toString();
                break;
            case R.id.yearSpinner:
                year = parent.getSelectedItem().toString();
                break;
            case R.id.headSpinner:
                heads = Integer.parseInt(parent.getSelectedItem().toString());
                break;
            case R.id.nightSpinner:
                nights = Integer.parseInt(parent.getSelectedItem().toString());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     activity_book_hotel is opened when the following method is called
     BookHotel.search is set to false to refresh the hotel list
     DataAccess.basketHotel is set to null
     DataAccess.hotelList is cleared to avoid duplication
     */
    public void back(View v){
        Intent i = new Intent(this, BookHotel.class);
        BookHotel.search = false;
        DataAccess.basketHotel = null;
        DataAccess.hotelList.clear();
        startActivity(i);
    }
}