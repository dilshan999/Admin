package com.group11.rentacar;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class IT19243818 {

    //2
    @Rule
    public ActivityTestRule<HomePageActivity> homePageActivityActivityTestRule = new ActivityTestRule<HomePageActivity>(HomePageActivity.class);
    private HomePageActivity homePageActivity = null;

    @Before
    public void setUp2() throws Exception {
        homePageActivity = homePageActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = homePageActivity.findViewById(R.id.recyclerview);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        homePageActivity = null;
    }


    //3


}
