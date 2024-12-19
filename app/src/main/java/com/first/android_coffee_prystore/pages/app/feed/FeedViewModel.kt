package com.first.android_coffee_prystore.pages.app.feed

import android.app.Dialog
import com.first.android_coffee_prystore.BaseViewModel
import com.first.android_coffee_prystore.Callback
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.Progress

import com.first.android_coffee_prystore.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor() : BaseViewModel<FeedUiState, Progress, Dialog, Callback>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is FeedUiEvent.LoadScreenData -> {
                loadScreenData()
            }
            is FeedUiEvent.OnFavouriteClick -> {
                onFavoriteClick(uiEvent.productId)
            }

            is FeedUiEvent.OnProductClick -> { /* no-op */ }
        }
    }

    private fun loadScreenData() {
        launch {
            val products = async { MockUtils.loadMockProducts() }
            val searchCategories = async { MockUtils.loadMockSearchCategories() }
            val response = products.await() to searchCategories.await()

            updateState { currentState ->
                currentState.value = FeedUiState(
                    products = TODO(),
                    searchCategories = TODO()
                )
            }
        }
    }

    private fun onFavoriteClick(productId: Long) {
        TODO("Not yet implemented")
    }


}