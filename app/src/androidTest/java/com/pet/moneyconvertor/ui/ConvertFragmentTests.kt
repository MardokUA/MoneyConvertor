package com.pet.moneyconvertor.ui


import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.internal.ContextUtils.getActivity
import com.pet.moneyconvertor.R
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ConvertFragmentTests {
    @Before
    fun setup() {
       ActivityScenario.launch(MainActivity::class.java)

    }
    @Test
    fun leftCurrencyButtonIsDisplayed() {
        onView(withId(R.id.buttonLeft)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonLeft)).check(matches(isClickable()))
        onView(withId(R.id.buttonLeft)).check(matches(isEnabled()))
    }

    @Test
    fun rightCurrencyButtonIsDisplayed() {
        val button = R.id.buttonRight
        onView(withId(button)).check(matches(isDisplayed()))
        onView(withId(button)).check(matches(isClickable()))
        onView(withId(button)).check(matches(isEnabled()))
    }

    @Test
    fun textEditValueIsNotFocusable() {
        onView(withId(R.id.editTextValue)).check(matches(isNotFocusable()))
    }
    @Test
    fun textEditValueClickWhileIsNotFocusable() {
        onView(withId(R.id.editTextValue)).perform(click())
        onView(withText(R.string.enabled_toast_message)).inRoot(
            withDecorView(
                not(
                    `is`(
                        getActivity(ApplicationProvider.getApplicationContext())?.window?.decorView
                    )
                )
            )
        ).check(
            matches(
                isDisplayed()
            )
        )
    }
    @Test
    fun selectCurrency() {

    }
}