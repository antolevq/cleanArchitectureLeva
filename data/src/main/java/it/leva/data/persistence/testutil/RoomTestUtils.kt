package it.leva.data.persistence.testutil

import android.util.Log
import it.leva.data.persistence.entity.PokeEntity
import it.leva.data.persistence.entity.SpritesAndImagesEntity
import it.leva.data.persistence.entity.StatEntity
import it.leva.data.persistence.entity.TypeEntity

object RoomTestUtils {

    fun createPokemon(name: String) = PokeEntity(
        name = name,
        url = "",
        cached = false
    )

    fun createTypes(count: Int, name: String) =
        (0 until count).map {
            createType(id = it.toLong() + 1, name = name)
        }

    fun createStats(count: Int, name: String) =
        (0 until count).map {
            createStat(id = it.toLong() + 1, name = name)
        }

    fun createSpritesAndImages(id: Long, fkPoke: String) = SpritesAndImagesEntity(
        pk = id,
        fkPoke = fkPoke,
        backDefault = "",
        backfemale = "",
        backShiny = "",
        backShinyFemale = "",
        frontDefault = "",
        frontfemale = "",
        frontShiny = "",
        frontShinyFemale = "",
        dreamWorldDefault = "",
        dreamWorldFemale = "",
        officialArtWorkDefault = "",
        officialArtWorkFemale = "",
    )

    private fun createType(id: Long, name: String) = TypeEntity(
        pk = id,
        name = "$name$id",
        slot = 0,
        url = "",
        fkPoke = name
    )

    private fun createStat(id: Long, name: String) = StatEntity(
        pk = id,
        effort = 0,
        name = "$name$id",
        url = "",
        fkPoke = name,
        baseStat = 0
    )
}