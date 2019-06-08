package at.tugraz.ist.swe.lang;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.ArgumentMatchers.startsWith;

@RunWith(AndroidJUnit4.class)
public class SettingsActivityEspressoTest {

    @Rule
    public ActivityTestRule<SettingsActivity> settingsActivityTestRule = new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void testElementsVisible() {

        onView(withId(R.id.btnStoreBackup)).check(matches(isDisplayed()));
        onView(withId(R.id.btnLoadBackup)).check(matches(isDisplayed()));
    }

    @Test
    public void testSaveLoadBackupTest() {
        String backupName = "testBackup";

        onView(withId(R.id.btnStoreBackup)).perform(click());

        String backupString = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        System.out.println(backupString);

        onView(withText(containsString(backupString)))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(replaceText(backupName));

        onView(withText("Save"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.btnLoadBackup)).perform(click());

        onView(withText(backupName))
                 .check(matches(isDisplayed()));

        onView(withText("vocabulary.json"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.btnManage)).perform(click());

        onView(withText(backupName))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

    }
}
