package net.fengg.dagger2mvpsample.ui.view.activity;

import android.support.test.rule.ActivityTestRule;

import junit.framework.Assert;

import net.fengg.dagger2mvpsample.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by feng on 2017/6/29.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    MainActivity mainActivity;

    @Before
    public void initString(){
        mainActivity = mActivityRule.getActivity();
    }

    @Test
    public void computeTest() throws InterruptedException {
        Thread.sleep(1000);
        onView(withId(R.id.et_first)).perform(replaceText("3"), closeSoftKeyboard());//sougou键盘不正常,可使用replaceText()
        onView(withId(R.id.et_second)).perform(replaceText("4"), closeSoftKeyboard());
        onView(withId(R.id.btn_compute)).perform(click());
        onView(withId(R.id.tv_result)).check(matches(withText("7")));
        Assert.assertNotNull(mainActivity.presenter);

    }

    @Test
    public void preTest() {

    }

}