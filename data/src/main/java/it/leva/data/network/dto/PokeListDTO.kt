package it.leva.data.network.dto

import com.google.gson.annotations.SerializedName

data class PokeListDTO(
    @SerializedName("count") var totalItemCount: Int,
    @SerializedName("next") var nextPage: String?,
    @SerializedName("previous") var previousPage: String?,
    @SerializedName("results") var pokeListDTO: List<PokeItemDTO>
)

class PokeItemDTO : BaseResponse()
