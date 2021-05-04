package com.ozan.cleanpokedex.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.PokemonTestFake
import com.ozan.cleanpokedex.data.datasource.database.PokedexDatabase
import com.ozan.cleanpokedex.data.datasource.database.dao.PokemonDao
import com.ozan.cleanpokedex.data.datasource.memory.PokemonListPageDataSource
import com.ozan.cleanpokedex.data.datasource.network.PokedexApi
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PokemonRepositoryImplTest : BaseTest() {

    private val pageSize= 10
    private lateinit var pokemonDao: PokemonDao
    private lateinit var db: PokedexDatabase
    private lateinit var repository: PokemonRepository

    @MockK
    private lateinit var pokedexApi: PokedexApi
    private lateinit var listPageDataSource: PokemonListPageDataSource
    private lateinit var pokemonMapper: PokemonMapper

    @Before
    override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PokedexDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        pokemonDao = db.pokemonDao()
        listPageDataSource = PokemonListPageDataSource(pageSize)
        pokemonMapper = PokemonMapper("")
        repository =
            PokemonRepositoryImpl(pokedexApi, listPageDataSource, pokemonDao, pokemonMapper)
    }

    @Test
    fun `when get pokemon list then increase page number`()= coroutineRule.runTest {
        val entityList= PokemonTestFake.createEntityList()
        pokemonDao.insert(entityList)
        repository.getPokemonList()
        Truth.assertThat(listPageDataSource.getOffset()).isEqualTo(pageSize)
        repository.getPokemonList()
        Truth.assertThat(listPageDataSource.getOffset()).isEqualTo(pageSize * 2)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}