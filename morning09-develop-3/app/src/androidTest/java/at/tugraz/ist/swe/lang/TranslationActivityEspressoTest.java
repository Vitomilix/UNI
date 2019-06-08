package at.tugraz.ist.swe.lang;

import static org.hamcrest.Matchers.anything;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static at.tugraz.ist.swe.lang.R.*;

@RunWith(AndroidJUnit4.class)
public class TranslationActivityEspressoTest {

    @Rule
    public ActivityTestRule<TranslationActivity> mActivityTestRule = new ActivityTestRule<>(TranslationActivity.class);

    @Test
    public void onViewElements() {
        onView(withId(R.id.btnGerman)).check(matches(isDisplayed()));
        onView(withId(R.id.btnEnglish)).check(matches(isDisplayed()));
        onView(withId(R.id.lvVocabulary)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTranslatedWord)).check(matches(isDisplayed()));
        onView(withId(R.id.btnGerman)).check(matches(withText("GERMAN")));
        onView(withId(R.id.btnEnglish)).check(matches(withText("ENGLISH")));
    }


    @Test
    public void onViewList() {

        Vocabulary vocabulary = (Vocabulary)mActivityTestRule.getActivity().vocabulary;
        vocabulary.add("Apfel", "Apple");

        ListView lvWordList = (ListView)mActivityTestRule.getActivity().findViewById(R.id.lvVocabulary);
        int lvItemsCount = lvWordList.getAdapter().getCount();

        onView(withId(id.btnGerman)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.lvVocabulary)).atPosition(lvItemsCount-1).check(matches(withText("Apfel")));
        onView(withId(id.btnEnglish)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.lvVocabulary)).atPosition(lvItemsCount-1).check(matches(withText("Apple")));
    }

    @Test
    public void onViewTranslate(){

        Vocabulary vocabulary = (Vocabulary)mActivityTestRule.getActivity().vocabulary;
        vocabulary.add("Apfel", "Apple");

        ListView lvWordList = (ListView)mActivityTestRule.getActivity().findViewById(R.id.lvVocabulary);
        int lvItemsCount = lvWordList.getAdapter().getCount();

        onData(anything())
                .inAdapterView(withId(R.id.lvVocabulary))
                .atPosition(lvItemsCount-1)
                .perform(click());
        onView(withId(R.id.tvTranslatedWord)).check(matches(withText("Apple")));

    }








}

