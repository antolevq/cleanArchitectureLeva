package it.leva.data.persistence.mapper

import it.leva.data.persistence.entity.PokemonWithRelationsEntity
import it.leva.domain.model.Pokemon

class PokemonWithRelationsEntityMapper(
    private val spritesAndImagesEntityMapper: SpritesAndImagesEntityMapper,
    private val statEntityEntityMapper: StatEntityEntityMapper,
    private val typeEntityEntityMapper: TypeEntityEntityMapper
) {

    fun mapToDomain(from: List<PokemonWithRelationsEntity>) =
        from.map {
            mapToDomain(it)
        }

    fun mapToDomain(from: PokemonWithRelationsEntity) =
        Pokemon(
            name = from.pokeEntity.name,
            url = from.pokeEntity.url,
            stats = statEntityEntityMapper.mapToDomain(from.statEntityList),
            types = typeEntityEntityMapper.mapToDomain(from.typeEntityList),
            sprites = spritesAndImagesEntityMapper.mapSpritesToDomainList(from.spritesAndImagesEntity),
            dreamWorldDefault = from.spritesAndImagesEntity?.dreamWorldDefault,
            dreamWorldFemale = from.spritesAndImagesEntity?.dreamWorldFemale,
            officialArtWorkDefault = from.spritesAndImagesEntity?.officialArtWorkDefault,
            officialArtWorkFemale = from.spritesAndImagesEntity?.officialArtWorkFemale,
        )
}