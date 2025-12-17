@file:OptIn(ExperimentalMaterial3Api::class)

package com.diiage.template.ui.core.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.diiage.template.ui.core.theme.AppTheme
import com.diiage.template.ui.core.theme.ThemeManager
import com.diiage.template.ui.core.theme.ThemeState

/**
 * TopBar de l'app :
 * - Titre centré
 * - Menu déroulant pour changer le thème (Light/Dark/System)
 *
 * @param title Titre au centre de la barre.
 */
@Composable
fun WaifuTopBar(
    title: String = "WaifuCringe"
) {
    var expanded by remember { mutableStateOf(false) }
    val currentThemeLabel = when (ThemeManager.themeState) {
        ThemeState.Light -> "Light"
        ThemeState.Dark -> "Dark"
        ThemeState.System -> "System"
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            // Pas d'icône pour éviter toute dépendance "icons-extended"
            IconButton(onClick = { expanded = true }) {
                Text(text = "⋮")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Thème actuel : $currentThemeLabel") },
                    onClick = { expanded = false }
                )

                DropdownMenuItem(
                    text = { Text("Forcer : Light") },
                    onClick = {
                        expanded = false
                        ThemeManager.setTheme(ThemeState.Light)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Forcer : Dark") },
                    onClick = {
                        expanded = false
                        ThemeManager.setTheme(ThemeState.Dark)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Forcer : System") },
                    onClick = {
                        expanded = false
                        ThemeManager.setTheme(ThemeState.System)
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
        WaifuTopBar()
    }
}
