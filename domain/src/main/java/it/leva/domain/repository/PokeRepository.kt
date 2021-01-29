package it.leva.domain.repository

import it.leva.domain.state.DataState
import it.leva.domain.viewstate.PokemonListViewState
import it.leva.domain.viewstate.PokemonViewState
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<DataState<PokemonListViewState>>
    fun getPokemonDetail(name: String, url: String): Flow<DataState<PokemonViewState>>
    fun getNextPokemon(nextUrl: String): Flow<DataState<PokemonListViewState>>
}