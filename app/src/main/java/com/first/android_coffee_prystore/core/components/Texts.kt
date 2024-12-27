package com.first.android_coffee_prystore.core.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.first.android_coffee_prystore.R

@Composable
fun ProductPrice(
    modifier: Modifier,
    price: String,
    style: TextStyle
    ){
    Text(
        modifier = modifier,
        text = stringResource(R.string.pattern_price, price),
        style = style,
        maxLines = 1,
        color = MaterialTheme.colorScheme.error,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ProductDescription(
    description: String,
    maxLines: Int
) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ProductTitle(
    modifier: Modifier,
    title: String) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
/*
@Composable
fun ProductItemTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
}*/

@Composable
fun NotificationTitle(
    title: String,
    modifier: Modifier = Modifier
){
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Black,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}