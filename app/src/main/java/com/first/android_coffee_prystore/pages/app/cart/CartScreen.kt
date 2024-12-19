package com.first.android_coffee_prystore.pages.app.cart

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onNavigateToNotifications: () -> Unit,
    onNavigateToConfirm: () -> Unit,
    onNavigateToProduct: (Long) -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Cart")},
                actions = {
                    IconButton(onClick = onNavigateToNotifications){
                        Icon(imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications")
                    }
                },

            )
        },
        content = {
            CartScreenContent(
                modifier = Modifier.padding(it),
                onNavigateToConfirm = onNavigateToConfirm,
                onNavigateToProduct = onNavigateToProduct
            )
        }
    )
}

@Composable
fun CartScreenContent(modifier: Modifier, onNavigateToConfirm: () -> Unit, onNavigateToProduct: (Long) -> Unit) {

}
