package com.first.android_coffee_prystore.pages.app.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.Product
import com.first.android_coffee_prystore.core.components.SearchCategories
import com.first.android_coffee_prystore.core.components.ToolbarTitle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp


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
        viewModel // Якщо обробка "Назад" не потрібна
    )
}

@Composable
private fun FeedScreenContent(
    products: List<Product>, // Приймаємо список продуктів
    searchCategories: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Column {
        ToolbarTitle(title = "Feed")
        SearchCategories(tags = searchCategories, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

        // Використовуємо LazyVerticalGrid для відображення продуктів
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
            Image(
                alignment = Alignment.TopEnd,
                painter = rememberAsyncImagePainter(model = product.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().clip(RectangleShape)
            )
            val isFavourite by remember { mutableStateOf(product.isFavourite)}

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
            ){
                OutlinedIconToggleButton(
                    checked = isFavourite,
                    onCheckedChange = {}
                ){
Icon(
    modifier = Modifier.size(22.dp),
    imageVector = Icons.Default.Favorite,
    contentDescription = null
)
                }
            }
        }
    }

    ProductItemTitle(title = product.title)

    ProductItemPrice(price = product.price)

}
}

@Composable
private fun ProductItemTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
}


@Composable
private fun ProductItemPrice(price: Double){
    Text(
        text = "\$$price",
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        color = Color.Gray,
        fontSize = 12.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
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