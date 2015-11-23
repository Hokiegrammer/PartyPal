package com.cs4634.group5.partypal;

/**
 * Created by Philip on 11/23/2015.
 */
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class InvitationViewTest extends ActivityInstrumentationTestCase2<InvitationView> {

    private InvitationView mActivity;

    public InvitationViewTest() {
        super(InvitationView.class);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void timePickerTest() throws InterruptedException {
        Thread.sleep(10000000);
        // time picker
        onView(withId(R.id.timeEditText))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withText("OK"))
                .perform(click());

        onView(withId(R.id.timeEditText))
                .check(matches(withText(new SimpleDateFormat("h:mm a").format(new Date().getTime()))));

        // date picker
        onView(withId(R.id.dateEditText))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withText("OK"))
                .perform(click());

        onView(withId(R.id.dateEditText))
                .check(matches(withText(new SimpleDateFormat("EEE, MMM d").format(new Date().getTime()))));

        // enter name, reason and place
        onView(withId(R.id.whoEditText))
                .perform(typeText("David Reese"));

        onView(withId(R.id.whatEditText))
                .perform(typeText("David's 7th Birthday Party"));

        onView(withId(R.id.whereEditText))
                .perform(typeText("5607 Maple Ridge Drive, Blacksburg, Va"));

        onView(withId(R.id.giftIdeasEditText))
                .perform(typeText("David likes robots, cars, and arts and crafts."));

        // test the restrictions hint
        onView(withId(R.id.restrictionsButton))
                .perform(click());

        onView(withText("This is a space to specify your child's dietary restrictions or allergies."))
                .check(matches(isDisplayed()));

        // type in restriction
        onView(withId(R.id.restrictionsEditText))
                .perform(typeText("David has a slight wheat allergy."));
    }
}
