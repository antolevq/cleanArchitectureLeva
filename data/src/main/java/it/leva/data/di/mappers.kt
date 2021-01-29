package it.leva.data.di

import it.leva.data.persistence.mapper.*
import org.koin.dsl.module

val mapperModule = module {
    single { PokeEntityMapper() }
    single { SpritesAndImagesEntityMapper() }
    single { StatEntityEntityMapper() }
    single { TypeEntityEntityMapper() }
    single { PokemonWithRelationsEntityMapper(get(), get(), get()) }
}