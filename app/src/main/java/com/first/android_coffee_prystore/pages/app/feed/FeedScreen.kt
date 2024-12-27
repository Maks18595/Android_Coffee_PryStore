package com.first.android_coffee_prystore.pages.app.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.Product
import com.first.android_coffee_prystore.core.components.SearchCategories
import com.first.android_coffee_prystore.core.components.ToolbarTitle


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.first.android_coffee_prystore.core.components.ProductPrice
import com.first.android_coffee_prystore.core.components.ProductSquareImage
import com.first.android_coffee_prystore.core.components.ProductTitle
import com.first.android_coffee_prystore.core.components.ToolbarLayout
import com.first.android_coffee_prystore.core.components.ToolbarMenuIcon
import com.first.android_coffee_prystore.core.components.FavouriteButton


@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    // Завантаження даних при створенні екрану
    LaunchedEffect(Unit) {
        viewModel.handleUiEvent(FeedUiEvent.LoadScreenData)
    }

    // Відображення вмісту через BaseContentLayout
    BaseContentLayout(
        onBackPressed = null,
        {
            // Отримання стану UI
            val uiState by viewModel.uiState

            uiState?.let {
                // Викликаємо FeedScreenContent і передаємо продукти
                FeedScreenContent(
                    products = listOf(), // Передайте список продуктів
                    searchCategories = { /* Завантаження категорій */ },
                    onNavigateToNotifications = onNavigateToNotifications,
                    onNavigateToProduct = onNavigateToProduct,
                    onNavigateToSearch = onNavigateToSearch
                )
            }
        },
        viewModel
    )
}

@Composable
private fun FeedScreenContent(
    products: List<Product>,
    searchCategories: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Column {
        ToolbarLayout (
            modifier = Modifier.fillMaxWidth(),
            content = {
            ToolbarTitle(title = "Feed")
 ToolbarMenuIcon(
     modifier = Modifier,
     icon = Icons.Filled.Notifications,
     onClick = { onNavigateToNotifications() }
 )


        }
        )

            SearchCategories(
                tags = searchCategories,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(products.size) { index ->
                    ProductItem(
                        product = products[index], // Використовуємо індекс для доступу до продукту
                        onNavigateToProduct = onNavigateToProduct
                    )
                }
            }
        )
    }
}


@Composable
fun ProductItem(product: Product, onNavigateToProduct: (Long) -> Unit) {
Column {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigateToProduct.invoke(product.id)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
 ProductSquareImage(imageUrl = product.imageUrl)
            val isFavourite by remember { mutableStateOf(product.isFavourite) }
 FavouriteButton(isFavourite)

                }
            }

    ProductTitle(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
        title = product.title,
    )
    ProductPrice(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
        price = product.price,
        style = MaterialTheme.typography.titleSmall
        )

}
}




@Preview(showSystemUi = true)
@Composable
private fun FeedScreenPreview() {
    FeedScreen(
        viewModel = FeedViewModel(),
        onNavigateToNotifications = {},
        onNavigateToProduct = {},
        onNavigateToSearch = {},

    )
}