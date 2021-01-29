package it.leva.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import it.leva.data.persistence.dao.PokeDao
import it.leva.data.persistence.dao.SpritesAndImagesDao
import it.leva.data.persistence.dao.StatDao
import it.leva.data.persistence.dao.TypeDao
import it.leva.data.persistence.entity.*

@Database(
    entities = [PokeEntity::class, SpritesAndImagesEntity::class, StatEntity::class, TypeEntity::class],
    version = 4
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPokeDao(): PokeDao
    abstract fun getStatDao(): StatDao
    abstract fun getSpritesAndImagesDao(): SpritesAndImagesDao
    abstract fun getTypeDao(): TypeDao

    companion object {
        const val DB_NAME = "pokemon_db"
    }
}