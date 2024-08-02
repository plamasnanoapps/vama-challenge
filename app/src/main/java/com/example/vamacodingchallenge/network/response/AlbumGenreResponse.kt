package com.example.vamacodingchallenge.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlbumGenreResponse(
    @SerializedName("genreId") val genreId: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Serializable

