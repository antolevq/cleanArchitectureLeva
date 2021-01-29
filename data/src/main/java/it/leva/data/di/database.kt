package it.leva.data.di

import androidx.room.Room
import it.leva.data.persistence.AppDatabase
import it.leva.data.persistence.datasource.CachedPokemonDataSource
import it.leva.data.persistence.datasource.CachedPokemonDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().getPokeDao() }
    single { get<AppDatabase>().getSpritesAndImagesDao() }
    single { get<AppDatabase>().getStatDao() }
    single { get<AppDatabase>().getTypeDao() }

    factory<CachedPokemonDataSource> {
        CachedPokemonDataSourceImpl(
            get(),
            get(),
            get(),
            get()
        )
    }


}