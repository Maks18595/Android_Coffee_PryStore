package com.first.android_coffee_prystore.pages.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.first.android_coffee_prystore.navigation.RootAppNavigation
import com.first.android_coffee_prystore.navigation.Routes
import com.first.android_coffee_prystore.pages.app.sign_in.SignInScreen
import com.first.android_coffee_prystore.pages.app.sign_in.SignInViewModel
import com.first.android_coffee_prystore.pages.app.sign_up.SignUpViewModel
import com.first.android_coffee_prystore.pages.app.sign_up.SignUpScreen
import com.first.android_coffee_prystore.pages.app.welcome.WelcomeScreen
import com.first.android_coffee_prystore.pages.app.welcome.WelcomeViewModel
import com.first.android_coffee_prystore.pages.app.splash.SplashScreen
import com.first.android_coffee_prystore.pages.app.splash.SplashViewModel
import com.first.android_coffee_prystore.ui.theme.Android_Coffee_PryStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_Coffee_PryStoreTheme {
                RootAppNavigation()
            }
        }
    }
}





