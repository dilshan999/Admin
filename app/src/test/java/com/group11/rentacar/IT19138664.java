package com.group11.rentacar;

import com.group11.rentacar.Model.Payment;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IT19138664 {

    private static PaymentActivity paymentActivity;
    private float total;
    //private int days;

    @BeforeClass                        //called only once before testing
    public static void initMain(){
        paymentActivity=new PaymentActivity();
    }

    @Before
    public void setUp(){
        total=0.0f;
        //days=0;
    }


    @Test
    public void total_isCorrect() {
        total= paymentActivity.total("1000","5");
        assertEquals(5000.0, total,0.1);
    }



    @AfterClass
    public static void clearAll(){
        paymentActivity=null;
    }

}
