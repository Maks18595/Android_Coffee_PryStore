package com.first.android_coffee_prystore.prototype

import androidx.compose.runtime.mutableStateOf
import com.first.android_coffee_prystore.BaseViewModel
import com.first.android_coffee_prystore.Callback
import com.first.android_coffee_prystore.Dialog
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.Progress
import com.first.android_coffee_prystore.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(): BaseViewModel<ProductUiState, Progress, Dialog, Callback>(){
    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent){
            is ProductUiEvent.LoadData -> {
                loadData(uiEvent.id)
            }
            is ProductUiEvent.AddToCartOnClick -> {

            }
            is ProductUiEvent.UpdateFavouriteOnClick -> {
 updateFavouriteState()
            }
        }
    }


    private fun updateFavouriteState(

    ){
 updateState { mutableState ->
     mutableState.value?.let { state ->
         val product = state.product.copy(isFavourite = !state.product.isFavourite)
         mutableState.value = state.copy(product = product)
         }
 }
    }

    private fun loadData(id: Long){
        launch {
            val product = async { MockUtils.loadMockProductsDetails(id) }
            val isProductInCart = async { MockUtils.isProductInCart(id)}
            val response = product.await() to isProductInCart.await()

            updateState { currentState ->
                currentState.value = ProductUiState(
                    product = response.first,
                    isAddedToCart = response.second
                )
            }

        }
    }

}