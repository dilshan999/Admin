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
import com.basgeekball.awesomevalidation.AwesomeValidation;

import com.google.common.collect.Range;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group11.rentacar.Model.Booking;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;


public class BookingActivity extends AppCompatActivity {

    //TextView textModel,textCategory,textPrice;
    EditText txtCustomerName,txtCustomerPhone,txtCustomerEmail,txtCustomerDate,txtCustomerNoOfDays;
    Button btnCon;
    DatabaseReference reff;
    Booking booking;
    String tp,nd;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        awesomeValidation = new AwesomeValidation(BASIC);

        TextView textTitle = findViewById(R.id.textModel);
        TextView textPrice = findViewById(R.id.textPrice);
        TextView textTrans = findViewById(R.id.textCategory);

        String name = "Not set";
        double price = 0;
        String trans = "Not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            name = extras.getString("brand");
           // price = extras.getDouble("price");
            price = extras.getDouble("price", 0.00);
            trans = extras.getString("transmission");
        }

           String price2=String.valueOf(price);

        textTitle.setText(name);
        textPrice.setText(price2);
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

        awesomeValidation.addValidation(BookingActivity.this, R.id.editTextCustomerName, "[a-zA-Z\\s]+", R.string.err_name);
        awesomeValidation.addValidation(BookingActivity.this, R.id.editTextCustomerPhone, "(?=.*[0-9]).{10,}", R.string.err_phone);
        awesomeValidation.addValidation(BookingActivity.this, R.id.editTextCustomerEmail, "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", R.string.err_email);
        awesomeValidation.addValidation(BookingActivity.this, R.id.editTextNoDays,  Range.closed(01,12), R.string.err_no_days);
        awesomeValidation.addValidation(BookingActivity.this, R.id.editTextDate,  "[0-9][0-9][0-9][0-9]/[0-9][0-9]/[0-9][0-9]+", R.string.err_date);


        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    booking.setModel(finalTextTitle.getText().toString().trim());
                    booking.setPrice(finalTextPrice.getText().toString().trim());
                    booking.setCusName(txtCustomerName.getText().toString().trim());
                    booking.setCusPhone(txtCustomerPhone.getText().toString().trim());
                    booking.setCusEmail(txtCustomerEmail.getText().toString().trim());
                    booking.setCusDate(txtCustomerDate.getText().toString().trim());
                    booking.setCusNoOfDays(txtCustomerNoOfDays.getText().toString().trim());
                    reff.push().setValue(booking);
                    Toast.makeText(BookingActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(BookingActivity.this, PaymentActivity.class);
                    tp = finalTextPrice.getText().toString();
                    intent.putExtra("Value1", tp);
                    nd = txtCustomerNoOfDays.getText().toString();
                    intent.putExtra("Value2", nd);
                    startActivity(intent);
                    finish();

                    float inputPrice=Float.parseFloat(finalTextPrice.getText().toString());
                    float inputDays=Float.parseFloat(txtCustomerNoOfDays.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"One or more fields are invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    float checkTotal(float inputPrice,float inputDays){
        return inputPrice*inputDays;
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