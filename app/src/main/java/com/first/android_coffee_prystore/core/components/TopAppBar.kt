package com.first.android_coffee_prystore.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.first.android_coffee_prystore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String? = null,
    onBackClick: (() -> Unit)? = null,
    menuIcon: ImageVector? = null,
    onMenuClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            title?.let { text ->
                Text(
                    text = text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.teal_700)),
        navigationIcon = {
            onBackClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        stringResource(id = R.string.back_button)
                    )
                }
            }
        },
        actions = {
            menuIcon?.let {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        Icons.Filled.Check,
                        stringResource(id = R.string.menu_button)
                    )
                }
            }
        }
    )
}

@Composable
fun ToolbarTitle(title: String) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarAsTextPreview() {
    ToolbarTitle(title = "TopBar as text")
}

@Preview(showBackground = true)
@Composable
private fun TopBarWithBackButtonOnlyPreview() {
    TopAppBar(
        title = null,
        onBackClick = {},
        menuIcon = null,
        onMenuClick = {}
    )
}

@Preview
@Composable
private fun TopBarWithTitleOnlyPreview() {
    TopAppBar(
        title = "TopBar with title only",
        onBackClick = null,
        menuIcon = null,
        onMenuClick = {}
    )
}

@Preview
@Composable
private fun TopBarWithTitleAndBackButtonPreview() {
    TopAppBar(
        title = "TopBar with title and back button",
        onBackClick = {},
        menuIcon = null,
        onMenuClick = {}
    )
}

@Preview
@Composable
private fun TopBarWithTitleAndMenuPreview() {
    TopAppBar(
        title = "TopBar with title and menu",
        onBackClick = null,
        menuIcon = Icons.Default.Check,
        onMenuClick = {}
    )
}

@Preview
@Composable
private fun TopBarWithTitleAndBackButtonAndMenuPreview() {
    TopAppBar(
        title = "TopBar with title, back button, and menu",
        onBackClick = {},
        menuIcon = Icons.Default.Check,
        onMenuClick = {}
    )
}