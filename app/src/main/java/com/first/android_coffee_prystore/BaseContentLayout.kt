package com.first.android_coffee_prystore

import android.app.Dialog
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier


@Composable
fun BaseContentLayout(
    onBackPressed: (() -> Unit)?,
    content: @Composable () -> Unit
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
