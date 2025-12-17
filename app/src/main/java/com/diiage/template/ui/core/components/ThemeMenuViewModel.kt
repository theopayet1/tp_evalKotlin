package com.diiage.template.ui.core.components

import android.content.Context
import androidx.lifecycle.ViewModel
import com.diiage.template.system.SoundManager
import com.diiage.template.ui.core.extensions.vibrateClick
import com.diiage.template.ui.core.theme.ThemeManager
import com.diiage.template.ui.core.theme.ThemeState

/**
 * ViewModel associé au menu de sélection du thème.
 *
 * Ce ViewModel centralise la logique liée au changement de thème :
 * - déclenchement d’un feedback sonore via [com.diiage.template.system.SoundManager],
 * - déclenchement d’une vibration courte via l’extension [vibrateClick],
 * - mise à jour du thème global via [com.diiage.template.ui.core.theme.ThemeManager].
 *
 *
 * @see com.diiage.template.ui.core.theme.ThemeManager
 * @see com.diiage.template.system.SoundManager
 * @see vibrateClick
 */
class ThemeMenuViewModel : ViewModel() {

    fun onNextThemeClicked(context: Context) {
        SoundManager.playClick()
        context.vibrateClick()
        ThemeManager.toggleTheme()
    }

    fun onThemeSelected(context: Context, theme: ThemeState) {
        SoundManager.playClick()
        context.vibrateClick()
        ThemeManager.setTheme(theme)
    }
}