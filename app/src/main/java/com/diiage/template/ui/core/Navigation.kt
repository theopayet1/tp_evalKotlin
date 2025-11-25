package com.diiage.template.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diiage.template.ui.screens.home.HomeScreen
import com.diiage.template.ui.screens.splash.SplashScreen

sealed class Destination(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {

    // Authentication
    object Splash : Destination(route = "splash")

    object Home : Destination(route = "home")
}

// Extension for NavGraphBuilder
fun NavGraphBuilder.composable(
    destination: Destination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(
    route = destination.route,
    arguments = destination.arguments,
    deepLinks = deepLinks
) { backStackEntry ->
    content(backStackEntry)
}

// Extension for NavController
fun NavController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) = navigate(
    route = destination.route,
    navOptions = navOptions,
    navigatorExtras = navigatorExtras
)

@Composable
fun NavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route,
        modifier = modifier
    ) {
        // Authentication Flow
        composable(Destination.Splash) { SplashScreen(navController) }
        composable(Destination.Home) { HomeScreen(navController) }
    }
}