package com.group11.rentacar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AfterPaymentActivity extends AppCompatActivity {

    Button home,btnDelete;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_payment);

        home = findViewById(R.id.btnhome);

        final  String i1 = getIntent().getExtras().getString("Value1");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterPaymentActivity.this,HomePageActivity.class);
                startActivity(intent);

            }
        });

        btnDelete = findViewById(R.id.btnOrderCancel);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AfterPaymentActivity.this,R.style.Theme_MaterialComponents_DayNight_Dialog_Alert_Bridge);
                builder.setMessage("Are you sure?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mReference = FirebaseDatabase.getInstance().getReference().child("Payment");
                        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(i1)){
                                    mReference = FirebaseDatabase.getInstance().getReference("Payment").child(i1);
                                    mReference.removeValue();

                                    Toast.makeText(getApplicationContext(),"Order Successfully Deleted",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(AfterPaymentActivity.this,HomePageActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }).setNegativeButton("Cancel",null);

                AlertDialog alert = builder.create();

                alert.show();


            }


        });

    }

}