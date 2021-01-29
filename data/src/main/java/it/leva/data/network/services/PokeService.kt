package it.leva.data.network.services

import it.leva.data.network.dto.PokeDetailDTO
import it.leva.data.network.dto.PokeListDTO
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeService {

    @GET("pokemon")
    suspend fun getPokeList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokeListDTO

    @GET
    suspend fun getPokeListFromUrl(
        @Url url: String
    ): PokeListDTO

    @GET
    suspend fun getPokeDetail(
        @Url url: String
    ): PokeDetailDTO


}