package com.camacho.rickandmortyapp.domain.model


import com.google.gson.annotations.SerializedName

data class OriginDomain(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)