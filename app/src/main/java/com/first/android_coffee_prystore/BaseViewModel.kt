package com.first.android_coffee_prystore

import android.app.Dialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import perfetto.protos.UiState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<S : AppUiState, P : Progress, D : Dialog, C : Callback> : ViewModel() {
    private val _uiState = mutableStateOf<S?>(null)
    val uiState: State<S?> get() = _uiState

    private val _eventState = mutableStateOf(EventState<P, D>())
    val eventState: State<EventState<P, D>> get() = _eventState

    protected fun updateState(block: (state: MutableState<S?>) -> Unit) {
        block.invoke(_uiState)
    }

    abstract fun handleUiEvent(uiEvent: UiEvent)

    protected fun launch(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(context = EmptyCoroutineContext, block = block)

    protected fun launchWithProgress(progress: P? = null, block: suspend CoroutineScope.() -> Unit) = launch {
        try {
            _eventState.value = _eventState.value.copy(progress = ProgressData(progress))
            block()
        } finally {
            _eventState.value = _eventState.value.copy(progress = null)
        }
    }

    abstract fun ProgressData(progress: P?): ProgressData<P>
}

// Progress interface
data class ProgressData<P : Progress>(val progress: P?)

