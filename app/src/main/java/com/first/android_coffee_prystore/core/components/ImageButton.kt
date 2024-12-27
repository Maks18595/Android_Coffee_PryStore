package com.first.android_coffee_prystore.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButton(isFavourite: Boolean){

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        OutlinedIconToggleButton(
            checked = isFavourite,
            onCheckedChange = {}
        ) {
            Icon(
                modifier = Modifier.size(22.dp),
                imageVector = Icons.Default.Favorite,
                contentDescription = null
            )
        }
    }
}
