/**
 * @author Suparno Deb
 * PURPOSE: Binds data regarding Hotel to RecyclerView
 */
package com.example.hotelbooker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private ArrayList<Hotel> hotelList;

    public static class HotelViewHolder extends RecyclerView.ViewHolder{
        public ImageView hotelLogo;
        public TextView name;
        public TextView address;
        public TextView phoneNumber;
        public TextView rating;
        public Button book;

        /**
         * Instantiate field objects to the buttons/textviews in hotel_item.xml
         * @param itemView
         */
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelLogo = itemView.findViewById(R.id.hotelLogo);
            name = itemView.findViewById(R.id.hotName);
            address = itemView.findViewById(R.id.hotAddress);
            phoneNumber = itemView.findViewById(R.id.reservationDetails);
            rating = itemView.findViewById(R.id.hotelPhoneNumber);
            book = itemView.findViewById(R.id.select);
        }
    }

    public HotelAdapter(ArrayList<Hotel> hotelList){
        this.hotelList = hotelList;
    }

    /**
     * Determine hotel_item.xml as the ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        HotelViewHolder hvh = new HotelViewHolder(v);
        return hvh;
    }

    /**
     * Iterate through the hotelList list and retrieve necessary information. Initialise appropriate texts to the retrieved information.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel current = hotelList.get(position);

        holder.name.setText(current.getName());
        holder.address.setText(current.getAddress() + ", " + current.getCity());
        holder.phoneNumber.setText(current.getPhoneNumber());
        holder.rating.setText("Rating: " + Integer.toString(current.getRating()));
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataAccess.basketHotel = current;
                notifyItemChanged(holder.getAdapterPosition());
                Toast.makeText(v.getContext(), "Hotel Selected: " + DataAccess.basketHotel.getName() + ". Please click 'CART' to proceed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}
