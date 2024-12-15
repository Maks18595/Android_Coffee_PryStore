package com.first.android_coffee_prystore.pages.app.welcome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.first.android_coffee_prystore.ui.theme.PurpleGrey40

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    Scaffold(
        topBar = { TopBar() },
        content = {
            WelcomeScreenContent(
                modifier = Modifier.padding(it),
                onNavigateToSignIn = onNavigateToSignIn,
                onNavigateToSignUp = onNavigateToSignUp
            )
        }
    )
}

@Composable
private fun WelcomeScreenContent(
    modifier: Modifier,
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Sign In",
            modifier = Modifier.clickable { onNavigateToSignIn() }
        )
        Text(
            text = "Sign Up",
            modifier = Modifier.clickable { onNavigateToSignUp() }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(){
    TopAppBar(title = {
        Text(
            text = "Welcome",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
        colors = TopAppBarDefaults.topAppBarColors(PurpleGrey40)
    )
}

