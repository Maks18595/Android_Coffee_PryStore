package com.first.android_coffee_prystore.pages.app.favourites


import com.first.android_coffee_prystore.BaseViewModel
import com.first.android_coffee_prystore.Callback
import com.first.android_coffee_prystore.Dialog
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.Progress
import com.first.android_coffee_prystore.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(): BaseViewModel<FavouritesUiState, Progress, Dialog, Callback>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is FavouritesUiEvent.Initiate -> {
                loadFavourites()
            }
            is FavouritesUiEvent.OnItemDeleted -> {
                onItemDeleted(uiEvent.id)
            }
        }
    }

    private fun onItemDeleted(id: Long) {
        updateState { mutableState ->
            mutableState.value?.let { state ->
                val found = state.items.first { it.id == id }
val newList = state.items.toMutableList().also { it.remove(found) }
                mutableState.value = state.copy(items = newList)
            }
        }
    }

    private fun loadFavourites() {
        launch {
            val data = MockUtils.loadMockFavourites()
            updateState {
                it.value = FavouritesUiState(items = data)
            }
        }

    }
}