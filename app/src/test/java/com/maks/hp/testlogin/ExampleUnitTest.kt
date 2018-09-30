package com.maks.hp.testlogin

import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkRegularPassword() {
        val testParameter = "123qweZ"
        testParameter.length in 6..40 &&
                Pattern.compile("[a-zA-Z0-9]+").matcher(testParameter).matches()
    }
}
