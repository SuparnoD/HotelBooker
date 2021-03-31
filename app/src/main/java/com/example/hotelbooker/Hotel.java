/**
 * @author Suparno Deb
 * PURPOSE: Contains properties/methods necessary to instantiate a Hotel object
 */
package com.example.hotelbooker;

public class Hotel {
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    private int rating;
    private double price;

    /**
     * Hotel Constructor
     * @param name          hotel name
     * @param city          hotel city
     * @param address       hotel address
     * @param phoneNumber   hotel phone number
     * @param rating        hotel rating
     * @param price         hotel price
     */
    public Hotel(String name, String city, String address, String phoneNumber, int rating, double price){
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.price = price;
    }

    public Hotel(){
        
    }

    /**
     * Returns the hotel's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Initialise name to parameter value
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the hotel's city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Initialise city to parameter value
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the hotel's address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Initialise address to parameter value
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the hotel's phone number
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Initialise phoneNumber to parameter value
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns hotel rating
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Initialise rating to parameter value
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Returns the hotel price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Initialise price to parameter value
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
