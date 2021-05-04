package com.ozan.cleanpokedex.data.datasource.memory

import com.google.common.truth.Truth
import com.ozan.cleanpokedex.BaseTest
import org.junit.Before
import org.junit.Test

class PokemonListPageDataSourceTest : BaseTest() {

    private val pageSize= 10
    private val dataSource= PokemonListPageDataSource(pageSize)

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `given page size, when get next offset, then return sum of previous offset and size`() {
        (0..5).forEach {
            val offset= dataSource.getOffset()
            Truth.assertThat(offset).isEqualTo(pageSize * it)
            dataSource.increasePage()
        }
    }

}