package com.camacho.rickandmortyapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class OriginDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)