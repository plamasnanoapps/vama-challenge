package com.example.vamacodingchallenge.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeedResponse(
    @SerializedName("feed") val feed: AlbumListResponse) : Serializable