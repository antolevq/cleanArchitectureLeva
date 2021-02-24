package it.leva.di

import it.leva.domain.usecase.GetPokemonDetailUseCase
import it.leva.domain.usecase.GetPokemonListUseCase
import it.leva.domain.usecase.GetPokemonNextPageListUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetPokemonListUseCase(get()) }
    factory { GetPokemonDetailUseCase(get()) }
    factory { GetPokemonNextPageListUseCase(get()) }
}