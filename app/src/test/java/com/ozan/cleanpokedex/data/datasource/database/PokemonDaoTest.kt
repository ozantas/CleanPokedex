package com.ozan.cleanpokedex.data.datasource.database

import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.PokemonTestFake
import com.ozan.cleanpokedex.data.datasource.database.dao.PokemonDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class PokemonDaoTest: BaseTest() {

    private lateinit var pokemonDao: PokemonDao
    private lateinit var db: PokedexDatabase

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
    }

    @Test
    fun `given entity list when insert return equal amount of entities`()= coroutineRule.runTest {
        val entityList= PokemonTestFake.createEntityList()

        pokemonDao.insert(entityList)

        val result = pokemonDao.getPokemonList(entityList.size,0)
        Truth.assertThat(result.size).isEqualTo(entityList.size)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}