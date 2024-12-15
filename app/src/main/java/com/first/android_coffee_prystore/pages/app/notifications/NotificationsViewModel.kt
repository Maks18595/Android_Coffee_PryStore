package com.first.android_coffee_prystore.pages.app.notifications

import android.app.Dialog
import androidx.lifecycle.ViewModel
import com.first.android_coffee_prystore.BaseViewModel
import com.first.android_coffee_prystore.Callback
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.Progress
import com.first.android_coffee_prystore.ProgressData
import com.first.android_coffee_prystore.UiEvent
import com.first.android_coffee_prystore.pages.app.notifications.NotificationsUiState

class NotificationsViewModel : BaseViewModel<NotificationsUiState, Progress, Dialog, Callback>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is NotificationsUiEvent.LoadScreenData -> {
                loadNotifications()
            }
            is NotificationsUiEvent.OnMarkAllAsReadClick -> {
                // Handle mark all as read
            }
            is NotificationsUiEvent.OnMarkAsReadClick -> {
                // Handle mark single notification as read
            }
        }
    }

    override fun ProgressData(progress: Progress?): ProgressData<Progress> {
        return ProgressData(progress)
    }

    private fun loadNotifications() {
        val notifications = MockUtils.loadMockNotifications()
        updateState { currentState ->
            currentState.value = NotificationsUiState(notifications = notifications)
        }
    }
}