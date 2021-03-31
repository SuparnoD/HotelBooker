/**
 * @author Suparno Deb
 * DATE LAST MODIFIED: 18/03/2021
 * PURPOSE: Handles events in response to the user's interaction with activity_search Activity
 */
package com.example.hotelbooker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class SearchHotel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private  int rating;
    private boolean spinnerSelected = false;

    /**
    Method is executed upon opening the Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        Spinner ratingSpinner = (Spinner)findViewById(R.id.ratingSpinner);
        ratingSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.rating_spinner, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(ad);
        ratingSpinner.setOnItemSelectedListener(this);
    }

    /**
    Search algorithm implementation
     */
    public void filter(View v){
        Intent i = new Intent(this, BookHotel.class);
        EditText hotel = (EditText)findViewById(R.id.hotelNameF);
        EditText city = (EditText)findViewById(R.id.cityF);

        BookHotel.search = true;
        boolean hotelEmpty = false;
        boolean cityEmpty = false;

        String sHotelName = (String)hotel.getText().toString();
        String sCity = (String)city.getText().toString();

        if(sHotelName.isEmpty()){
            hotelEmpty = true;
        } else {
            hotelEmpty = false;
        }

        if(sCity.isEmpty()){
            cityEmpty = true;
        } else {
            cityEmpty = false;
        }

        /*
        SEARCH CASE: RATING
        - Rating, Name, City
        - Rating, Name
        - Rating, City
        - Rating
         */
        if(spinnerSelected){
            for(Hotel h : DataAccess.hotelList){
                if((!hotelEmpty) && (!cityEmpty)){
                    if((h.getRating() == rating) && (h.getName().contains(sHotelName)) && (h.getCity().contains(sCity))){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((cityEmpty) && (!hotelEmpty)){
                    if((h.getRating() == rating) && (h.getName().contains(sHotelName))){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((hotelEmpty) && (!cityEmpty)){
                    if((h.getRating() == rating) && (h.getCity().contains(sCity))){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((hotelEmpty) && (cityEmpty)){
                    if(h.getRating() == rating){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
            }
        }

        /*
        SEARCH CASE: NAME
        - Name, City, Rating
        - Name, Rating
        - Name, City
        - Name
         */
        else if(!hotelEmpty){
            for(Hotel h : DataAccess.hotelList){
                if((!cityEmpty) && (spinnerSelected)){
                    if((h.getName().contains(sHotelName)) && (h.getCity().contains(sCity)) && (h.getRating() == rating)){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((cityEmpty) && (spinnerSelected)){
                    if((h.getName().contains(sHotelName)) && (h.getRating() == rating)){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((!spinnerSelected) && (!cityEmpty)){
                    if((h.getName().contains(sHotelName)) && (h.getCity().contains(sCity))){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((!spinnerSelected) && (cityEmpty)){
                    if(h.getName().contains(sHotelName)){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
            }
        }

        /*
        SEARCH CASE: CITY
        - City, Name, Rating
        - City, Rating
        - City, Name
        - City
         */
        else if(!cityEmpty){
            for(Hotel h : DataAccess.hotelList){
                if((!hotelEmpty) && (spinnerSelected)){
                    if((h.getCity().contains(sCity)) && (h.getName().contains(sHotelName)) && (h.getRating() == rating)){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((hotelEmpty) && (spinnerSelected)){
                    if((h.getCity().contains(sCity)) && (h.getRating() == rating)){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((!spinnerSelected) && (!hotelEmpty)){
                    if(h.getCity().contains(sCity) && (h.getName().contains(sHotelName))){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
                if((!spinnerSelected) && (hotelEmpty)){
                    if(h.getCity().contains(sCity)){
                        BookHotel.search = true;
                        DataAccess.searchList.add(h);
                        startActivity(i);
                    }
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selection = parent.getItemAtPosition(position).toString();

        if(!selection.contains("-")){
            spinnerSelected = true;
            rating = Integer.parseInt(selection);
        } else {
            spinnerSelected = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
    activity_book_hotel is opened when the following method is called
     */
    public void back(View v){
        Intent i = new Intent(this, BookHotel.class);
        startActivity(i);
        DataAccess.hotelList.clear();
    }
}