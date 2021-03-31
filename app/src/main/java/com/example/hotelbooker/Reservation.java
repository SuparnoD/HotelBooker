/**
 * @author Suparno Deb
 * PURPOSE: Contains properties/methods necessary to instantiate a Reservation object
 */
package com.example.hotelbooker;

public class Reservation {
    private Customer customer;
    private Hotel hotel;
    private int people;
    private int nights;
    private double price;
    private String date;

    /**
     * Constructor for Reservation
     * @param customer  Customer object
     * @param hotel     Hotel object
     * @param people    Number of heads/people
     * @param nights    Number of nights
     * @param price     Total price
     * @param date      Date of reservation
     */
    public Reservation(Customer customer, Hotel hotel, int people, int nights, double price, String date){
        this.customer = customer;
        this.hotel = hotel;
        this.people = people;
        this.nights = nights;
        this.price = price;
        this.date = date;
    }

    public Reservation(){
        
    }

    /**
     * Instantiate customer to parameter value
     * @param customer
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    /**
     * Returns the Customer object
     * @return customer
     */
    public Customer getCustomer(){
        return customer;
    }

    /**
     * Instantiate hotel to parameter value
     * @param hotel
     */
    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    /**
     * Returns the Hotel object
     * @return hotel
     */
    public Hotel getHotel(){
        return hotel;
    }

    /**
     * Initialise people to parameter value
     * @param people
     */
    public void setPeople(int people) { this.people = people; }

    /**
     * Returns people
     * @return people
     */
    public int getPeople() { return people; }

    /**
     * Initialise nights to parameter value
     * @param nights
     */
    public void setNights(int nights){
        this.nights = nights;
    }

    /**
     * Returns nights
     * @return night
     */
    public int getNights(){
        return nights;
    }

    /**
     * Initialise price to parameter value
     * @param price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Returns price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Initialise date to parameter value
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
