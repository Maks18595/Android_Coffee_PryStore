package com.first.android_coffee_prystore.pages.app.sign_in

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
import com.first.android_coffee_prystore.pages.app.splash.SplashViewModel
import com.first.android_coffee_prystore.ui.theme.PurpleGrey40

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    onNavigationNext: () -> Unit,
    onBackClick: () -> Unit,
    onTermsConditionsClick: () -> Unit,
    privacyPolicyOnClick: () -> Unit
) {
    Scaffold(
        topBar = { TopBar() },
        content = {
            SignInScreenContent(
                modifier = Modifier.padding(it),
                onNavigationNext = onNavigationNext,
                onBackClick = onBackClick,
                onTermsConditionsClick = onTermsConditionsClick,
                privacyPolicyOnClick = privacyPolicyOnClick
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(){
    TopAppBar(title = {
        Text(
            text = "SignIn",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
        colors = TopAppBarDefaults.topAppBarColors(PurpleGrey40)
    )
}

@Composable
private fun SignInScreenContent(
    modifier: Modifier,
    onNavigationNext: () -> Unit,
    onBackClick: () -> Unit,
    onTermsConditionsClick: () -> Unit,
    privacyPolicyOnClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "SignIn screen",
            modifier = Modifier.clickable { onNavigationNext() }
        )
        Text(
            text = "Back",
            modifier = Modifier.clickable { onBackClick() }
        )
        Text(
            text = "Terms & Conditions",
            modifier = Modifier.clickable { onTermsConditionsClick() }
        )
        Text(
            text = "Privacy Policy",
            modifier = Modifier.clickable { privacyPolicyOnClick() }
        )
    }
}
