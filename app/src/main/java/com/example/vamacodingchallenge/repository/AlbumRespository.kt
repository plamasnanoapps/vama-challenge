package com.example.vamacodingchallenge.repository

import com.example.vamacodingchallenge.model.Album
import com.example.vamacodingchallenge.model.dao.AlbumDao
import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import com.example.vamacodingchallenge.network.ApiService
import com.example.vamacodingchallenge.network.response.AlbumListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val apiService: ApiService, private val albumDao: AlbumDao
) {

    /**
     * Returns 100 albums from network or all albums saved on the database
     */
    fun getAllAlbums(): Flow<List<AlbumPresenter>> = flow {

        // Get data from local data base
        val albumLocalList = ArrayList<AlbumPresenter>()
        albumLocalList.addAll(getAlbumPresenterLisFromDatabase(albumDao.getAllNotes()))

        // Check if the app has local data
        if (albumLocalList.size == 0) {
            // If app has no local data, the data is requested to network.
            val feedResponse = apiService.getAlbumList()
            albumLocalList.addAll(getAlbumPresenterLisFromResponse(feedResponse.feed))

            //Save new data to database
            saveAlbumsOnLocalDataBase(feedResponse.feed)
        }
        emit(albumLocalList)
    }.flowOn(Dispatchers.IO)

    /**
     * Returns all albums saved on the database
     */
    fun getAllAlbumsFromLocal(): Flow<List<AlbumPresenter>> = flow {
        // Get data from local data base
        val albumLocalList = ArrayList<AlbumPresenter>()
        albumLocalList.addAll(getAlbumPresenterLisFromDatabase(albumDao.getAllNotes()))
        emit(albumLocalList)
    }.flowOn(Dispatchers.IO)

    /**
     * Converts a list of AlbumResponse into one of AlbumPresenter to use on UI
     * param albumListResponse: List to be converted
     */
    private fun getAlbumPresenterLisFromResponse(albumListResponse: AlbumListResponse): ArrayList<AlbumPresenter> {
        val albumDataList = ArrayList<AlbumPresenter>()
        if (albumListResponse.albums != null) {
            for (albumResponse in albumListResponse.albums) {
                albumDataList.add(AlbumPresenter(
                    idAlbum = albumResponse.id,
                    name = albumResponse.name,
                    artistName = albumResponse.artistName,
                    releaseDate = albumResponse.releaseDate,
                    coverImage = albumResponse.coverImage,
                    url = albumResponse.url,
                    genres = albumResponse.genres.joinToString(prefix = "",
                        postfix = "",
                        separator = " • ",
                        transform = { it.name }),
                    copyright = albumListResponse.copyright,
                ))
            }
        }
        return albumDataList
    }

    /**
     * Converts a list of Album data base model into one of AlbumPresenter to use on UI
     * param albums: List to be converted
     */
    private fun getAlbumPresenterLisFromDatabase(albums: List<Album>): ArrayList<AlbumPresenter> {
        val albumDataList = ArrayList<AlbumPresenter>()
        if (albums != null) {
            for (albumResponse in albums) {
                albumDataList.add(
                    AlbumPresenter(
                        idAlbum = albumResponse.id,
                        name = albumResponse.name,
                        artistName = albumResponse.artistName,
                        releaseDate = albumResponse.releaseDate,
                        coverImage = albumResponse.coverImage,
                        url = albumResponse.url,
                        genres = albumResponse.genres,
                        copyright = albumResponse.copyright,
                    )
                )
            }
        }
        return albumDataList
    }

    /**
     * Converts a list of AlbumResponse into one of Album data base model
     * param albumListResponse: List to be converted
     */
    private fun getAlbumLisFromResponse(albumListResponse: AlbumListResponse): ArrayList<Album> {
        val albumDataList = ArrayList<Album>()
        if (albumListResponse.albums != null) {
            for (albumResponse in albumListResponse.albums) {
                albumDataList.add(Album(
                    idAlbum = albumResponse.id,
                    name = albumResponse.name,
                    artistName = albumResponse.artistName,
                    releaseDate = albumResponse.releaseDate,
                    coverImage = albumResponse.coverImage,
                    url = albumResponse.url,
                    genres = albumResponse.genres.joinToString(prefix = "",
                        postfix = "",
                        separator = " • ",
                        transform = { it.name }),
                    copyright = albumListResponse.copyright,
                ))
            }
        }
        return albumDataList
    }

    /**
     * Replaces all albums in the database by removing old ones and saving new ones.
     * param albumListResponse: List of new albums requested from network.
     */
    private suspend fun saveAlbumsOnLocalDataBase(albumListResponse: AlbumListResponse) {
        albumDao.deleteAllAlbums()
        albumDao.insertAlbums(getAlbumLisFromResponse(albumListResponse))
    }


}