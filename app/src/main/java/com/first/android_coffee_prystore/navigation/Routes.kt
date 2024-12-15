package com.first.android_coffee_prystore.navigation

sealed class Routes(val route: String) {
    data object Splash: Routes("splash")
    data object Welcome: Routes("welcome")
    data object SignIn: Routes("sign_in")
    data object SignUp: Routes("sign_up")
    data object Main: Routes("main")

    data object Feed: Routes("feed")
    data object Search: Routes("feed/search")
    data object Favourites: Routes("main/favourites")
    data object Cart: Routes("main/cart")
    data object Profile: Routes("main/profile")

    data object FeedGraph: Routes("feed_graph")
    data object FavouritesGraph: Routes("favourites_graph")
    data object CartGraph: Routes("cart_graph")
    data object ProfileGraph: Routes("profile_graph")
}