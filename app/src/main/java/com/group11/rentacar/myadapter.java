package com.group11.rentacar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model)

    {
        holder.queastion.setText(model.getQus());
        holder.answer.setText(model.getAns());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)

    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    protected void populateViewHolder(myviewholder myviewholder, model model, int i) {

    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView queastion , answer;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            queastion=(TextView)itemView.findViewById(R.id.questiontext);
            answer=(TextView)itemView.findViewById(R.id.answertext);

        }
    }

}
