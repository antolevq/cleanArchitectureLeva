package it.leva.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "stat",
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
data class StatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk")
    var pk: Long?,
    @ColumnInfo(name = "effort")
    var effort: Int?,
    @ColumnInfo(name = "base_stat")
    var baseStat: Int?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "url")
    var url: String?,
    @ColumnInfo(name = "fk_poke")
    var fkPoke: String?
)