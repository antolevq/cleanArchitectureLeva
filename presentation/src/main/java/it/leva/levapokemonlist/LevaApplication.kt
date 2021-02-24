package it.leva.levapokemonlist

import android.app.Application
import it.leva.di.apiModule
import it.leva.di.databaseModule
import it.leva.di.mapperModule
import it.leva.di.useCasesModule
import it.leva.levapokemonlist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class LevaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LevaApplication)

            loadKoinModules(
                listOf(
                    databaseModule,
                    mapperModule,
                    apiModule,
                    useCasesModule,
                    viewModelModule
                )
            )
        }
    }
}