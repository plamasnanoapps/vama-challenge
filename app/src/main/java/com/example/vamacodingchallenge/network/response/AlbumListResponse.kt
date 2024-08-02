package com.example.vamacodingchallenge.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlbumListResponse(
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("country") val country: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("updated") val updated: String,
    @SerializedName("results") val albums: List<AlbumResponse>?
) : Serializable