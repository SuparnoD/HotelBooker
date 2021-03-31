/**
 * @author Suparno Deb
 * PURPOSE: Handles events in response to the user's interaction with activity_main Activity
 */
package com.example.hotelbooker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CustomerDB cusDB;
    ReservationDB resDB;

    /**
     Method is executed upon opening the Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Populate the customerList list with data contained in the customer table
        // Populate the reservationsList with data contained in the reservations table
        cusDB = new CustomerDB(this);
        resDB = new ReservationDB(this);
        DataAccess.customerList = cusDB.selectAll();
        DataAccess.reservationsList = resDB.selectAll();
        DataAccess.updateReservation();
        DataAccess.hotelList.clear();

        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        Button registerBtn = (Button)findViewById(R.id.registerBtn);
        Button logoutBtn = (Button)findViewById(R.id.logoutBtn);
        TextView welcomeTxt = (TextView)findViewById(R.id.welcomeText);
        View logBtn = loginBtn;
        View regBtn = registerBtn;
        View logout = logoutBtn;
        View welcome = welcomeTxt;

        /*
        Checks to see if any user is currently logged in:
        If logged in: login button and register button is invisible. A welcome text and logout button is displayed
        If not logged in: login button and register button is displayed whereas the welcome text and logout button is invisible
         */
        if(!DataAccess.currentLogged.isEmpty()){
            logBtn.setVisibility(View.GONE);
            regBtn.setVisibility(View.GONE);
            for(Customer i : DataAccess.currentLogged){
               welcomeTxt.setText(welcomeTxt.getText() + " " + i.getForename() + " " + i.getSurname());
            }
        } else {
            welcome.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        }
    }

    /*
    IF
    - currentLogged list is empty
    THEN
    - Display a message prompting the user to login first
    ELSE
    - Open book_hotel Activity
     */
    public void bookHotel(View v){
        Button book = (Button)findViewById(R.id.bookBtn);
        Intent i = new Intent(this, BookHotel.class);

       if(DataAccess.currentLogged.isEmpty()) {
           if (book.isPressed()) {
               Toast.makeText(this, "You are required to login first!", Toast.LENGTH_LONG).show();
           }
       } else {
           startActivity(i);
       }
    }

    /**
     * IF
     * - currentLogged list is empty
     * THEN
     * - Display a message prompting the user to login first
     * ELSE
     * - Open reservation_list Activity
     */
    public void reservationList(View v){
        Button reserve = (Button)findViewById(R.id.dbBtn);
        Intent i = new Intent(this, ReservationListActivity.class);

        if(DataAccess.currentLogged.isEmpty()){
            if(reserve.isPressed()){
                Toast.makeText(this, "You are required to login first!", Toast.LENGTH_LONG).show();
            }
        } else {
            startActivity(i);
        }
    }

    /**
     * activity_register is opened when the following method is called
     */
    public void register(View v){
        Button regBtn = (Button)findViewById(R.id.registerBtn);

        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    /**
     * A Login dialog window is opened when the following method is called
     * An authentication system is implemented herein where the user is prompted to enter an email address and password
     * The input is then checked to see if it is valid. If valid, login is successful else an error message is returned.
     */
    public void login(View v){
        Intent i = new Intent(this, MainActivity.class);
        v = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_login, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        EditText email = (EditText)v.findViewById(R.id.emailF);
        EditText password = (EditText)v.findViewById(R.id.passwordF);

        // Pressing the 'Login' button executes the following code
        builder.setMessage("Login").setView(v).setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // var sEmail is initialised to the value put in the email field
                String sEmail = (String)email.getText().toString();
                // var sPassword is initialised to the value put in the password field
                String sPassword = (String)password.getText().toString();
                // loginSuccess is initialised to false. This variable will determine the end result of interaction with the dialog window
                boolean loginSuccess = false;

                // Iterates through all the Customer objects in customerList list
                for(Customer cus : DataAccess.customerList){
                    /*
                    IF
                    - Customer.Email is equal to sEmail
                    AND
                    - Customer.Password is equal to sPassword
                    THEN
                    - Display a login success message
                    - Set loginSuccess to true
                    - Add that particular Customer object to currentLogged list
                     */
                    if(sEmail.equals(cus.getEmail()) && sPassword.equals(cus.getPassword())){
                        Toast logResult = Toast.makeText(getApplicationContext(),"Login Successful, Welcome " + cus.getForename() + " " + cus.getSurname(), Toast.LENGTH_LONG);
                        logResult.show();
                        loginSuccess = true;
                        DataAccess.currentLogged.add(cus);
                    }
                }
                // Else (if loginSuccess is still false), display a login failure message
                if(!loginSuccess){
                    Toast logResult = Toast.makeText(getApplicationContext(), "Login Failed: Incorrect email or password", Toast.LENGTH_LONG);
                    logResult.show();
                }
                startActivity(i);
            }
            // Pressing 'Cancel' button will close the dialog window
        }).setNegativeButton("Cancel", null).setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Method will clear the currentLogged list and restart the current Activity
     */
    public void logout(View v){
        DataAccess.currentLogged.clear();
        finish();
        startActivity(getIntent());
    }

    public void clear(View v){
        cusDB = new CustomerDB(this);
        cusDB.deleteAll();

        resDB = new ReservationDB(this);
        resDB.deleteAll();
        Intent i = getIntent();
        finish();
        startActivity(i);
    }
}