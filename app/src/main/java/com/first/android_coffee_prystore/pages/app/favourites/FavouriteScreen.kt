package com.first.android_coffee_prystore.pages.app.favourites

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.text.style.TextOverflow
import com.first.android_coffee_prystore.confirm.Content
import com.first.android_coffee_prystore.core.components.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
 viewModel: FavouriteViewModel,
 onNavigateToProduct: (Long) -> Unit,
){
Scaffold(
    topBar = { TopBar() },
    content = {
        Content(
            modifier = Modifier.padding(it),
            onNavigateToProduct = onNavigateToProduct
        )
    }
)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                "Favourites",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors()
    )
}
