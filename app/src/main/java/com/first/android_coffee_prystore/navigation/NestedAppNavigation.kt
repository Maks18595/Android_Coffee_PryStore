package com.first.android_coffee_prystore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun NestedAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Routes.Feed.route
){
NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = startDestination,
){
    feedGraph(navController)
    favouritesGraph(navController)
    cartGraph(navController)
    profileGraph(navController)
}
}

fun NavGraphBuilder.feedGraph(
    navController: NavHostController,
){
    navigation(
        startDestination = Routes.Feed.route,
        route = Routes.FeedGraph.route
    ){
composable(Routes.Feed.route) {
   /* val viewModel = hiltViewModel<FeedViewModel>()
    FeedScreen()
    */
}
        composable(Routes.Search.route) {
            /* val viewModel = hiltViewModel<SearchViewModel>()
    SearchScreen()
    */
        }
    }
}

fun NavGraphBuilder.favouritesGraph(
    navController: NavHostController,
){
    navigation(
        startDestination = Routes.Favourites.route,
        route = Routes.FavouritesGraph.route
    ) {
        composable(Routes.Favourites.route) {
            /* val viewModel = hiltViewModel<FavouritesViewModel>()
             FavouritesScreen()
             */
        }

    }
}

fun NavGraphBuilder.cartGraph(
    navController: NavHostController,
){
    navigation(
        startDestination = Routes.Cart.route,
        route = Routes.CartGraph.route
    ) {
        composable(Routes.Cart.route) {
            /* val viewModel = hiltViewModel<CartViewModel>()
             CartScreen()
             */
        }

    }
}

fun NavGraphBuilder.profileGraph(
    navController: NavHostController,
){
    navigation(
        startDestination = Routes.Profile.route,
        route = Routes.Profile.route
    ) {
        composable(Routes.Profile.route) {
            /* val viewModel = hiltViewModel<ProfileViewModel>()
             ProfileScreen()
             */
        }

    }
}