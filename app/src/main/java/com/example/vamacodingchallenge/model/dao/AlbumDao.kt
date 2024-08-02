package com.example.vamacodingchallenge.model.dao

import com.example.vamacodingchallenge.model.Album
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumDao @Inject constructor(
    private val realm: Realm
) {
    suspend fun insertAlbums(album: List<Album>) = realm.write {
        album.map { copyToRealm(it, updatePolicy = UpdatePolicy.ALL) }
    }

    // fetch all objects of a type as a flow, asynchronously
    fun getAllNotes(): List<Album> = realm.query<Album>().find()

    suspend fun deleteAllAlbums() = realm.write {
        // Query Album type with no filter to return all objects
        val albumsInTheRealm = query<Album>().find()
        // Pass the query results to delete()
        delete(albumsInTheRealm)
    }



}