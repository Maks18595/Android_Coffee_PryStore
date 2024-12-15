package com.first.android_coffee_prystore.pages.app.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.R


@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel,
    onBackClick: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(NotificationsUiEvent.LoadScreenData)
    }
    BaseContentLayout(viewModel = viewModel) { uiState ->
        uiState?.let {
            NotificationsScreenContent(
                notifications = it.notifications,
                onBackClick = onBackClick,
                uiEvent = viewModel::handleUiEvent
            )
        }
    }
}



@Composable
private fun NotificationsScreenContent(
    notifications: List<com.first.android_coffee_prystore.pages.app.notifications.Notification>,
    onBackClick: () -> Unit,
    uiEvent: (NotificationsUiEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = stringResource(id = R.string.notifications),
            onBackClick = onBackClick
        )
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
items(notifications){
    notification ->
    NotificationCard(notification, uiEvent)
}
        }
    }
}




@Composable
private fun NotificationCard(
    notification: Notification,
    uiEvent: (NotificationsUiEvent) -> Unit)
{
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)

    ){
        Box(contentAlignment = Alignment.TopEnd){
            Row {

            }
            Text(text = notification.title)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    onBackClick: () -> Unit = {},
    isEnabledBackIcon: Boolean = true
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = androidx.compose.ui.graphics.Color.White
            )
        },
        navigationIcon = {
            if (isEnabledBackIcon) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back button",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            }
        },
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = androidx.compose.ui.graphics.Color(0xFF6200EE)
        )
    )
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationsScreenContentPreview() {
    NotificationsScreenContent(
        notifications = MockUtils.loadMockNotifications(),
        onBackClick = {},
        uiEvent = {}
    )

}
