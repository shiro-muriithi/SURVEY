package com.quickbirdstudios.test.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.quickbirdstudios.test.R
import org.hamcrest.CoreMatchers
import org.junit.Assert

internal fun PageTest.testEmailStep(
    emailInputWrong: String,
    emailInputRight: String,
    keyboardVisibilityCheck: () -> Boolean
) {
    checkIfTitleInfoAndContinueAreDisplayed()

    onView(withId(R.id.button_continue)).check(matches(CoreMatchers.not(isEnabled())))
    onView(withId(R.id.textFieldPartField)).perform(click())
    Assert.assertTrue(keyboardVisibilityCheck())

    onView(withId(R.id.textFieldPartField)).perform(typeText(emailInputWrong))
    onView(withId(R.id.button_continue)).perform(scrollTo())
    onView(withId(R.id.button_continue)).check(matches(CoreMatchers.not(isEnabled())))

    onView(withId(R.id.textFieldPartField)).perform(scrollTo())
    onView(withId(R.id.textFieldPartField)).perform(clearText(), typeText(emailInputRight))

    continueToNextStep()
}
