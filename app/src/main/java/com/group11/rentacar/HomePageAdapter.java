package com.group11.rentacar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

    public OnItemClickListener listener;

    /*String st1[];
    int images[];
    Context context;*/

    private Context context;
    private ArrayList<Car> cars;

    public HomePageAdapter(/*Context ct, String s1[], int imgs[]*/Context c, ArrayList<Car> ca) {
        /*st1 = s1;
        context = ct;
        images = imgs;*/
        context = c;
        cars = ca;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_menu_raw,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       /* holder.txt1.setText(st1[position]);
        holder.imgView.setImageResource(images[position]);*/
        Car carCurrent = cars.get(position);
        holder.txt1.setText(carCurrent.getName());
        Picasso.with(context).load(cars.get(position).getImage()).placeholder(R.mipmap.ic_launcher).into(holder.imgView);


    }

    @Override
    public int getItemCount() {
        /* return st1.length;*/
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /*TextView txt1;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.vehicle_name);
            imgView = itemView.findViewById(R.id.vehicle_img);*/

         TextView txt1;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.vehicle_name);
            imgView = itemView.findViewById(R.id.vehicle_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){

                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DataSnapshot dataSnapshot,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}
