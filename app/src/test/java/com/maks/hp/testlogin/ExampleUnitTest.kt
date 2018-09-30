package com.maks.hp.testlogin

import com.maks.hp.testlogin.extensions.isValidEmail
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun emailExt_isCorrect() {
        assertEquals(true, "name@email.com".isValidEmail())
    }
    @Test
    fun emailExt_isNotCorrect() {
        assertEquals(false, "nameemailcom".isValidEmail())
    }
}
