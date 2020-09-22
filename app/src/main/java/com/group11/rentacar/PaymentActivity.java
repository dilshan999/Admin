package com.group11.rentacar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText editCardName;
    private EditText editCardNumber;
    private EditText editMonth;
    private EditText editYear;
    private EditText editCvc;
    private Button btnPay;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCardName = findViewById(R.id.txtname);
        editCardNumber = findViewById(R.id.txtcard);
        editMonth = findViewById(R.id.txtmonth);
        editYear = findViewById(R.id.txtyear);
        editCvc = findViewById(R.id.txtcvc);
        btnPay = findViewById(R.id.btnPay);
        btnCancel = findViewById(R.id.btnCancel);



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setMessage("Are you sure?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                }).setNegativeButton("Cancel",null);

                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment();
            }
        });



    }

    private boolean validateName(){
        String val = editCardName.getText().toString();

        if (val.isEmpty()){
            editCardName.setError("Field cannot be empty");
            return false;
        }
        else {
            editCardName.setError(null);
            return true;
        }

    }

    public void payment(){

        if (!validateName()){
            return;
        }

    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        builder.setTitle("Really Exit?").setMessage("Are you sure?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                PaymentActivity.super.onBackPressed();

            }
        }).setNegativeButton("Cancel",null).setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }

}
