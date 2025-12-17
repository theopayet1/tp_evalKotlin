package com.diiage.template.ui.core.extensions

import androidx.navigation.NavController
import com.diiage.template.ui.core.Destination

/**
 * Navigation “propre” vers une destination.
 *
 * - Remplace la backstack pour éviter de revenir sur l'écran précédent (ex : Splash).
 * - Utile après un écran d’entrée (Splash / Login).
 *
 * @param destination Destination cible (type-safe via [Destination]).
 *
 * @see NavController.navigate
 * @see androidx.navigation.NavOptionsBuilder.popUpTo
 */
//inutile de faire une fonction d'extention our sa present pour dire je sais faire
fun NavController.navigateAndClearBackStack(destination: Destination) {
    navigate(destination.route) {
        popUpTo(graph.startDestinationId) { inclusive = true }
        launchSingleTop = true
        restoreState = false
    }
}
