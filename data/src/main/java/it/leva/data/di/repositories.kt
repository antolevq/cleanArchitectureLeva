package it.leva.data.di

import it.leva.data.network.RetrofitBuilder
import it.leva.data.repository.PokeRepositoryImpl
import it.leva.domain.repository.PokeRepository
import org.koin.dsl.module

val apiModule = module {
    single { RetrofitBuilder.pokemonService }

    factory<PokeRepository> { PokeRepositoryImpl(get(), get(), get(), get(), get(), get(), get()) }
}