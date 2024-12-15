package com.first.android_coffee_prystore.pages.app.notifications

import com.first.android_coffee_prystore.pages.app.notifications.Notification
import androidx.collection.emptyObjectList
import com.first.android_coffee_prystore.AppUiState
import perfetto.protos.UiState

data class NotificationsUiState(
    val notifications: List<Notification> = emptyList()
) : AppUiState

