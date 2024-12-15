package com.first.android_coffee_prystore

import android.app.Dialog

data class EventState<P: Progress, D: Dialog>(
    val progress: ProgressData<P>? = null,
    val dialog: D? = null
)

interface Progress {
    val isLoading: Boolean
}
interface Dialog
interface Message
interface Callback {
    fun onSuccess()
    fun onError(error: String)
}
interface UiEvent

interface AppUiState
