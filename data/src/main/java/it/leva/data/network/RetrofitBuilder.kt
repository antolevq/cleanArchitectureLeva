package it.leva.data.network

import it.leva.data.BuildConfig
import it.leva.data.network.services.PokeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = BuildConfig.BASE_URL

    private fun getRetrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val pokemonService: PokeService = getRetrofitService().create(PokeService::class.java)
}