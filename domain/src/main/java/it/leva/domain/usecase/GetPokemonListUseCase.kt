package it.leva.domain.usecase

import it.leva.domain.repository.PokeRepository

class GetPokemonListUseCase(private val pokemonRepository: PokeRepository) {
    fun invoke(limit: Int, offset: Int) = pokemonRepository.getPokemonList(
        limit = limit,
        offset = offset
    )
}