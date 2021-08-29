package com.ozan.cleanpokedex.extension

import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class StringExtensionKtTest {

    @Before
    fun setUp() {
    }

    @Test
    fun capitalizeFirstChar() {
        val given= "pokedex"
        val result= given.capitalizeFirstChar()
        Truth.assertThat(result).isEqualTo("Pokedex")
    }
}