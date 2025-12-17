package com.diiage.template.ui.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * ViewModel de base générique utilisé dans l’application.
 *
 * Cette classe fournit une implémentation commune pour :
 * - la gestion d’un état UI via [StateFlow],
 * - l’envoi d’événements one-shot (navigation, messages, actions ponctuelles),
 * - l’exécution de traitements asynchrones de manière sécurisée.
 *
 * L’état est exposé sous forme de [StateFlow]
 *
 * Les événements sont transmis via un [Channel] pour éviter les répétitions
 *
 * La méthode [fetchData] fournit un helper standardisé pour :
 * - exécuter une opération asynchrone en arrière-plan,
 * - gérer le succès et l’erreur,
 * - renvoyer le résultat sur le thread principal.
 *
 *
 * @param State Type représentant l’état UI associé au ViewModel.
 * @param application Contexte application utilisé par [AndroidViewModel].
 *
 * @see StateFlow
 * @see Channel
 * @see AndroidViewModel
 */

open class ViewModel<State>(initialState: State, application: Application): AndroidViewModel(application),
    KoinComponent {


    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<State>
        get() = _state


    private val _events = Channel<Any>(Channel.BUFFERED)


    val events: Flow<Any>
        get() = _events.receiveAsFlow()

    protected fun sendEvent(obj: Any) {
        viewModelScope.launch {
            _events.send(obj)
        }
    }


    fun <T> fetchData(
        source: suspend () -> T,
        onResult: Result<T>.() -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val success = source()
                launch(Dispatchers.Main) {
                    onResult(Result.success(success))
                }
            } catch (ex: Throwable) {
                launch(Dispatchers.Main) {
                    onResult(Result.failure(ex))
                }
            }
        }
    }
}