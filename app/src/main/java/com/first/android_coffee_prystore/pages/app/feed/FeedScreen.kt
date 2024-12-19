package com.first.android_coffee_prystore.pages.app.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.core.components.ToolbarTitle
import com.first.android_coffee_prystore.pages.app.feed.FeedViewModel


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
        onBackPressed = null // Якщо обробка "Назад" не потрібна
    ) {
        // Отримання стану UI
        val uiState by viewModel.uiState

        uiState?.let {
            FeedScreenContent(
                products = { /* Завантаження продуктів */ },
                searchCategories = { /* Завантаження категорій */ },
                onNavigateToNotifications = onNavigateToNotifications,
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToSearch = onNavigateToSearch
            )
        }
    }
}

@Composable
private fun FeedScreenContent(
    products: () -> Unit,
    searchCategories: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToSearch: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Додайте свій UI тут, наприклад, список продуктів
    }
}





@Preview(showSystemUi = true)
@Composable
private fun FeedScreenContentPreview() {
    FeedScreenContent(
        onNavigateToNotifications = {},
        onNavigateToProduct = {},
        onNavigateToSearch = {},
        products = {},
        searchCategories = {}
    )
}