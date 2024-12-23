package com.first.android_coffee_prystore.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.dialog.Confirmation
import com.first.android_coffee_prystore.confirm.ConfirmScreen
import com.first.android_coffee_prystore.confirm.ConfirmViewModel
import com.first.android_coffee_prystore.pages.app.cart.CartScreen
import com.first.android_coffee_prystore.pages.app.cart.CartViewModel
import com.first.android_coffee_prystore.pages.app.main.MainScreen
import com.first.android_coffee_prystore.pages.app.sign_in.SignInScreen
import com.first.android_coffee_prystore.pages.app.sign_in.SignInViewModel
import com.first.android_coffee_prystore.pages.app.sign_up.SignUpScreen
import com.first.android_coffee_prystore.pages.app.sign_up.SignUpViewModel
import com.first.android_coffee_prystore.pages.app.splash.SplashScreen
import com.first.android_coffee_prystore.pages.app.splash.SplashViewModel
import com.first.android_coffee_prystore.pages.app.welcome.WelcomeScreen
import com.first.android_coffee_prystore.pages.app.welcome.WelcomeViewModel
import com.first.android_coffee_prystore.prototype.ProductScreen
import com.first.android_coffee_prystore.prototype.ProductViewModel


fun appToastShow(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun RootAppNavigation (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Splash.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Routes.Splash.route) {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                viewModel = viewModel,
                onNavigationNext = {
                    navController.navigate(route = Routes.Welcome.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Routes.Welcome.route) {
            val viewModel = hiltViewModel<WelcomeViewModel>()
            WelcomeScreen(
                viewModel = viewModel,
                onNavigateToSignIn = { navController.navigate(Routes.SignIn.route) },
                onNavigateToSignUp = { navController.navigate(Routes.SignUp.route) }
            )
        }


        composable(Routes.SignIn.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            val context = LocalContext.current
            SignInScreen(
                viewModel = viewModel,
                onNavigationNext = { navController.navigate(Routes.Main.route) },
                onBackClick = { navController.popBackStack() },
                onTermsConditionsClick = { appToastShow(context, "Terms and Conditions clicked") },
                privacyPolicyOnClick = { appToastShow(context, "Privacy Policy clicked") }
            )


        }

        composable(Routes.SignUp.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            val context = LocalContext.current
            SignUpScreen(
                viewModel = viewModel,
                onNavigationNext = { navController.navigate(Routes.Main.route) },
                onBackClick = { navController.popBackStack() },
                onTermsConditionsClick = { appToastShow(context, "Terms and Conditions clicked") },
                privacyPolicyOnClick = { appToastShow(context, "Privacy Policy clicked") },

            )
        }



        composable(Routes.Main.route) {
            MainScreen(
                onNavigateToProduct = {
                    navController.navigate(route = Routes.Product.getProductById(it))
                },
                onNavigateToNotifications = {
                    navController.navigate(route = Routes.Notifications.route)
                },
                onNavigateToConfirmation = {
                    navController.navigate(route = Routes.Confirmation.route)
                },
                onChangeFavourite = {

                }
            )
        }

        composable(Routes.Product.route){
            val viewModel = hiltViewModel<ProductViewModel>()
            val id = it.arguments?.getString(Routes.PRODUCT_ID)?.toLong()?: -1L
            ProductScreen(
                viewModel = viewModel,
                id = id,
                onBackClick = {
                    navController.popBackStack()
                },
                onBuyNowClick = {
                    navController.navigate(Routes.Cart.route)
                }
            )
        }

        composable(Routes.Cart.route){
            val viewModel = hiltViewModel<CartViewModel>()
            CartScreen(
                viewModel = viewModel,
                onNavigateToConfirm = {},
                onNavigateToProduct = {}

            )
        }

        composable(Routes.Confirmation.route){
            val viewModel = hiltViewModel<ConfirmViewModel>()

            ConfirmScreen(
                viewModel = viewModel,
                onNavigateToSuccess = {
                    navController.navigate(Routes.Success.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }

            )
        }



    }


}
