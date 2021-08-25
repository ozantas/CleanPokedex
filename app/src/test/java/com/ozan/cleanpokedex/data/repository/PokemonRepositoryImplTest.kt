package com.ozan.cleanpokedex.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.PokemonTestFake
import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.data.datasource.database.PokedexDatabase
import com.ozan.cleanpokedex.data.datasource.database.dao.PokemonDao
import com.ozan.cleanpokedex.data.datasource.memory.PokemonDetailDataSource
import com.ozan.cleanpokedex.data.datasource.network.PokedexApi
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListResponse
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@Config(manifest= Config.NONE)
@RunWith(AndroidJUnit4::class)
class PokemonRepositoryImplTest : BaseTest() {

    private lateinit var pokemonDao: PokemonDao
    private lateinit var db: PokedexDatabase
    private lateinit var repository: PokemonRepository

    @MockK
    private lateinit var pokedexApi: PokedexApi
    private lateinit var detailDataSource: PokemonDetailDataSource
    private lateinit var pokemonMapper: PokemonMapper
    private val jsonParser= Json {
        ignoreUnknownKeys = true
    }

    @Before
    override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PokedexDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        pokemonDao = db.pokemonDao()
        pokemonMapper = PokemonMapper("")
        detailDataSource= PokemonDetailDataSource()
        repository = PokemonRepositoryImpl(pokedexApi, detailDataSource, pokemonDao, pokemonMapper)
    }

    @Test
    fun `given success response when get page return list`() = coroutineRule.runTest {
        val pageSize = 10
        val page1Response = jsonParser.decodeFromString<PokemonListResponse>(PokemonTestFake.page1Json)
        val page2Response = jsonParser.decodeFromString<PokemonListResponse>(PokemonTestFake.page2Json)

        val entityList= repository.getPokemonList(pageSize *2, 0)
        Truth.assertThat(entityList).isInstanceOf(Resource.Success::class.java)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}