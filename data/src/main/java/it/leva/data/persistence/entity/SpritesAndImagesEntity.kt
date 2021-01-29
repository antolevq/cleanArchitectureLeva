package it.leva.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sprites_and_images",
    foreignKeys = [
        ForeignKey(
            entity = PokeEntity::class,
            childColumns = ["fk_poke"],
            parentColumns = ["name"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class SpritesAndImagesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk")
    var pk: Long?,
    @ColumnInfo(name = "back_default")
    var backDefault: String?,
    @ColumnInfo(name = "back_female")
    var backfemale: String?,
    @ColumnInfo(name = "back_shiny")
    var backShiny: String?,
    @ColumnInfo(name = "back_shiny_female")
    var backShinyFemale: String?,
    @ColumnInfo(name = "front_default")
    var frontDefault: String?,
    @ColumnInfo(name = "front_female")
    var frontfemale: String?,
    @ColumnInfo(name = "front_shiny")
    var frontShiny: String?,
    @ColumnInfo(name = "front_shiny_female")
    var frontShinyFemale: String?,
    @ColumnInfo(name = "dream_world_default")
    var dreamWorldDefault: String?,
    @ColumnInfo(name = "dream_world_female")
    var dreamWorldFemale: String?,
    @ColumnInfo(name = "official-artwork_default")
    var officialArtWorkDefault: String?,
    @ColumnInfo(name = "official-artwork_female")
    var officialArtWorkFemale: String?,
    @ColumnInfo(name = "fk_poke")
    var fkPoke: String?
)
