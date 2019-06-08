package at.tugraz.ist.swe.lang;


import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

public class CategorizationActivityEspressoTest {
    @Rule
    public ActivityTestRule<CategorizationActivity> CategorizationActivityTestRule = new ActivityTestRule<>(CategorizationActivity.class);

    String toast = "No language selected!";

    @Test
    public void testElementsVisible() {
        onView(withId(R.id.btnAlphabeticSort)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAlphabeticSort)).check(matches(withText("Sort")));
        onView(withId(R.id.spLanguageSort)).check(matches(isDisplayed()));
        onView(withId(R.id.spTagSort)).check(matches(isDisplayed()));
        onView(withId(R.id.lvVocabulary)).check(matches(isDisplayed()));
    }

    @Test
    public void testToastMessage() {

        onView(withId(R.id.spLanguageSort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Select language"))).perform(click());

        CategorizationActivity activity = CategorizationActivityTestRule.getActivity();

        onView(withId(R.id.btnAlphabeticSort)).perform(click());

        onView(withText(toast)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @Test
    public void testLanguageSpinnerView() {

        //check if "Select language" text at the spinner is right
        onView(withId(R.id.spLanguageSort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Select language"))).perform(click());
        onView(withId(R.id.spLanguageSort)).check(matches(withSpinnerText(containsString("Select language"))));

        //check if "german" text at the spinner is is right
        onView(withId(R.id.spLanguageSort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("german"))).perform(click());
        onView(withId(R.id.spLanguageSort)).check(matches(withSpinnerText(containsString("german"))));

        //check if "english" text at the spinner is is right
        onView(withId(R.id.spLanguageSort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("english"))).perform(click());
        onView(withId(R.id.spLanguageSort)).check(matches(withSpinnerText(containsString("english"))));

    }


}
