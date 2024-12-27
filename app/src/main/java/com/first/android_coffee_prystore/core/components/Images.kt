package com.first.android_coffee_prystore.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.first.android_coffee_prystore.R
import com.first.android_coffee_prystore.pages.app.favourites.FavouritesUiEvent

@Composable
fun ProductImage(
    url: String,
    onClick: () -> Unit
) {

    Image(
        alignment = Alignment.Center,
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick.invoke() }
    )

}


@Composable
fun DeleteImageButton(
    onClick: () -> Unit
){
    OutlinedIconButton(
        modifier = Modifier.size(22.dp),
        onClick = {

           onClick()
        },
        content = {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.surfaceTint
            )
        }
    )
}

@Composable
fun ToolbarMenuIcon(
    modifier: Modifier,
    icon: ImageVector,
    onClick: () -> Unit
){
    IconButton(
        modifier = modifier,
        onClick = { onClick() },
        content = {
            Icon(
                icon,
                stringResource(id = R.string.menu_button)
            )
        }
    )
}

@Composable
fun ProductSquareImage(imageUrl: String){
        Image(
            alignment = Alignment.Center,
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RectangleShape)
        )

}

@Composable
fun ProductLandscapePhoto(url: String){
    Image(
        alignment = Alignment.Center,
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().height(250.dp)
            .clip(RoundedCornerShape(12.dp))
    )

}


