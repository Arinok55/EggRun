package com.example.eggrun.ui;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.eggrun.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EggSelectionTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void eggSelectionTest() throws InterruptedException {
        Thread.sleep(600);
        try {
            ViewInteraction appCompatEditText = onView(
                    allOf(withId(R.id.username_text),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                            0),
                                    2),
                            isDisplayed()));
            appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

            ViewInteraction appCompatEditText2 = onView(
                    allOf(withId(R.id.password_text),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                            0),
                                    4),
                            isDisplayed()));
            appCompatEditText2.perform(replaceText("1234567890"), closeSoftKeyboard());

            ViewInteraction appCompatEditText3 = onView(
                    allOf(withId(R.id.confirm_text),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                            0),
                                    6),
                            isDisplayed()));
            appCompatEditText3.perform(replaceText("1234567890"), closeSoftKeyboard());

            ViewInteraction materialButton = onView(
                    allOf(withId(R.id.create_account_button), withText("Create Account"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                            0),
                                    7),
                            isDisplayed()));
            materialButton.perform(click());
            Thread.sleep(300);
        } catch (Exception e) {

        }

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.hatchButton), withText("Hatch New Egg"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.addCommonEggButton),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
