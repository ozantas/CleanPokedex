package com.ozan.cleanpokedex.extension

import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ListExtensionKtTest {

    @Before
    fun setUp() {
    }

    @Test
    fun mergeWith() {
        val given = listOf(1,2,3)
        val result= given.mergeWith(listOf(4,5,6))
        Truth.assertThat(result).containsExactlyElementsIn(listOf(1,2,3,4,5,6))
    }
}