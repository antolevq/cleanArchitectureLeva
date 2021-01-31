package it.leva.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import it.leva.data.persistence.AppDatabase
import it.leva.data.persistence.dao.PokeDao
import it.leva.data.persistence.dao.SpritesAndImagesDao
import it.leva.data.persistence.dao.StatDao
import it.leva.data.persistence.dao.TypeDao
import it.leva.data.persistence.testutil.RoomTestUtils
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EntityWriteAndReadTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var pokeDao: PokeDao
    private lateinit var statDao: StatDao
    private lateinit var typeDao: TypeDao
    private lateinit var spritesAndImagesDao: SpritesAndImagesDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        pokeDao = appDatabase.getPokeDao()
        statDao = appDatabase.getStatDao()
        typeDao = appDatabase.getTypeDao()
        spritesAndImagesDao = appDatabase.getSpritesAndImagesDao()
    }

    @Test
    @Throws(Exception::class)
    fun testRelations() {
        runBlocking {
            val pokeName = "Test"
            pokeDao.insertPokemon(RoomTestUtils.createPokemon(pokeName))
            val counter = 3
            statDao.insertAll(
                RoomTestUtils.createStats(
                    count = counter,
                    name = pokeName
                )
            )
            typeDao.insertAll(
                RoomTestUtils.createTypes(
                    count = counter,
                    name = pokeName
                )
            )
            spritesAndImagesDao.insert(
                RoomTestUtils.createSpritesAndImages(
                    id = 1,
                    fkPoke = pokeName
                )
            )

            val res = pokeDao.getAllPokemon().first()
            assert(
                res.statEntityList?.size == counter
                        && res.typeEntityList?.size == counter
                        && res.spritesAndImagesEntity?.fkPoke == pokeName
            )
        }

    }

    @After
    fun close() {
        appDatabase.close()
    }
}