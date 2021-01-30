package it.leva.data.persistence.dao

import androidx.room.*
import it.leva.data.persistence.entity.PokeEntity
import it.leva.data.persistence.entity.PokemonWithRelationsEntity
import it.leva.data.persistence.entity.SpritesAndImagesEntity

@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemon(pokeEntity: PokeEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pokeEntityList: List<PokeEntity>)

    @Delete
    fun delete(pokeEntity: PokeEntity)

    @Transaction
    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemon(): List<PokemonWithRelationsEntity>

    @Transaction
    @Query("SELECT * FROM pokemon where name IN (:idList)")
    suspend fun getAllPokemon(idList: List<String>): List<PokemonWithRelationsEntity>

    @Transaction
    @Query("SELECT * FROM pokemon WHERE name = :name")
    suspend fun getPokemon(name: String): PokemonWithRelationsEntity?

    @Query("UPDATE pokemon SET cached = 1 where name = :name")
    fun setPokemonAsChecked(name:String)
}