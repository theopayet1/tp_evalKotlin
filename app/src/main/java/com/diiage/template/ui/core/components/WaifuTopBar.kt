@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.diiage.template.ui.core.components
import com.diiage.template.R

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.diiage.template.ui.core.theme.AppTheme
import com.diiage.template.ui.core.theme.ThemeManager
import com.diiage.template.ui.core.theme.ThemeState

/**
 * TopBar "Waifu" avec menu thème.
 * La TopBar ne gère pas la logique : elle déclenche juste des callbacks (ViewModel).
 */
@Composable
fun WaifuTopBar(
    onNextThemeClicked: () -> Unit,
    onThemeSelected: (ThemeState) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val currentThemeLabel = when (ThemeManager.themeState) {
        ThemeState.Light -> Text(stringResource(R.string.theme_light))
        ThemeState.Dark -> Text(stringResource(R.string.theme_dark))
        ThemeState.System -> Text(stringResource(R.string.theme_system))
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Waifu",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Text(text = "⋮")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Light") },
                    onClick = {
                        expanded = false
                        onThemeSelected(ThemeState.Light)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Dark") },
                    onClick = {
                        expanded = false
                        onThemeSelected(ThemeState.Dark)
                    }
                )

                DropdownMenuItem(
                    text = { Text("System") },
                    onClick = {
                        expanded = false
                        onThemeSelected(ThemeState.System)
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun WaifuTopBarPreview() {
    AppTheme {
        WaifuTopBar(
            onNextThemeClicked = {},
            onThemeSelected = {}
        )
    }
}
