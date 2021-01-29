package it.leva.data.persistence.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class PokemonWithRelationsEntity(
    @Embedded var pokeEntity: PokeEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "fk_poke"
    )
    var statEntityList: List<StatEntity>?,
    @Relation(
        parentColumn = "name",
        entityColumn = "fk_poke"
    )
    var typeEntityList: List<TypeEntity>?,
    @Relation(
        parentColumn = "name",
        entityColumn = "fk_poke"
    )
    var spritesAndImagesEntity: SpritesAndImagesEntity?

)
