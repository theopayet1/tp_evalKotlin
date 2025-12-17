package com.diiage.template.ui.core

import androidx.lifecycle.ViewModel
import com.diiage.template.system.SoundManager
import com.diiage.template.ui.core.theme.ThemeManager
import com.diiage.template.ui.core.theme.ThemeState

// ViewModel de la TopBar : déclenche un son quand on change le thème
class ThemeMenuViewModel : ViewModel() {

    fun onNextThemeClicked() {
        SoundManager.playClick()
        ThemeManager.toggleTheme()
    }

    fun onThemeSelected(theme: ThemeState) {
        SoundManager.playClick()
        ThemeManager.setTheme(theme)
    }
}