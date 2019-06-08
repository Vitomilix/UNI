package at.tugraz.ist.swe.lang;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainAppSurfaceElementsVisibleTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testButtonsVisible() {

        onView(withId(R.id.btMainAddWords)).check(matches(isDisplayed()));
        onView(withId(R.id.btMainAddWords)).check(matches(withText("Add Words")));
        onView(withId(R.id.main_btn_translate)).check(matches(isDisplayed()));
        onView(withId(R.id.main_btn_translate)).check(matches(withText("Translate Words")));
        onView(withId(R.id.btnCategory)).check(matches(isDisplayed()));
        onView(withId(R.id.btnCategory)).check(matches(withText("Show All Words")));
        onView(withId(R.id.btnSettings)).check(matches(withText("Settings")));
    }
}
