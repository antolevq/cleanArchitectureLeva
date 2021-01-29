package it.leva.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.leva.data.persistence.entity.TypeEntity

@Dao
interface TypeDao {

    @Insert
    suspend fun insertAll(typeList: List<TypeEntity>)

    @Query("DELETE FROM type WHERE fk_poke=:fkPoke")
    fun deleteRelationedType(fkPoke: String)
}