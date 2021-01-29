package it.leva.data.persistence.mapper

import it.leva.data.network.dto.OtherImagesDTO
import it.leva.data.network.dto.SpritesDTO
import it.leva.data.persistence.entity.SpritesAndImagesEntity

class SpritesAndImagesEntityMapper {

    fun mapToEntity(from: SpritesDTO) =
        SpritesAndImagesEntity(
            pk = null,
            backDefault = from.backDefault,
            backfemale = from.backfemale,
            backShiny = from.backShiny,
            backShinyFemale = from.backShinyFemale,
            frontDefault = from.frontDefault,
            frontfemale = from.frontfemale,
            frontShiny = from.frontShiny,
            frontShinyFemale = from.frontShinyFemale,
            dreamWorldDefault = from.otherImagesDTO.dreamWorld.frontDefault ?: "",
            dreamWorldFemale = from.otherImagesDTO.dreamWorld.frontFemale ?: "",
            officialArtWorkDefault = from.otherImagesDTO.officialArtWork.frontDefault ?: "",
            officialArtWorkFemale = from.otherImagesDTO.officialArtWork.frontFemale ?: "",
            fkPoke = ""
        )

    fun mapSpritesToDomainList(from: SpritesAndImagesEntity?): List<String?> {
        from?.let {
            return listOf(
                it.backDefault,
                it.backfemale,
                it.backShiny,
                it.backShinyFemale,
                it.frontDefault,
                it.frontfemale,
                it.frontShiny,
                it.frontShinyFemale
            ).filter { it.isNullOrEmpty().not() }
        }

        return listOf()
    }


}