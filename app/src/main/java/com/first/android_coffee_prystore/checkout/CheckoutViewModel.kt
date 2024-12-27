package com.first.android_coffee_prystore.checkout

import androidx.lifecycle.ViewModel
import com.first.android_coffee_prystore.BaseViewModel
import com.first.android_coffee_prystore.Callback
import com.first.android_coffee_prystore.Dialog
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.Progress
import com.first.android_coffee_prystore.UiEvent
import kotlinx.coroutines.async

class CheckoutViewModel: BaseViewModel<CheckoutUiState, Progress, Dialog, Callback>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent){
            is CheckoutUiEvent.LoadScreenData -> {
                loadScreenData()
            }
            is CheckoutUiEvent.SelectDeliveryAddress -> {
                updateDeliveryAddress(uiEvent.id)
            }
            is CheckoutUiEvent.SelectPaymentMethod -> {
                updatePaymentMethod(uiEvent.id)
            }
        }
    }

    private fun updatePaymentMethod(id: Long) {
        updateState { mutableState ->
            mutableState.value?.let { state ->
                val paymentMethod = state.paymentMethod.map { paymentMethod ->
                    paymentMethod.copy(isSelected = paymentMethod.id == id)
                }
                mutableState.value = state.copy(paymentMethod = paymentMethod)
            }
        }
    }

    private fun updateDeliveryAddress(id: Long) {
     updateState { mutableState ->
            mutableState.value?.let { state ->
                val deliveryAddress = state.address.map { deliveryAddress ->
                    deliveryAddress.copy(isSelected = deliveryAddress.id == id)
                }
                mutableState.value = state.copy(address = deliveryAddress)
            }
        }
    }

    private fun loadScreenData() {
        launch {
            val addressesResponse = async { MockUtils.loadMockAdresses() }
            val paymentMethodResponse = async { MockUtils.loadMockPaymentMethods() }
            val cartResponse = async { MockUtils.loadMockCart() }
            val pricesResponse = async { MockUtils.loadMockProductPrices() }

            val (addresses, paymentMethods) = addressesResponse.await() to paymentMethodResponse.await()
            val (cart, prices)  = cartResponse.await() to pricesResponse.await()

            updateState { mutableState ->
                mutableState.value = CheckoutUiState(
                    addresses =addresses,
                    paymentMethods = paymentMethods,
                    products = cart,
                    total = prices.total
                )
            }
        }
    }

}