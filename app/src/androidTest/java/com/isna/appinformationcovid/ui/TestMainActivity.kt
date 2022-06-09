package com.isna.appinformationcovid.ui

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.isna.appinformationcovid.MainActivity
import com.isna.appinformationcovid.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TestMainActivity{

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val materialButton = Espresso.onView(
            Matchers.allOf(
                withId(R.id.btn_register),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0
                    ),
                    6
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton.perform(ViewActions.click())

        val textInputEditText = Espresso.onView(
            Matchers.allOf(
                withId(R.id.et_nama),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_nama),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("Sulthan"),
            ViewActions.closeSoftKeyboard()
        )

        val textInputEditText2 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.et_email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_email),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText2.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("sulthan@gmail.com"),
            ViewActions.closeSoftKeyboard()
        )

        val textInputEditText3 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.et_username),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_username),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText3.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("than"),
            ViewActions.closeSoftKeyboard()
        )

        val textInputEditText4 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.et_password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_password),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText4.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("123"),
            ViewActions.closeSoftKeyboard()
        )

        val textInputEditText5 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.et_confirm_password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_confirm_password),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText5.perform(
            ViewActions.scrollTo(),
            ViewActions.replaceText("123"),
            ViewActions.closeSoftKeyboard()
        )

        val materialButton2 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.btn_register),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    7
                )
            )
        )
        materialButton2.perform(ViewActions.scrollTo(), ViewActions.click())

        val textInputEditText6 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.et_username),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_username),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textInputEditText6.perform(ViewActions.replaceText("than"), ViewActions.closeSoftKeyboard())

        val textInputEditText7 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.et_password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_password),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textInputEditText7.perform(ViewActions.replaceText("123"), ViewActions.closeSoftKeyboard())

        val materialButton3 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.btn_login),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0
                    ),
                    4
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton3.perform(ViewActions.click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
