package com.group11.rentacar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.common.collect.Range;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group11.rentacar.Model.Payment;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class PaymentActivity extends AppCompatActivity {

    private EditText editCardName;
    private EditText editCardNumber;
    private EditText editMonth;
    private EditText editYear;
    private EditText editCvc;
    private TextView edittotal;
    private Button btnPay;
    private Button btnCancel;
    AwesomeValidation awesomeValidation;
    DatabaseReference dref;
    Payment pyt;
    String id,tp,nd;
    String i1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        awesomeValidation = new AwesomeValidation(BASIC);

        editCardName = findViewById(R.id.txtname);
        editCardNumber = findViewById(R.id.txtcard);
        editMonth = findViewById(R.id.txtmonth);
        editYear = findViewById(R.id.txtyear);
        editCvc = findViewById(R.id.txtcvc);
        edittotal = findViewById(R.id.txttotal);
        btnPay = findViewById(R.id.btnPay);
        btnCancel = findViewById(R.id.btnCancel);

        pyt = new Payment();

        tp=getIntent().getExtras().getString("Value1");
        nd= getIntent().getExtras().getString("Value2");

        edittotal.setText(String.valueOf(total(tp,nd)));

        awesomeValidation.addValidation(PaymentActivity.this, R.id.txtname, "[a-zA-Z\\s]+", R.string.err_name);
        awesomeValidation.addValidation(PaymentActivity.this, R.id.txtcard, "(?=.*[0-9]).{16,}", R.string.err_card);
        awesomeValidation.addValidation(PaymentActivity.this, R.id.txtmonth, Range.closed(01,12), R.string.err_month);
        awesomeValidation.addValidation(PaymentActivity.this, R.id.txtyear, "[?=.*[0-9]).{4,}]+", R.string.err_year);
        awesomeValidation.addValidation(PaymentActivity.this, R.id.txtcvc, "(?=.*[0-9]).{3,}", R.string.err_cvc);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this,R.style.Theme_MaterialComponents_DayNight_Dialog_Alert_Bridge);
                builder.setMessage("Are you sure?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(PaymentActivity.this,HomePageActivity.class);
                        startActivity(intent);

                    }
                }).setNegativeButton("Cancel",null);

                AlertDialog alert = builder.create();

                alert.show();


            }

        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (awesomeValidation.validate()) {
                    String aa = editCardName.getText().toString();
                    String bb = editCardNumber.getText().toString();
                    String cc = editMonth.getText().toString();
                    String dd = editYear.getText().toString();
                    String ee = editCvc.getText().toString();

                    Toast.makeText(getApplicationContext(),"Validating Payment",Toast.LENGTH_SHORT).show();

                    dref = FirebaseDatabase.getInstance().getReference().child("Payment");

                    pyt.setName(editCardName.getText().toString().trim());
                    pyt.setCardNum(editCardNumber.getText().toString().trim());
                    pyt.setMonth(editMonth.getText().toString().trim());
                    pyt.setYear(editYear.getText().toString().trim());
                    pyt.setCvc(editCvc.getText().toString().trim());
                    pyt.setTotal(edittotal.getText().toString().trim());

                    id = editCardNumber.getText().toString();

                    dref.child(id).setValue(pyt);

                    Toast.makeText(getApplicationContext(),"Data Successfully Added",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(PaymentActivity.this,AfterPaymentActivity.class);
                    i1= editCardNumber.getText().toString();
                    intent.putExtra("Value1",i1);

                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid data",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this,R.style.Theme_MaterialComponents_DayNight_Dialog_Alert_Bridge);
        builder.setTitle("Really Exit?").setMessage("Are you sure?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                PaymentActivity.super.onBackPressed();

            }
        }).setNegativeButton("Cancel",null).setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public float total(String tp, String nd){
        float tot;

        tot = Float.valueOf(tp)*Float.valueOf(nd);
        return tot;
    }

}
