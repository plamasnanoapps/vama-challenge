package com.example.vamacodingchallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vamacodingchallenge.R
import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import com.example.vamacodingchallenge.ui.theme.AppFont

@Composable
fun AlbumGridItem(album: AlbumPresenter, selectedItem: (AlbumPresenter) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .clickable { selectedItem(album) }
                .background(colorResource(R.color.white))) {

            AsyncImage(
                model = album.coverImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(15.dp)
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = album.name,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = AppFont.GeometriaFamily,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            Text(
                text = album.artistName,
                style = MaterialTheme.typography.labelLarge,
                fontFamily = AppFont.GeometriaFamily,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                minLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))

        }

    }
}