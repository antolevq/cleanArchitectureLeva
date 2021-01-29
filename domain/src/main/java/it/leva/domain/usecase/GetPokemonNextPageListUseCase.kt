package it.leva.domain.usecase

import it.leva.domain.repository.PokeRepository

class GetPokemonNextPageListUseCase(private val pokemonRepository: PokeRepository) {
    fun invoke(nextUrl: String) = pokemonRepository.getNextPokemon(nextUrl)
}