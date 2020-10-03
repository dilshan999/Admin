package com.group11.rentacar;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IT19138664 {

    @Rule
    public ActivityTestRule<PaymentActivity> PaymentActivityTestRule = new ActivityTestRule<PaymentActivity>(PaymentActivity.class);

    private PaymentActivity pActivity = null;

    @Before
    public void setUp() throws Exception{
        pActivity = PaymentActivityTestRule.getActivity();
    }

    @Test
    public void testImageViewIsThere(){
        View view =  pActivity.findViewById(R.id.btnPay);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception{
        pActivity=null;
    }
}
