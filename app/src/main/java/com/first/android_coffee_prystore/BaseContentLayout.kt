package com.first.android_coffee_prystore

import android.app.Dialog
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import perfetto.protos.UiState
import com.first.android_coffee_prystore.BaseViewModel


@Composable
fun <S : AppUiState, P : Progress, D : Dialog, C : Callback> BaseContentLayout(
    viewModel: BaseViewModel<S, P, D, C>,
    onBackPressed: (() -> Unit)? = null,
    content: @Composable (uiState: S?) -> Unit
) {
    BackHandler(enabled = onBackPressed != null) {
        onBackPressed?.invoke()
    }

    val uiState by viewModel.uiState
    val eventState by viewModel.eventState

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        content(uiState)
    }
}


