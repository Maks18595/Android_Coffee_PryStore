package com.first.android_coffee_prystore.pages.app.notifications

import com.first.android_coffee_prystore.UiEvent

sealed class NotificationsUiEvent : UiEvent {
    object LoadScreenData : NotificationsUiEvent()
    object OnMarkAllAsReadClick : NotificationsUiEvent()
    data class OnMarkAsReadClick(val notificationId: Int) : NotificationsUiEvent()
}