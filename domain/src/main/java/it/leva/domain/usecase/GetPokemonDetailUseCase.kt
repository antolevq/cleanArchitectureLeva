package it.leva.domain.usecase

import it.leva.domain.repository.PokeRepository

class GetPokemonDetailUseCase(private val pokemonRepository: PokeRepository) {
    fun invoke(name: String, url: String) = pokemonRepository.getPokemonDetail(
        name = name,
        url = url
    )
}