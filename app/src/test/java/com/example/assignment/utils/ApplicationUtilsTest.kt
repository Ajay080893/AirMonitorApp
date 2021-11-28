package com.example.assignment.utils






import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApplicationUtilsTest {

    @Test
    fun validateDecimal(){
        val aqi = "181.95"
        val result = ApplicationUtils.decimal(aqi.toDouble())
        assertThat(result).isEqualTo(aqi)
    }



}