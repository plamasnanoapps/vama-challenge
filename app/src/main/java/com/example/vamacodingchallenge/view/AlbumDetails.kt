package com.example.vamacodingchallenge.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vamacodingchallenge.R
import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import com.example.vamacodingchallenge.ui.theme.AppFont

@Composable
fun AlbumDetails(album: AlbumPresenter) {

    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .background(color = colorResource(R.color.background)),
                ) {
                    Card(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 50.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(R.color.white),
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                        shape = RoundedCornerShape(corner = CornerSize(10.dp))
                    ) {
                        DetailsHeader(album)
                        DetailsContent(album, this@BoxWithConstraints.maxHeight)
                    }
                }
            }
            AlbumPageBtn(
                album, modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun DetailsHeader(album: AlbumPresenter) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp, bottom = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = album.coverImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(20.dp)
                    .width(200.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun DetailsContent(album: AlbumPresenter, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Text(
                text = album.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFont.GeometriaFamily
            )
        }

        ProfileProperty(album.artistName, album.releaseDate)

        ProfileProperty(album.genres, "")

        ProfileProperty(album.copyright, "")

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
fun ProfileProperty(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp),
                text = value,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = AppFont.GeometriaFamily
            )
        }

    }
}

@Composable
fun AlbumPageBtn(album: AlbumPresenter, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(album.url)) }

    Box(modifier = Modifier.fillMaxSize()) {
        // add your column here (with align modifier)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            OutlinedButton(colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, contentColor = Color.Black
            ), onClick = {
                context.startActivity(intent)
            }) {
                Text(
                    text = stringResource(R.string.open_itunes), fontFamily = AppFont.GeometriaFamily
                )
            }
        }
    }


}
