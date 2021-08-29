package com.ozan.cleanpokedex.data.datasource.memory

import com.google.common.truth.Truth
import org.junit.Assert.*

import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.PokemonTestFake

import org.junit.Before
import org.junit.Test

class PokemonDetailDataSourceTest {

    private lateinit var dataSource: PokemonDetailDataSource

    @Before
    fun setUp() {
        dataSource= PokemonDetailDataSource()
    }

    @Test
    fun `given empty cache when get detail return null`() {
        val result= dataSource.getDetail("")
        Truth.assertThat(result).isNull()
    }

    @Test
    fun `given detail response when get detail return cached data`() {
        val response= PokemonTestFake.detailResponse
        val name= response.name
        dataSource.save(name,response)
        val result= dataSource.getDetail(name)
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun `given multiple detail response when get detail return correct cached data`() {
        val name= "some pokemon"
        val response1= PokemonTestFake.detailResponse
        val response2= PokemonTestFake.detailResponse.copy(name = name)

        dataSource.save(response1.name,response1)
        dataSource.save(name,response2)

        val result= dataSource.getDetail(name)
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result!!.name).isEqualTo(name)
    }

}