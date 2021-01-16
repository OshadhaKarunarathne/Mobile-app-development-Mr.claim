package com.example.mrclaim.ShowCase.Adpater;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrclaim.Model.Report_Model;
import com.example.mrclaim.R;
import com.example.mrclaim.ShowCase.GrageDetailsActivity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CaseViewAdapter extends  RecyclerView.Adapter<CaseViewAdapter.MyViewHolder> {
    private  Context context;
    private  List<Report_Model> report_modelList;


    public CaseViewAdapter(Context context, List<Report_Model> report_modelList) {
        this.context = context;
        this.report_modelList = report_modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate( R.layout.show_case_card_layout,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                         Intent i = new Intent(context, GrageDetailsActivity.class);
                        i.putExtra("vid",report_modelList.get(viewHolder.getAdapterPosition()).getVehicleNo());
                        i.putExtra("date",report_modelList.get(viewHolder.getAdapterPosition()).getDateTime());
                        context.startActivity(i);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.vheical_no.setText(report_modelList.get(position).getVehicleNo()+"");
        holder.date.setText(report_modelList.get(position).getCity()+"");
        holder.address.setText(report_modelList.get(position).getAddress()+"");
        holder.city.setText(report_modelList.get(position).getCity()+"");

        try{
            Picasso.get().load( report_modelList.get( position ).getImagePath() )
                    .networkPolicy( NetworkPolicy.NO_CACHE)
                    .memoryPolicy( MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                    .into( holder.post_image );


        }catch ( Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return report_modelList.size();
    }


    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView post_image;
        TextView vheical_no;
        TextView date;
        TextView address;
        TextView city;


        LinearLayout container;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            post_image=itemView.findViewById(R.id.post_image);
            vheical_no=itemView.findViewById(R.id.vheical_no);
            date=itemView.findViewById(R.id.date);
            address=itemView.findViewById(R.id.address);
            city=itemView.findViewById(R.id.city);
            container=itemView.findViewById(R.id.container);
        }
    }
}
