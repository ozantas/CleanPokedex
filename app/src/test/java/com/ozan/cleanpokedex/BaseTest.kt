package com.ozan.cleanpokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule

open class BaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = MainCoroutineRule()
    val testScope = TestCoroutineScope(coroutineRule.testDispatcher)

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }

    @ExperimentalCoroutinesApi
    protected fun MainCoroutineRule.runTest(block: suspend () -> Unit) =
        testDispatcher.runBlockingTest {
            block()
        }
}