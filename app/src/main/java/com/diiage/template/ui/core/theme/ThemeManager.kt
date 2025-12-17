package com.diiage.template.ui.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
/**
 * Gestion centralisée du thème de l’application.
 *
 *les états possibles du thème ([ThemeState]) : Clair, Sombre ou Système,
 *
 * La fonction [isDarkTheme] est composable afin de réagir automatiquement
 * aux changements de thème et de garantir une mise à jour
 *
 * @see ThemeState
 * @see androidx.compose.foundation.isSystemInDarkTheme
 */

sealed class ThemeState {
    object Light : ThemeState()
    object Dark : ThemeState()
    object System : ThemeState()
}

object ThemeManager {
    var themeState by mutableStateOf<ThemeState>(ThemeState.System)

    fun toggleTheme() {
        themeState = when (themeState) {
            ThemeState.Light -> ThemeState.Dark
            ThemeState.Dark -> ThemeState.System
            ThemeState.System -> ThemeState.Light
        }
    }

    fun setTheme(theme: ThemeState) {
        themeState = theme
    }

    @Composable
    fun isDarkTheme(): Boolean {
        return when (themeState) {
            ThemeState.Light -> false
            ThemeState.Dark -> true
            ThemeState.System -> isSystemInDarkTheme()
        }
    }
}