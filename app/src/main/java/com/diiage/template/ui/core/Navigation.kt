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
 * Système de navigation de l’application.
 *
 * Cette navigation repose sur une sealed class [Destination] afin de définir
 * l’ensemble des écrans de manière type-safe, sans utiliser de routes en String
 * dispersées dans le code.
 *
 * Les extensions sur [NavGraphBuilder] et [NavController] permettent :
 * - d’ajouter des écrans de façon lisible et cohérente,
 * - de naviguer entre les écrans sans risque d’erreur de route,
 * - de centraliser la logique de navigation au même endroit.
 *
 * Le composable [AppNavHost] représente le point d’entrée de la navigation.
 * Il définit le graphe principal et le cycle de vie des écrans :
 * - démarrage sur l’écran Splash,
 * - navigation contrôlée vers l’écran Home.
 *
 * Cette approche améliore :
 * - la lisibilité du code,
 * - la maintenabilité,
 * - la compréhension du parcours utilisateur.
 *
 * @see Destination
 * @see AppNavHost
 * @see androidx.navigation.compose.NavHost
 */


sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object Splash : Destination(route = "splash")
    object Home : Destination(route = "home")
}


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
