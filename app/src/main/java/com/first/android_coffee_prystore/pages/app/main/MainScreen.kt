package com.first.android_coffee_prystore.pages.app.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.first.android_coffee_prystore.R
import com.first.android_coffee_prystore.navigation.Routes
import com.first.android_coffee_prystore.navigation.NestedAppNavigation

@Composable
fun MainScreen(
) {
    val navController = rememberNavController()
    val navGraphs: List<Routes> = listOf(
        Routes.Favourites,
        Routes.Feed,
        Routes.Cart,
        Routes.Profile,
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navGraphs.forEach{
                    graph ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = when (graph) {
                                    Routes.Feed -> Icons.Filled.Home
                                    Routes.Cart -> Icons.Filled.ShoppingCart
                                    Routes.Favourites -> Icons.Filled.Favorite
                                    Routes.Profile -> Icons.Filled.Person
                                    else -> Icons.Filled.Person
                                },
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                stringResource(
                                    id = when (graph) {
                                        Routes.Feed -> R.string.label_feed
                                        Routes.Cart -> R.string.label_cart
                                        Routes.Favourites -> R.string.label_favourites
                                        Routes.Profile -> R.string.label_profile
                                        else -> -1
                                    })

                            )
                                 },
                        selected = currentDestination?.hierarchy?.any{it.route == graph.route} == true,
                        onClick = { TODO() }

                    )
                }
            }
        }
    ) {
NestedAppNavigation(
    navController = navController,
    startDestination = Routes.Feed.route,
    modifier = Modifier
        .fillMaxSize()
        .padding(buttom = it.calculateBottomPadding())
)
    }
}
