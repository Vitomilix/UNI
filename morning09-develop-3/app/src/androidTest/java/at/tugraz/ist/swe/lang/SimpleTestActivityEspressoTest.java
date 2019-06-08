package at.tugraz.ist.swe.lang;

import static org.hamcrest.Matchers.anything;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class SimpleTestActivityEspressoTest {

    @Rule
    public ActivityTestRule<SimpleTestActivity> mActivityTestRule = new ActivityTestRule<>(SimpleTestActivity.class);

    @Test
    public void onViewElements() {
        onView(withId(R.id.score)).check(matches(isDisplayed()));
        onView(withId(R.id.question)).check(matches(isDisplayed()));
        onView(withId(R.id.multiple)).check(matches(isDisplayed()));

    }

    @Test
    public void checkRightAnswer(){

        Vocabulary vocab = (Vocabulary)mActivityTestRule.getActivity().vocabulary;
        int score = (int)mActivityTestRule.getActivity().score;

        TextView questionTxt = mActivityTestRule.getActivity().findViewById(id.tvQuestion);

        String translateWord = questionTxt.getText().toString();
        String answer = vocab.getTranslation("german", translateWord);

        onView(withText(answer))
                .perform(click());

        onView(withId(id.score)).check(matches(withText("Score: "+ ++score)));
    }

    @Test
    public void checkInvalidAnswer() {

        Vocabulary vocab = (Vocabulary) mActivityTestRule.getActivity().vocabulary;

        int score = (int) mActivityTestRule.getActivity().score;

        TextView questionTxt = mActivityTestRule.getActivity().findViewById(id.tvQuestion);

        String translateWord = questionTxt.getText().toString();
        System.out.println(translateWord);
        String answer = vocab.getTranslation("german", translateWord);
        System.out.println(answer);

        ArrayAdapter adapter = (ArrayAdapter) mActivityTestRule.getActivity().tvAnswers.getAdapter();

        if (adapter.getItem(0).toString() == answer)
        {
            onData(anything())
                    .inAdapterView(withId(R.id.multiple))
                    .atPosition(1)
                    .perform(click());
        }
        else
        {
            onData(anything())
                    .inAdapterView(withId(R.id.multiple))
                    .atPosition(0)
                    .perform(click());
        }

        onView(withId(id.score)).check(matches(withText("Score: "+ score)));

    }
}