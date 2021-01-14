package com.example.mrclaim;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VehicleRecViewAdapter extends RecyclerView.Adapter<VehicleRecViewAdapter.ViewHolder> {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    private Context context;

    public VehicleRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicles_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtVehicle.setText(vehicles.get(position).getVehino());
        holder.txtdate.setText(vehicles.get(position).getExpdate());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, vehicles.get(position).getVehino() + "Selected", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(v.getContext(),SelectedVehicle.class);
                i.putExtra("title",vehicles.get(position));
                v.getContext().startActivity(i);

            }
        });
        Glide.with(context).asBitmap().load(vehicles.get(position).getImageurl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtVehicle, txtdate;
        private CardView parent;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i =new Intent(v.getContext(),SelectedVehicle.class);
//                    i.putExtra("title",vehicles);
//                    v.getContext().startActivity(i);
//                }
//            });
            txtVehicle = itemView.findViewById(R.id.vehi_no);
            parent = itemView.findViewById(R.id.vehicle_list);
            txtdate = itemView.findViewById(R.id.exp_date);
            image = itemView.findViewById(R.id.vehicle_img);
        }
    }
}
