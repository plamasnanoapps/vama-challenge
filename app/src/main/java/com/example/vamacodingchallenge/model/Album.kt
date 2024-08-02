package com.example.vamacodingchallenge.model

import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.io.Serializable
import java.util.UUID

class Album() : RealmObject {

    @PrimaryKey
    var id: String = ""
    var idAlbum: String = ""
    var name: String = ""
    var artistName: String = ""
    var releaseDate: String = ""
    var coverImage: String = ""
    var url: String = ""
    var genres: String = ""
    var copyright: String = ""

    constructor(
        id: String = UUID.randomUUID().toString(),
        idAlbum: String,
        name: String,
        artistName: String,
        releaseDate: String,
        coverImage: String,
        url: String,
        genres: String,
        copyright: String

    ) : this() {

        this.id = id
        this.idAlbum = idAlbum
        this.name = name
        this.artistName = artistName
        this.releaseDate = releaseDate
        this.coverImage = coverImage
        this.url = url
        this.genres = genres
        this.copyright = copyright
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Album

        if (idAlbum != other.idAlbum) return false
        if (name != other.name) return false
        if (artistName != other.artistName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + idAlbum.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + artistName.hashCode()
        result = 31 * result + releaseDate.hashCode()
        result = 31 * result + coverImage.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + copyright.hashCode()

        return result
    }

    override fun toString(): String {
        return super.toString()
    }

    fun toPresentation(): AlbumPresenter {
        return AlbumPresenter(
            idAlbum = idAlbum,
            name = name,
            artistName = artistName,
            releaseDate = releaseDate,
            coverImage = coverImage,
            url = url,
            genres = genres,
            copyright = copyright
        )
    }
}

