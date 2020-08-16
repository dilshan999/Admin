package com.group11.rentacar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

    String st1[];
    int images[];
    Context context;

    public HomePageAdapter(Context ct, String s1[], int imgs[]){
        st1 = s1;
        context = ct;
        images = imgs;
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
        holder.txt1.setText(st1[position]);
        holder.imgView.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return st1.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt1;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.vehicle_name);
            imgView = itemView.findViewById(R.id.vehicle_img);
        }
    }
}
