package it.leva.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "type",
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
data class TypeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk")
    var pk: Long?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "slot")
    var slot: Int?,
    @ColumnInfo(name = "url")
    var url: String?,
    @ColumnInfo(name = "fk_poke")
    var fkPoke: String?
)
