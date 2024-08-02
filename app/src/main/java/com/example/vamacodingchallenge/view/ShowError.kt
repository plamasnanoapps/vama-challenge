package com.example.vamacodingchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vamacodingchallenge.R
import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import com.example.vamacodingchallenge.ui.theme.AppFont

@Composable
fun ShowError(errorMessage: String?, recompose: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(colorResource(R.color.background))
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Warning,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            if (errorMessage != null) {
                Text(errorMessage)
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, contentColor = Color.Black
            ), onClick = {
                recompose("")
            }) {
                Text(
                    text = stringResource(R.string.try_again),
                    fontFamily = AppFont.GeometriaFamily
                )
            }
        }

    }
}