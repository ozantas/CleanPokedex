package com.ozan.cleanpokedex.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ozan.cleanpokedex.R

private val RobotoFamily = FontFamily(
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_black, FontWeight.Black)
)

val PokedexTypography = Typography(
    h1 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.Medium
    ),
    h2 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    h3 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    h4 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    h5 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    h6 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    subtitle1 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Light
    ),
    subtitle2 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.Light
    ),
    body1 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    body2 = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    ),
    button = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    caption = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    ),
    overline = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
)