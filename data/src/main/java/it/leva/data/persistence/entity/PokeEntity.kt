package it.leva.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokeEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name ="url")
    var url: String
)
