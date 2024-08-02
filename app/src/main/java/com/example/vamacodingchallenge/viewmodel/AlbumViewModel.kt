package com.example.vamacodingchallenge.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vamacodingchallenge.R
import com.example.vamacodingchallenge.model.Album
import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import com.example.vamacodingchallenge.network.response.AlbumResponse
import com.example.vamacodingchallenge.repository.AlbumRepository
import com.example.vamacodingchallenge.utils.CommonFunction
import com.example.vamacodingchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _response: MutableStateFlow<Resource<List<AlbumPresenter>?>> =
        MutableStateFlow(Resource.Loading)
    val albumResponse: StateFlow<Resource<List<AlbumPresenter>?>> = _response

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {

        viewModelScope.launch {
            _response.emit(Resource.Loading)
            // this line was added so user can see the screen loading for challenge purposes
            delay(2000)
            if (CommonFunction.isNetworkAvailable(context)) {
                try {
                    val response = albumRepository.getAllAlbums().first()
                    _response.emit(Resource.Success(response))
                } catch (e: Exception) {
                    val errorMessage = context.getString(R.string.error_gral)
                    _response.emit(Resource.Error(errorMessage))
                }
            } else {
                try {
                    val response = albumRepository.getAllAlbumsFromLocal().first()
                    if(response.isEmpty()) {
                        _response.emit(Resource.Error(context.getString(R.string.error_internet)))
                    } else {
                        _response.emit(Resource.Success(response))
                    }
                } catch (e: Exception) {
                    val errorMessage = context.getString(R.string.error_gral)
                    _response.emit(Resource.Error(errorMessage))
                }


            }
        }
    }

    fun refreshData() {
        fetchAlbums()
    }
}