package at.tugraz.ist.swe.lang;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class OpenRatingActivityEspressoTest {
    @Rule
    public ActivityTestRule<AddWordsActivity> openRatingActivityTestRule = new ActivityTestRule<>(AddWordsActivity.class);

    String toast = "Empty input!";

    @Test
    public void testElementsVisible() {

        // Just basic implementation
        onView(withId(R.id.btnAdd)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAdd)).check(matches(withText("ADD")));
        onView(withId(R.id.ptAddEnglish)).check(matches(isDisplayed()));
        onView(withId(R.id.ptAddGerman)).check(matches(isDisplayed()));
        onView(withId(R.id.lvWordList)).check(matches(isDisplayed()));

    }






}
