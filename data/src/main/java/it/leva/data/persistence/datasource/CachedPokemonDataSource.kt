package it.leva.data.persistence.datasource

import it.leva.data.persistence.entity.*

interface CachedPokemonDataSource {
    suspend fun insertAllStats(statsList: List<StatEntity>)
    suspend fun insertAllTypes(typeList: List<TypeEntity>)
    suspend fun insertSpritesAndImage(listOfImages: SpritesAndImagesEntity)
    suspend fun insertPokemon(pokeEntity: PokeEntity): Long
    suspend fun insertAllPokemon(pokeEntityList: List<PokeEntity>)
    fun deletePokemon(pokeEntity: PokeEntity)
    suspend fun getAllPokemon(idList: List<String>): List<PokemonWithRelationsEntity>
    suspend fun getAllPokemon(): List<PokemonWithRelationsEntity>
    suspend fun getPokemon(name: String): PokemonWithRelationsEntity?
    fun setPokemonAsCached(name:String)


}