package it.leva.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Pokemon(
    val name: String,
    val url: String,
    val stats: List<Stat>?,
    val types: List<Type>?,
    val sprites: List<String?>?,
    val dreamWorldDefault: String?,
    val dreamWorldFemale: String?,
    val officialArtWorkDefault: String?,
    val officialArtWorkFemale: String?
) : Parcelable {
    fun isDownloaded() =
        stats.isNullOrEmpty().not()
                || types.isNullOrEmpty().not()
                || sprites.isNullOrEmpty().not()

    fun getImageList(): MutableList<String?>? {
        val listOfImages: MutableList<String?>? = sprites?.toMutableList()
        listOfImages?.let {
            it.apply {
                dreamWorldDefault?.let { image -> if (image.isNotEmpty()) add(image) }
                dreamWorldFemale?.let { image -> if (image.isNotEmpty()) add(image) }
                officialArtWorkFemale?.let { image -> if (image.isNotEmpty()) add(image) }
            }
        }

        return listOfImages?.filter { it?.endsWith("svg", true)?.not() ?: false }?.toMutableList()
    }

    fun getTypeText(): String {
        var res = ""
        types?.forEachIndexed { index, type ->
            res += type.name
            if (index < types.size - 1) {
                res += " - "
            }
        }
        return res
    }

}

@Parcelize
data class Type(
    val id: Long,
    val name: String,
    val slot: Int,
    val url: String,
    val fkPoke: String
) : Parcelable

@Parcelize
data class Stat(
    val id: Long,
    val baseStat: Int,
    val effort: Int,
    val name: String,
    val url: String,
    val fkPoke: String
) : Parcelable