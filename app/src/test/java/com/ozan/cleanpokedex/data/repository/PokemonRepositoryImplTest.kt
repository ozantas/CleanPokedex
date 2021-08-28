package com.ozan.cleanpokedex.data.repository

import android.os.Build
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class PokemonRepositoryImplTest: BaseTest() {

    private lateinit var pokemonDao: PokemonDao
    private lateinit var db: PokedexDatabase
    private lateinit var repository: PokemonRepository

    @MockK
    private lateinit var pokedexApi: PokedexApi
    private lateinit var detailDataSource: PokemonDetailDataSource
    private lateinit var pokemonMapper: PokemonMapper
    private val parser = Json {
        ignoreUnknownKeys = true
    }

    @Before
    override fun setUp() {
        super.setUp()
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PokedexDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        pokemonDao = db.pokemonDao()
        pokemonMapper = PokemonMapper("")
        detailDataSource = PokemonDetailDataSource()
        repository = PokemonRepositoryImpl(pokedexApi, detailDataSource, pokemonDao, pokemonMapper)
    }

    @Test
    fun `given success response when get page return list and cache it`()= coroutineRule.runTest {
        val response = parser.decodeFromString<PokemonListResponse>(PokemonTestFake.page1Json)

        coEvery { pokedexApi.getPokemonList(any(), any()) } returns response

        val result = repository.getPokemonList(10, 0)
        val savedEntities= pokemonDao.getPokemonList(10,0)
        Truth.assertThat(result).isInstanceOf(Resource.Success::class.java)
        Truth.assertThat(savedEntities.size).isEqualTo(response.pokemonListItems.size)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}