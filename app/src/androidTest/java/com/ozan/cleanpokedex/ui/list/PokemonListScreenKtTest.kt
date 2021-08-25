package com.ozan.cleanpokedex.ui.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.ozan.cleanpokedex.ui.theme.PokedexTheme
import org.junit.Rule
import org.junit.Test

class PokemonListTest {

    val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun loadList() {
        composeRule.setContent {
            PokedexTheme {
                PokemonListContent(
                    pokemonList = TestFake.uiModelList,
                    onItemClicked = {},
                    onScroll = {}
                )
            }
        }

        composeRule.onNodeWithText(TestFake.pokemonNameList.first()).assertIsDisplayed()
    }

}


