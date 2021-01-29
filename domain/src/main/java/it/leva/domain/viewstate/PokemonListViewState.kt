package it.leva.domain.viewstate

import it.leva.domain.model.Pokemon

data class PokemonListViewState(
    val pokemonList: List<Pokemon>? = null,
    val nextPage: String?=null
)