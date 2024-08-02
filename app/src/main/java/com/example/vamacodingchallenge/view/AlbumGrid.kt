package com.example.vamacodingchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vamacodingchallenge.R
import com.example.vamacodingchallenge.model.Album
import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import com.example.vamacodingchallenge.ui.theme.AppFont
import com.example.vamacodingchallenge.utils.Resource
import com.example.vamacodingchallenge.viewmodel.AlbumViewModel
import okhttp3.internal.notify

@Composable
fun AlbumGrid(viewModel: AlbumViewModel, selectedItem: (AlbumPresenter) -> Unit) {

    val albumListState by viewModel.albumResponse.collectAsState()
    val lazyVerticalGridState = rememberLazyGridState()

    when (albumListState) {
        is Resource.Success -> {
            // Handle success state for albums
            val albumListData = (albumListState as Resource.Success<List<AlbumPresenter>?>).data
            if (albumListData != null) {
                Box(
                    modifier = Modifier.background(color = colorResource(R.color.background))
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        GridHeader()
                        LazyVerticalGrid(state = lazyVerticalGridState,
                            columns = GridCells.Fixed(2),
                            content = {
                                items(albumListData.size) { index ->
                                    AlbumGridItem(
                                        album = albumListData[index],
                                        selectedItem = selectedItem
                                    )
                                }
                            })
                    }
                }
            }

        }

        is Resource.Error -> {
            // Handle error state for regular quotes
            val errorMessage = (albumListState as Resource.Error).message
            // Render UI with errorMessage
            ShowError(errorMessage) {
                viewModel.refreshData() }
        }

        is Resource.Loading -> {
            // Handle loading state for regular quotes
            // Render loading indicator or any UI
            CenteredCircularProgressIndicator()
        }
    }
}

@Composable
private fun GridHeader() {

    Spacer(modifier = Modifier.height(50.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.user),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = stringResource(R.string.welcome),
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.titleSmall,
                fontFamily = AppFont.GeometriaFamily,
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.app_title),
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = AppFont.GeometriaFamily,
                color = Color.Black
            )
        }

        Image(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.menu),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
fun SimpleComposablePreview() {
    GridHeader()
}

