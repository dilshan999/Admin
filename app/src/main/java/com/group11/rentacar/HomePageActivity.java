package com.group11.rentacar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import android.content.Context;

import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private ArrayList<Car> list;
    private RecyclerView recyclerView2;
    private HomePageAdapter homePageAdapter;
    private HomePageAdapter.OnItemClickListener listener;

    private Context mContext;
    public ImageView updimage;
    private ValueEventListener mDBListener;
    /*String string1[];
    int images[] = {R.drawable.eonlogo,R.drawable.axiologo1,R.drawable.minivanlogo};
    RecyclerView recyclerView2;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView2 = findViewById(R.id.recyclerview);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Car>();

        setOnClickListener();


        reference = FirebaseDatabase.getInstance().getReference().child("Vehicles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    Car ca = dataSnapshot1.getValue(Car.class);
                    list.add(ca);
                }
                homePageAdapter = new HomePageAdapter(HomePageActivity.this, list,listener);
                recyclerView2.setAdapter(homePageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


        /*string1 = getResources().getStringArray(R.array.Vehicles);
        recyclerView2 = findViewById(R.id.recyclerview);
        HomePageAdapter homePageAdapter = new HomePageAdapter(this, string1, images);
        recyclerView2.setAdapter(homePageAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));*/


        /*recyclerView2 = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Car>();
        reference = FirebaseDatabase.getInstance().getReference().child("Vehicles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1: snapshot.getChildren())
                {
                    Car ca = dataSnapshot1.getValue(Car.class);
                    list.add(ca);
                }
                homePageAdapter = new HomePageAdapter(HomePageActivity.this,list);
                recyclerView2.setAdapter(homePageAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
*/


    }

    private void setOnClickListener() {
        listener = new HomePageAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),BookingActivity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("price",list.get(position).getPrice());
                intent.putExtra("transmission",list.get(position).getTransmission());
                startActivity(intent);
            }
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}