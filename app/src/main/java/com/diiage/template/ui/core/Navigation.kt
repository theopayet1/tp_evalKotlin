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

/**
 * Décrit les écrans de l’application via des routes “type-safe”.
 * Objectif : éviter les strings en dur partout et limiter les fautes de frappe.
 */
sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object Splash : Destination(route = "splash")
    object Home : Destination(route = "home")
}

/**
 * Variante pratique de `composable()` : on passe un [Destination] au lieu d’une route en String.
 */
fun NavGraphBuilder.composable(
    destination: Destination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = deepLinks
    ) { backStackEntry ->
        content(backStackEntry)
    }
}

/**
 * Navigation “type-safe” : on navigue vers un [Destination] plutôt que vers une String.
 */
fun NavController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    navigate(
        route = destination.route,
        navOptions = navOptions,
        navigatorExtras = navigatorExtras
    )
}

/**
 * Navigation utile après un écran d’entrée (Splash, Login…).
 * Elle remplace la pile de navigation pour éviter un retour arrière sur l’écran précédent.
 */
fun NavController.navigateAndClearBackStack(
    destination: Destination
) {
    navigate(destination.route) {
        popUpTo(graph.startDestinationId) { inclusive = true }
        launchSingleTop = true
        restoreState = false
    }
}

/**
 * Hôte de navigation principal.
 *
 * Note : le nom est volontairement `AppNavHost` pour éviter le conflit avec
 * `androidx.navigation.compose.NavHost`.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route,
        modifier = modifier
    ) {
        composable(Destination.Splash) { SplashScreen(navController) }
        composable(Destination.Home) { HomeScreen(navController) }
    }
}
