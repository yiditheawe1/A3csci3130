package com.acme.a3csci3130;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Rule;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import android.support.test.rule.ActivityTestRule;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.support.test.espresso.Espresso;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.replaceText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleInstrumentedTest {

    private DatabaseReference mDatabase;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);
    private MainActivity mainActivity;

    @Before
    public void setUp() throws InterruptedException {
        mainActivity = mActivityRule.getActivity();
        mDatabase = FirebaseDatabase.getInstance().getReference("contacts");
        mDatabase.removeValue();
    }

    //@Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }
    @Test
    public void a_creatContact() throws InterruptedException {

        Espresso.onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        Espresso.onView(withId(R.id.name)).perform(typeText("Joey"), closeSoftKeyboard());
        //Thread.sleep(500);

        Espresso.onView(withId(R.id.number)).perform(typeText("123456789"), closeSoftKeyboard());
        //Thread.sleep(500);
        Espresso.onView(withId(R.id.primary)).perform((typeText("Fisher")), closeSoftKeyboard());
        //Thread.sleep(200);
        //leave everything else empty
        Espresso.onView(withId(R.id.submitButton)).perform(click());
        //Thread.sleep(200);
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
    }

    @Test
    public  void c_deleteContact() throws InterruptedException{
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.deleteButton)).perform(click());
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).equals(null);
    }

    //@Test
    public  void b_updateContact() throws InterruptedException{
        Thread.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.number)).perform(replaceText("123456789"));
        closeSoftKeyboard();
        onView(withId(R.id.name)).perform(replaceText("Joey"));
        closeSoftKeyboard();
        onView(withId(R.id.primary)).perform(replaceText("Fisher"));
        closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());
        Thread.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.number)).check(matches(withText("123456789")));
        onView(withId(R.id.name)).check(matches(withText("Joey")));
        onView(withId(R.id.primary)).check(matches(withText("Fisher")));
    }

}
