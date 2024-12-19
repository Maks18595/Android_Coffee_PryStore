package com.first.android_coffee_prystore.pages.app.notifications

import com.first.android_coffee_prystore.UiEvent

sealed class NotificationsUiEvent : UiEvent {
    data object LoadScreenData : NotificationsUiEvent()
    data object OnMarkAllAsReadClick : NotificationsUiEvent()
    data class OnMarkAsReadClick(val id: Long) : NotificationsUiEvent()
}