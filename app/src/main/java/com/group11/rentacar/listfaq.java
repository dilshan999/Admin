package com.group11.rentacar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class listfaq extends AppCompatActivity {



    RecyclerView review ;
    myadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqlistview);


        review= findViewById(R.id.review);
        review.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<model> options
                = new FirebaseRecyclerOptions.Builder<model>().setQuery(FirebaseDatabase.getInstance().getReference().child("Datain"),model.class ).build();

        adapter=new myadapter(options);
        review.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}