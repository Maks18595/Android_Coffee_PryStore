package com.first.android_coffee_prystore

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import com.first.android_coffee_prystore.prototype.ProductViewModel


@Composable
fun BaseContentLayout(
    onBackPressed: (() -> Unit)?,
    content: @Composable (Any?) -> Unit,
    viewModel: ProductViewModel
) {
    // Handle back press if provided
    if (onBackPressed != null) {
        BackHandler {
            onBackPressed()
        }
    }

    // Main layout container with content
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        content() // Display content passed to this composable
    }
}
