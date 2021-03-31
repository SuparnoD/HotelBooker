/**
 * @author Suparno Deb
 * PURPOSE: Binds data regarding Reservation to RecyclerView
 */
package com.example.hotelbooker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {
    private ArrayList<Reservation> reservationsList;

    public static class ReservationViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView address;
        public TextView phoneNumber;
        public TextView details;
        public Button cancel;

        /**
         * Instantiate field objects to the buttons/textviews in reservation_list.xml
         * @param itemView
         */
        public ReservationViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.hotelName);
            address = itemView.findViewById(R.id.hotelAddress);
            phoneNumber = itemView.findViewById(R.id.hotelPhoneNumber);
            details = itemView.findViewById(R.id.reservationDetails);
        }
    }

    public ReservationAdapter(ArrayList<Reservation> reservationsList){
        this.reservationsList = reservationsList;
    }

    /**
     * Determine reservation_list.xml as the ViewHoolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_list, parent, false);
        ReservationViewHolder rvh = new ReservationViewHolder(v);
        return rvh;
    }

    /**
     * Iterate through the reservationList list and retrieve necessary information. Initialise appropriate texts to the retrieved information.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position){
        Reservation current = reservationsList.get(position);

        holder.name.setText(current.getHotel().getName());
        holder.address.setText(current.getHotel().getAddress() + ", " + current.getHotel().getCity());
        holder.phoneNumber.setText(current.getHotel().getPhoneNumber());
        holder.details.setText("\n" +
                "Date: " + current.getDate() + " - " + current.getNights() + " nights - " + current.getPeople() + " heads \n" +
                "TOTAL COST: £" + current.getPrice());
    }

    @Override
    public int getItemCount(){
        return reservationsList.size();
    }
}
