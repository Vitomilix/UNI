package at.tugraz.ist.swe.lang;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.anything;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class AddWordsActivityEspressoTest {
    @Rule
    public ActivityTestRule<AddWordsActivity> addWordsActivityTestRule = new ActivityTestRule<>(AddWordsActivity.class);

    String toast = "Empty input!";

    @Test
    public void testElementsVisible() {

        onView(withId(R.id.btnAdd)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAdd)).check(matches(withText("ADD")));
        onView(withId(R.id.ptAddEnglish)).check(matches(isDisplayed()));
        onView(withId(R.id.ptAddGerman)).check(matches(isDisplayed()));
        onView(withId(R.id.lvWordList)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddWordsVisible() {
        String inputEnglish = "Apple";
        String inputGerman = "Apfel";
        String display = inputEnglish + " : " + inputGerman;

        onView(withId(R.id.ptAddEnglish)).perform(typeText(inputEnglish));
        onView(withId(R.id.ptAddGerman)).perform(typeText(inputGerman));

        closeSoftKeyboard();

        onView(withId(R.id.btnAdd)).perform(click());

        ListView lvWordList = (ListView)addWordsActivityTestRule.getActivity().findViewById(R.id.lvWordList);
        int lvItemsCount = lvWordList.getAdapter().getCount();

        onData(anything())
                .inAdapterView(withId(R.id.lvWordList))
                .atPosition(lvItemsCount-1)
                .check(matches(withText(display)));
    }

    @Test
    public void testEmptyGermanPtToastVisible() {
        String inputEnglish = "Apple";

        onView(withId(R.id.ptAddEnglish)).perform(typeText(inputEnglish));

        closeSoftKeyboard();

        onView(withId(R.id.btnAdd)).perform(click());

        AddWordsActivity activity = addWordsActivityTestRule.getActivity();
        onView(withText(toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyEnglishPtToastVisible() {
        String inputGerman = "Apfel";

        onView(withId(R.id.ptAddGerman)).perform(typeText(inputGerman));

        closeSoftKeyboard();

        onView(withId(R.id.btnAdd)).perform(click());

        AddWordsActivity activity = addWordsActivityTestRule.getActivity();
        onView(withText(toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyPtsToastVisible() {
        closeSoftKeyboard();

        onView(withId(R.id.btnAdd)).perform(click());

        AddWordsActivity activity = addWordsActivityTestRule.getActivity();
        onView(withText(toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

}
