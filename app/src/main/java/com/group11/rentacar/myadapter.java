package com.group11.rentacar;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class myadapter extends RecyclerView.Adapter<myadapter.ImageViewHolder> {
    private Context mContext;
    private List<model> bookings;
    private OnItemClickListener mListener;

    public myadapter(Context context, List<model> uploads) {
        mContext = context;
        bookings = uploads;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlerow, parent, false);
        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        model uploadCurrent = bookings.get(position);
        //holder.viewBrand.setText(uploadCurrent.getModel());

        holder.viewqus.setText(uploadCurrent.getQus());
        holder.viewans.setText(uploadCurrent.getAns());

        // holder.viewPassengers.setText(String.valueOf(uploadCurrent.getPassengers()));
        //  holder.viewTransmission.setText(String.valueOf(uploadCurrent.getTransmission()));
    }
    @Override
    public int getItemCount() {
        return bookings.size();
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener {
        public TextView viewqus,viewans;


        public ImageViewHolder(View itemView) {
            super(itemView);

            viewqus = itemView.findViewById(R.id.questiontext);
            viewans = itemView.findViewById(R.id.answertext);

            //  viewPassengers = itemView.findViewById(R.id.viewPass);
            //  viewTransmission = itemView.findViewById(R.id.viewTrans);

            itemView.setOnClickListener(this);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener!=null){
                int position =getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select");
            // MenuItem dowhatever = contextMenu.add(Menu.NONE,1,1,"Update");
            MenuItem delete = contextMenu.add(Menu.NONE,1,1,"Delete");

            //dowhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if(mListener!=null){
                int position =getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    switch(menuItem.getItemId()){

                       /* case 1 :
                            mListener.onWhatEverClick(position);
                            return  true;*/
                        case 1:
                            mListener.onDeleteClick(position);
                            return  true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener{

        void onItemClick(int position);
        // void onWhatEverClick(int position);
        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }



}
