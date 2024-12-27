package com.first.android_coffee_prystore.pages.app.cart



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
class CartViewModel @Inject constructor() : BaseViewModel<CartUiState, Progress, Dialog, Callback>() {

    sealed class QuantityChange{
        data object Increase: QuantityChange()
        data object Decrease: QuantityChange()
    }

    override fun handleUiEvent(uiEvent: UiEvent) {

        when(uiEvent){
            is CartUiEvent.LoadScreenData -> {
                loadData()
            }
            is CartUiEvent.OnIncreaseQuantity -> {
                changeQuantity(uiEvent.id, QuantityChange.Increase)
            }
            is CartUiEvent.OnDecreaseQuantity -> {
                changeQuantity(uiEvent.id, QuantityChange.Decrease)
            }
        }

    }

    private fun changeQuantity(id: Long, action: QuantityChange) {
 updateState {
     mutableState ->
     mutableState.value?.let {
         state ->
         val products = state.products.map {
             product ->
             val quantity = if (action is QuantityChange.Increase){
             product.quantity +1 }
             else {
                 product.quantity -1
             }

             if (product.id == id) product.copy(quantity = quantity.coerceAtLeast(1))
                 else product
         }
         mutableState.value = state.copy(products = products)
     }
 }
    }

    private fun loadData() {
 launch {
     val productsResponse = async { MockUtils.loadMockProductsInCart() }
     val pricesResponse = async { MockUtils.loadMockProductPrices() }
     val (product, prices) = productsResponse.await() to pricesResponse.await()

     updateState {
         currentState.value = CartUiState(
             products = product,
             subtotal = prices.subtotal,
             shipping = prices.shipping,
             discount = prices.discount,
             total = prices.total
         )
     }

 }
    }


}