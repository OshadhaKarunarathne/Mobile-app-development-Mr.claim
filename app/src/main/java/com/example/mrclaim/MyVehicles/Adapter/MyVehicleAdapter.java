package com.example.mrclaim.MyVehicles.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrclaim.Model.MyVehicle_Model;
import com.example.mrclaim.MyVehicles.SelectedVehicle;
import com.example.mrclaim.R;
import com.example.mrclaim.ShowCase.Adpater.CaseViewAdapter;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyVehicleAdapter extends   RecyclerView.Adapter<MyVehicleAdapter.MyViewHolder>{


    private Context context;
    private List<MyVehicle_Model> report_modelList;

    public MyVehicleAdapter(Context context, List<MyVehicle_Model> report_modelList) {
        this.context = context;
        this.report_modelList = report_modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate( R.layout.show_my_vehicles_card_layout,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.Model.setText(report_modelList.get(position).getModel()+"");
        holder.Capacity.setText(report_modelList.get(position).getCapacity()+"");
        holder.EngineNo.setText(report_modelList.get(position).getEngineNo()+"");
        holder.EXDate.setText(report_modelList.get(position).getExDate()+"");
        holder.Color.setText(report_modelList.get(position).getColor()+"");
        holder.Vehino.setText(report_modelList.get(position).getVehino()+"");
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,report_modelList.get(position).getModel() + "Selected",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), SelectedVehicle.class);
                i.putExtra("title", report_modelList.get(position));
                v.getContext().startActivity(i);
            }
        });

        try{
            Picasso.get().load( report_modelList.get( position ).getImagePath() )
                    .networkPolicy( NetworkPolicy.NO_CACHE)
                    .memoryPolicy( MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)

                    .into( holder.car_image );


        }catch ( Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return report_modelList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView car_image;
        TextView Model,Vehino;
        TextView Capacity;
        TextView EngineNo;
        TextView EXDate;
        TextView Color;
        CardView parent;

        public MyViewHolder(@NonNull View itemView) {
                    super(itemView);


                    car_image=itemView.findViewById(R.id.car_image);
                    Model=itemView.findViewById(R.id.Model);
                    Capacity=itemView.findViewById(R.id.Capacity);
                    EngineNo=itemView.findViewById(R.id.EngineNo);
                    EXDate=itemView.findViewById(R.id.EXDate);
                    Color=itemView.findViewById(R.id.Color);
                    Vehino = itemView.findViewById(R.id.Vehicle_Nom);
                    parent = itemView.findViewById(R.id.parent);
                }
    }
}
