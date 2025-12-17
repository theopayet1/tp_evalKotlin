package com.diiage.template.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.diiage.template.domain.model.WaifuImage
import com.diiage.template.ui.core.components.input.PrimaryButton
import com.diiage.template.ui.core.theme.AppTheme
import org.koin.androidx.compose.koinViewModel
/**
 * Contenu principal de l’écran Home.
 *
 * Ce composant gère l’affichage des différents états de l’écran :
 * - chargement des données (indicateur de progression),
 * - affichage d’une erreur avec possibilité de réessayer,
 * - affichage d’une grille d’images lorsque les données sont disponibles.
 *
 *
 * @param state État UI courant de l’écran Home (loading, erreur, données).
 * @param onRetry Action déclenchée lorsque l’utilisateur souhaite relancer le chargement.
 *
 * @see WaifuImage
 * @see HomeContracts.UiState
 */

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    HomeContent(
        state = state,
        onRetry = { viewModel.onAction(HomeContracts.UiAction.Retry) }
    )
}

/**
 * Écran Home :
 * - charge 10 images portrait
 * - affiche : loading / erreur / grille
 */
@Composable
private fun HomeContent(
    state: HomeContracts.UiState,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
            return
        }

        state.errorMessage?.let { msg ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = msg,
                    color = MaterialTheme.colorScheme.error
                )
                PrimaryButton(
                    onClick = onRetry,
                    text = "Réessayer"
                )
            }
            return
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.waifus) { waifu ->
                WaifuCard(waifu)
            }
        }
    }
}

@Composable
private fun WaifuCard(waifu: WaifuImage) {
    val isPreview = LocalInspectionMode.current
    val label = remember(waifu.id) { "#${waifu.id}" }

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        if (isPreview) {
            Text(text = label, color = MaterialTheme.colorScheme.onBackground)
        } else {
            AsyncImage(
                model = waifu.url,
                contentDescription = "Waifu $label"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    val fake = listOf(
        WaifuImage(1, "https://cdn.waifu.im/8108.png", null, 1536, 2304),
        WaifuImage(2, "https://cdn.waifu.im/8108.png", null, 1536, 2304)
    )

    AppTheme {
        HomeContent(
            state = HomeContracts.UiState(isLoading = false, waifus = fake),
            onRetry = {}
        )
    }
}
