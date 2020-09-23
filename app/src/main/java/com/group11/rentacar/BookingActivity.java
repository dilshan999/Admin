package com.group11.rentacar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BookingActivity extends AppCompatActivity {

    //TextView textModel,textCategory,textPrice;
    EditText txtCustomerName,txtCustomerPhone,txtCustomerEmail,txtCustomerDate,txtCustomerNoOfDays;
    Button btnCon;
    DatabaseReference reff;
    Booking booking;
    String tp,nd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        TextView textTitle = findViewById(R.id.textModel);
        TextView textPrice = findViewById(R.id.textPrice);
        TextView textTrans = findViewById(R.id.textCategory);

        String name = "Not set";
        String price = "Not set";
        String trans = "Not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            name = extras.getString("name");
            price = extras.getString("price");
            trans = extras.getString("transmission");
        }

        textTitle.setText(name);
        textPrice.setText(price);
        textTrans.setText(trans);

        textTitle = findViewById(R.id.textModel);
        textPrice = findViewById(R.id.textPrice);
        txtCustomerName = (EditText)findViewById(R.id.editTextCustomerName);
        txtCustomerPhone = (EditText)findViewById(R.id.editTextCustomerPhone);
        txtCustomerEmail = (EditText)findViewById(R.id.editTextCustomerEmail);
        txtCustomerDate = (EditText)findViewById(R.id.editTextDate);
        txtCustomerNoOfDays = (EditText)findViewById(R.id.editTextNoDays);
        btnCon = (Button) findViewById(R.id.btnContinue);
        booking = new Booking();
        reff = FirebaseDatabase.getInstance().getReference().child("Booking");
        final TextView finalTextTitle = textTitle;
        final TextView finalTextPrice = textPrice;


        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking.setModel(finalTextTitle.getText().toString().trim());
                booking.setPrice(finalTextPrice.getText().toString().trim());
                booking.setCusName(txtCustomerName.getText().toString().trim());
                booking.setCusPhone(txtCustomerPhone.getText().toString().trim());
                booking.setCusEmail(txtCustomerEmail.getText().toString().trim());
                booking.setCusDate(txtCustomerDate.getText().toString().trim());
                booking.setCusNoOfDays(txtCustomerNoOfDays.getText().toString().trim());
                reff.push().setValue(booking);
                Toast.makeText(BookingActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BookingActivity.this,PaymentActivity.class);
                tp= finalTextPrice.getText().toString();
                intent.putExtra("Value1",tp);
                nd= txtCustomerNoOfDays.getText().toString();
                intent.putExtra("Value2",nd);
                startActivity(intent);
                finish();
            }
        });

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