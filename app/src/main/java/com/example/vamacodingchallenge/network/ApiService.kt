package com.example.vamacodingchallenge.network

import com.example.vamacodingchallenge.network.response.FeedResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET("most-played/100/albums.json")
    suspend fun getAlbumList(): FeedResponse
}