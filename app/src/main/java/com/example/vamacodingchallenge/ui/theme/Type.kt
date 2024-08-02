package com.example.vamacodingchallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.vamacodingchallenge.R

// Set of Material typography styles to start with
object AppFont {
    val GeometriaFamily = FontFamily(
        Font(R.font.geometria, FontWeight.Normal),
        Font(R.font.geometria_thin, FontWeight.Thin),
        Font(R.font.geometria_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.geometria_medium, FontWeight.Medium),
        Font(R.font.geometria_bold, FontWeight.Bold)
    )
}

private val defaultTypography = Typography()
val Typography = Typography(
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.GeometriaFamily),
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.GeometriaFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.GeometriaFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.GeometriaFamily),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.GeometriaFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.GeometriaFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.GeometriaFamily),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.GeometriaFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.GeometriaFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.GeometriaFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.GeometriaFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.GeometriaFamily),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.GeometriaFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.GeometriaFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.GeometriaFamily),
)

