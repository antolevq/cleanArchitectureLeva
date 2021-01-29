package it.leva.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.leva.data.persistence.entity.SpritesAndImagesEntity

@Dao
interface SpritesAndImagesDao {

    @Insert
    suspend fun insert(listOfImages: SpritesAndImagesEntity)

    @Query("DELETE FROM sprites_and_images WHERE fk_poke=:fkPoke")
    fun deleteRelationedImage(fkPoke: String)
}