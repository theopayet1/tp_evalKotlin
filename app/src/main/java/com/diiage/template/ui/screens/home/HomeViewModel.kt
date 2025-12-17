package com.diiage.template.ui.screens.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diiage.template.domain.usecase.GetPortraitWaifusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.diiage.template.domain.model.WaifuImage

object HomeContracts {

    data class UiState(
        val isLoading: Boolean = true,
        val waifus: List<WaifuImage> = emptyList(),
        val errorMessage: String? = null
    )

    sealed interface UiAction {
        data object Load : UiAction
        data object Retry : UiAction
    }
}


class HomeViewModel(
    private val getPortraitWaifus: GetPortraitWaifusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeContracts.UiState())
    val uiState: StateFlow<HomeContracts.UiState> = _uiState.asStateFlow()

    init {
        onAction(HomeContracts.UiAction.Load)
    }

    fun onAction(action: HomeContracts.UiAction) {
        when (action) {
            HomeContracts.UiAction.Load,
            HomeContracts.UiAction.Retry -> load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            runCatching { getPortraitWaifus(limit = 10) }
                .onSuccess { list ->
                    _uiState.update { it.copy(isLoading = false, waifus = list, errorMessage = null) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Erreur réseau"
                        )
                    }
                }
        }
    }
}