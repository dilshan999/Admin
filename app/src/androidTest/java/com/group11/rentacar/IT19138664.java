package com.group11.rentacar;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class IT19138664 {

    @Rule
    public IntentsTestRule<BookingActivity> intentsTestRule = new IntentsTestRule<>(BookingActivity.class);


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testIntentBookingActivityToPaymentActivity() {

        onView(withId(R.id.btnContinue)).perform(click());

        intended(hasComponent(PaymentActivity.class.getName()));
    }

    @After
    public void tearDown() throws Exception {

    }



}
