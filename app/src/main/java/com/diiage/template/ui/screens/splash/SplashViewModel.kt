package com.diiage.template.ui.screens.splash

import android.app.Application
import com.diiage.template.ui.core.Destination
import com.diiage.template.ui.core.ViewModel
import kotlinx.coroutines.delay
/**
 * ViewModel de l’écran Splash.
 *
 *
 * L’écran Splash n’a pas de logique métier complexe.
 * Son rôle est uniquement temporel (attente courte) avant de rediriger
 * l’utilisateur vers l’écran principal.
 *
 *
 * La navigation est déclenchée via un événement ([SplashContracts.Event])
 *
 * @see SplashContracts
 * @see Destination.Home
 */
interface SplashContracts {

    data class UiState(
        val isLoading: Boolean = true
    )

    sealed interface UiAction {
        data object SplashCompleted : UiAction
    }

    sealed interface Event {
        data class NavigateTo(val destination: Destination) : Event
    }
}

class SplashViewModel(
    application: Application
) : ViewModel<SplashContracts.UiState>(
    initialState = SplashContracts.UiState(),
    application = application
) {

    init {
        startSplashTimer()
    }

    private fun startSplashTimer() {
        fetchData(
            source = {
                delay(2000)
                true  // true est pas unit pour eviter le warning
            },
            onResult = {
                navigateToHome()
            }
        )
    }

    private fun navigateToHome() {
        sendEvent(SplashContracts.Event.NavigateTo(Destination.Home))
    }
}
