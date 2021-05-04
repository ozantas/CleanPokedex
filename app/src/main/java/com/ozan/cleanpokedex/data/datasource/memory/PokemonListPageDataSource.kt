package com.ozan.cleanpokedex.data.datasource.memory

class PokemonListPageDataSource constructor(
    val pageSize: Int = 50
) {

    companion object {
        private var currentPage = 0
    }

    fun increasePage() {
        currentPage++
    }

    fun getOffset(): Int {
        return pageSize * currentPage
    }

}