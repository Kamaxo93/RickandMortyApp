package com.camacho.rickandmortyapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("created")
    val created: String?,
    @SerializedName("episode")
    val episode: List<String?>?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: LocationDTO?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: OriginDTO?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)