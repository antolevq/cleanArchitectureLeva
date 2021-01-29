package it.leva.data.network.dto

import com.google.gson.annotations.SerializedName


abstract class BaseResponse {
    @SerializedName("name")
    var name: String = ""

    @SerializedName( "url")
    var url: String = ""
}