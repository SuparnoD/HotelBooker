/**
 * @author Suparno Deb
 * PURPOSE: Contains properties/methods necessary to instantiate a Customer object
 */
package com.example.hotelbooker;

public class Customer {
    private String forename;
    private String surname;
    private String email;
    private String password;
    private Reservation reservations;

    public Customer(){

    }

    /**
     * Customer constructor
     * @param forename      forename of a customer
     * @param surname       surname of a customer
     * @param email         email of a customer
     * @param password      password of a customer
     * @param reservations  Reservation object
     */
    public Customer(String forename, String surname, String email, String password, Reservation reservations){
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.reservations = reservations;
    }

    public Customer(String forename, String surname, String email, String password){
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the customer's forename
     * @return forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Initialise forename to parameter value
     * @param forename
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Returns the customer's surname
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Initialise surname to parameter value
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the customer's email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Initialise email to parameter value
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the customer's password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Initialise password to parameter value
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the customer's Reservation
     * @return reservations
     */
    public Reservation getReservations() {
        return reservations;
    }

    /**
     * Instantiate's reservation to parameter value
     * @param reservations
     */
    public void setReservations(Reservation reservations) {
        this.reservations = reservations;
    }
}