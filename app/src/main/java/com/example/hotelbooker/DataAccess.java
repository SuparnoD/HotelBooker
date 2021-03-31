/**
 * @author Suparno Deb
 * PURPOSE: Provides an abstract interface to alter necessary data
 */
package com.example.hotelbooker;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataAccess {
    /*
    List of type Customer
    Stores only one Customer indicating that the one Customer object in that list, is the one logged in. Interaction will only affect that one logged in Customer object
     */
    public static ArrayList<Customer> currentLogged = new ArrayList<Customer>();

    /*
    List of type Reservation
    Stores reservation data of currently logged in user
     */
    public static ArrayList<Reservation> currentReservation = new ArrayList<Reservation>();

    /*
    List of type Customer
    Stores data of all individual customer's
     */
    public static ArrayList<Customer> customerList = new ArrayList<Customer>();

    /*
    List of type Reservation
    Stores data of all reservations made
     */
    public static ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();

    /*
    List of type Hotel
    Stores a list of all hotels
     */
    public static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    /*
    List of type Hotel
    Stores a list of hotels that matches the search criteria
     */
    public static ArrayList<Hotel> searchList = new ArrayList<Hotel>();

    /*
    Instance of type Hotel
    The Hotel object is instantiated to the intended Hotel object that the customer is planning to reserve
     */
    public static Hotel basketHotel;

    /**
     * Iterate through reservationsList list and customerList list
     * If the Customer.Email equals Reservation.Email, set the Customer.Reservation to the select Reservation object
     */
    public static void updateReservation(){
        DataAccess.updateHotelList();

        for(Reservation res : DataAccess.reservationsList){
            for(Customer cus : DataAccess.customerList){
                if(res.getCustomer().getEmail().equals(cus.getEmail())){
                    for(Hotel hot : DataAccess.hotelList){
                        if(res.getHotel().getName().equals(hot.getName())){
                            res.setHotel(hot);
                            cus.setReservations(res);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will retrieve Reservation objects from the Reservation List.
     * Where the Customer Email (of Reservation) is equal to the email currently logged in, assign Reservation objects to currently logged in customers
     */
    public static void updateCurrentLoggedRes(){
        for(Reservation res : DataAccess.reservationsList){
            for(Customer cus : DataAccess.currentLogged){
                if(res.getCustomer().getEmail().equals(cus.getEmail())){
                    DataAccess.currentReservation.add(res);
                }
            }
        }
    }

    /**
     * Method to fill the hotelList with Hotel objects
     */
    public static void updateHotelList(){
        Hotel TheBalmoral = new Hotel("The Balmoral", "Edinburgh", "1 Princess Street", "0131 556 2414", 5, 50.00);
        Hotel RadissonBlu = new Hotel("Radisson Blu", "Edinburgh", "80 High Street", "0131 557 9797", 4, 35.00);
        Hotel TheQueensPark = new Hotel("The Queens Park", "London", "48 Queensborough Terrace", "020 7229 8080", 3, 25.00);
        Hotel TheSavoy = new Hotel("The Savoy", "London", "Strand", "020 7836 4343", 5, 50.00);
        Hotel Merchant = new Hotel("Merchant", "Manchester", "31 Black Piccadilly", "0161 236 2939", 2, 15.00);
        Hotel CrownePlaza = new Hotel("Crowne Plaza", "Manchester", "70 Shudehill", "0161 828 8600", 4, 35.00);
        Hotel Casa = new Hotel("Casa Seashell Hotel", "Blackpool", "43 York Street", "07401 880534", 1, 10.00);
        Hotel Heroes = new Hotel("Heroes Hotel", "Newcastle", "71 Grainger Street", "0191 231 3131", 3, 25.00);
        Hotel RoyalStation = new Hotel("Royal Station", "Newcastle", "Neville Street", "0191 232 0781", 4, 35.00);
        Hotel ZHotel = new Hotel("The Z Hotel", "Glasgow", "36 North Frederick Street", "0141 212 4550", 4, 35.00);
        Hotel Malmaison = new Hotel("Malmaison", "Aberdeen", "49-53 Queen's Road", "0330 016 0380", 5, 50.00);
        Hotel Tay = new Hotel("Tay Apartments", "Dundee", "101 Broughty Ferry Road", "01382 451142", 3, 25.00);
        Hotel CityView = new Hotel("City View Hotel", "London", "113 Roman Road", "020 3022 5351", 1, 10.00);
        Hotel Lochness = new Hotel("1 Lochness Hotel", "Inverness", "Main Street", "07773 160260", 1, 10.00);
        Hotel Windemere = new Hotel("The Windermere Hotel", "Windermere", "Kendal Road", "015394 42251", 3, 25.00);
        Hotel StGiles = new Hotel("38 St Giles", "Norwich", "38 Street Giles", "01603 662944", 5, 50.00);
        Hotel duVin = new Hotel("Hotel du Vin", "Newcastle", "Allan House, City Road", "0191 389 8628", 4, 35.00);
        Hotel AbbeyLodge = new Hotel("Abbey Lodge", "Southampton", "37 The Polygon", "023 8022 1466", 2, 15.00);
        Hotel ibis = new Hotel("ibis Budget", "Portsmouth", "Fratton Way, Southsea", "023 9273 6386", 2, 15.00);
        Hotel LochNess = new Hotel("Loch Ness Clansman", "Inverness", "Brackla, Loch Ness-side", "01456 450326", 3, 25.00);



        try {
            DataAccess.hotelList.add(ZHotel);
            DataAccess.hotelList.add(duVin);
            DataAccess.hotelList.add(RoyalStation);
            DataAccess.hotelList.add(ibis);
            DataAccess.hotelList.add(TheQueensPark);
            DataAccess.hotelList.add(Tay);
            DataAccess.hotelList.add(TheSavoy);
            DataAccess.hotelList.add(AbbeyLodge);
            DataAccess.hotelList.add(Merchant);
            DataAccess.hotelList.add(StGiles);
            DataAccess.hotelList.add(TheBalmoral);
            DataAccess.hotelList.add(LochNess);
            DataAccess.hotelList.add(RadissonBlu);
            DataAccess.hotelList.add(Casa);
            DataAccess.hotelList.add(CrownePlaza);
            DataAccess.hotelList.add(Heroes);
            DataAccess.hotelList.add(Malmaison);
            DataAccess.hotelList.add(CityView);
            DataAccess.hotelList.add(Lochness);
            DataAccess.hotelList.add(Windemere);
        } catch (Exception e){

        }
    }
}
