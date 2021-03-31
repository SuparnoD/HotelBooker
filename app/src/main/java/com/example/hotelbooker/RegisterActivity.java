/**
 * @author Suparno Deb
 * PURPOSE: Handles events in response to the user's interaction with activity_register Activity
 */
package com.example.hotelbooker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private CustomerDB cusDB;

    /**
     * Method is executed upon opening the Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Error message box is set to invisible
        TextView errorTxt = (TextView)findViewById(R.id.error);
        errorTxt.setVisibility(View.GONE);
    }

    /**
     * Method deals with handling events in response to pressing the register button
     */
    public void register(View v){
        Intent i = new Intent(this, MainActivity.class);
        Button regBtn = (Button)findViewById(R.id.regBtn);
        TextView errorTxt = (TextView)findViewById(R.id.error);
        // userExists is set to false. This variable will switch depending if a Customer object with the same email value exists in the customerList list
        Boolean userExists = false;

        EditText fname = (EditText)findViewById(R.id.forenameField);
        EditText sname = (EditText)findViewById(R.id.surnameField);
        EditText email = (EditText)findViewById(R.id.emailField);
        EditText password = (EditText)findViewById(R.id.passworldField);
        // var sFname is initialised to the value put in the forename field
        String sFname = fname.getText().toString();
        // var sSname is initialised to the value put in the surname field
        String sSname = sname.getText().toString();
        // var sEmail is initialised to the value put in the email field
        String sEmail = email.getText().toString();
        // var sPassword is initialised to the value put in the password field
        String sPassword = password.getText().toString();

        /**
         * Iterates through all the Customer objects in customerList list
         * If: var sEmail is equal to any Customer.Email, userExists switches to true
         */
        for(Customer cus : DataAccess.customerList){
            if(sEmail.equals(cus.getEmail())){
                userExists = true;
            }
        }

        // Instantiation of a new Customer object
        Customer customer = new Customer();

        /*
        IF:
        - Any fields are empty
        - The value put in the email field does not contain '@'
        - userExist is true
        THEN
        - Set error message box to visible
        - Return a message corresponding to the type of error made
        ELSE
        - Set the customer object:-
            forename to sFname
            surname to sSname
            email to sEmail
            password to sPassword
         - Add customer object to customerList list
         - Add customer object to currentLogged
         - Register data to the database
         - Open the activity_main Activity
         */
        if((sFname.isEmpty()) || (sSname.isEmpty()) || (sEmail.isEmpty()) || (sPassword.isEmpty())){
            errorTxt.setVisibility(View.VISIBLE);
            errorTxt.setText("Error: please ensure you have filled all the fields");
        } else if((!sEmail.contains("@"))){
            errorTxt.setVisibility(View.VISIBLE);
            errorTxt.setText("Error: invalid email address");
        } else if(userExists){
            errorTxt.setVisibility(View.VISIBLE);
            errorTxt.setText("Error: email address has been used");
        } else {
            customer.setForename(sFname);
            customer.setSurname(sSname);
            customer.setEmail(sEmail);
            customer.setPassword(sPassword);

            DataAccess.customerList.add(customer);
            DataAccess.currentLogged.add(customer);
            this.cusDB = new CustomerDB(this);
            this.cusDB.insert(customer);
            startActivity(i);

            regBtn.setEnabled(false);
        }
    }

    /**
     activity_main is opened when the following method is called
     */
    public void back(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}