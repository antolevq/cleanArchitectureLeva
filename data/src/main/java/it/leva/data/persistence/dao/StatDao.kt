package it.leva.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.leva.data.persistence.entity.StatEntity

@Dao
interface StatDao {

    @Insert
    suspend fun insertAll(statsList: List<StatEntity>)

    @Query("DELETE FROM stat WHERE fk_poke=:fkPoke")
    fun deleteRelationedStat(fkPoke: String)
}
