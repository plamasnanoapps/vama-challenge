package com.example.vamacodingchallenge.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlbumResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("artistName") val artistName: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("contentAdvisoryRating") val contentAdvisoryRating: String,
    @SerializedName("artworkUrl100") val coverImage: String,
    @SerializedName("url") val url: String,
    @SerializedName("genres") val genres: List<AlbumGenreResponse>
) : Serializable

