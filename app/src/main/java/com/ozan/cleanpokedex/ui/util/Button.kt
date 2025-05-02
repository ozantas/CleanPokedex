package com.ozan.cleanpokedex.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ozan.cleanpokedex.R

@Composable
fun NavigateBackIcon(
    onBackClicked: () -> Unit
) {
    IconButton(
        onClick = onBackClicked,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = stringResource(R.string.back),
        )
    }
}