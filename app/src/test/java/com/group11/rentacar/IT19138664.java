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

    @BeforeClass
    public static void initMain(){
        paymentActivity=new PaymentActivity();
    }

    @Before
    public void setUp(){
        total=0.0f;
    }


    @Test
    public void total_isCorrect() {
        total= paymentActivity.total("1000","5");
        assertEquals(5000.0, total,0.1);
    }

    @After
    public void clear(){
        total=0.0f;
    }

    @AfterClass
    public static void clearAll(){
        paymentActivity=null;
    }

}
