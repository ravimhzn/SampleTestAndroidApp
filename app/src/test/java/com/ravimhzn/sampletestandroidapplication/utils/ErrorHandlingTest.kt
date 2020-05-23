package com.ravimhzn.sampletestandroidapplication.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import java.util.*

class ErrorHandlingTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `isNetworkError Message equals Unable to resolve host return true`() {
        assertTrue(ErrorHandling.isNetworkError("Unable to resolve host"))
    }

    @Test
    fun `isNetworkError Message not equals Unable to resolve host return false`() {
        assertFalse(ErrorHandling.isNetworkError(UUID.randomUUID().toString()))
    }
}