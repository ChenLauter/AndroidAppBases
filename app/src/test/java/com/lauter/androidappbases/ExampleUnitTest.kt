package com.lauter.androidappbases

import com.lauter.androidappbases.common.transformation.CircleBorder
import com.lauter.androidappbases.common.utils.ColorUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val circle1 = CircleBorder(10f,10)
        val circle2 = CircleBorder(10f,10)
        assertEquals(circle1, circle2)
    }
}