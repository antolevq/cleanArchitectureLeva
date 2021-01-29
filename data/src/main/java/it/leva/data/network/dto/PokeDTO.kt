package it.leva.data.network.dto

import com.google.gson.annotations.SerializedName


data class PokeDetailDTO(
    @SerializedName("stats") val statDTOS: List<StatDTO>,
    @SerializedName("types") val typeDTOS: List<TypeDTO>,
    @SerializedName("sprites") val spriteAdImages: SpritesDTO
) : BaseResponse()

/**
 * Sprites Infos
 */

data class SpritesDTO(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backfemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontfemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?,
    @SerializedName("other") val otherImagesDTO: OtherImagesDTO
)

data class OtherImagesDTO(
    @SerializedName("dream_world") val dreamWorld: OtherImage,
    @SerializedName("official-artwork") val officialArtWork: OtherImage
)

data class OtherImage(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?
)

/**
 * Poke Type Infos
 */

data class TypeInfo(
    @SerializedName("name") val typeName: String,
    @SerializedName("url") val typeUrl: String
)

data class TypeDTO(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val typeInfo: TypeInfo
)

/**
 * Poke Stat Infos
 */

data class StatDTO(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val statInfo: StatInfo
)

data class StatInfo(
    @SerializedName("name") val statName: String,
    @SerializedName("url") val statUrl: String
)
