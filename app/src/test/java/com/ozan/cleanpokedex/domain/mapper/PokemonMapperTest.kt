package com.ozan.cleanpokedex.domain.mapper

import com.google.common.truth.Truth
import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.PokemonTestFake
import org.junit.Before
import org.junit.Test

class PokemonMapperTest: BaseTest(){

    private val imageBaseUrl= ""
    private val mapper= PokemonMapper(imageBaseUrl)

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `given entity list, when map to ui model list, then return ui models`() {
        val modelList= PokemonTestFake.createEntityList()
        val uiModelList= mapper.toUiModelList(modelList)
        Truth.assertThat(uiModelList.size).isEqualTo(modelList.size)
        Truth.assertThat(uiModelList.first().id).isEqualTo(modelList.first().id)
        Truth.assertThat(uiModelList.last().id).isEqualTo(modelList.last().id)
    }

    @Test
    fun `given detail response when map to ui model then return PokemonDetailUiModel`() {
        val result= mapper.toDetailUiModel(PokemonTestFake.detailResponse)

        Truth.assertThat(result.statList.first().progress).isEqualTo(0.5f)

    }

}