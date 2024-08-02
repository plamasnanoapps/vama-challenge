package com.example.vamacodingchallenge.model.presentation

import java.io.Serializable

data class AlbumPresenter(
    val idAlbum: String,
    val name: String,
    val artistName: String,
    val releaseDate: String,
    val coverImage: String,
    val url: String,
    val genres: String,
    val copyright: String
) : Serializable

