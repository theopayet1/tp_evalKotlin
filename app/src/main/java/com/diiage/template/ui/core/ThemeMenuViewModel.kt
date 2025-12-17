package com.diiage.template.ui.core

import android.content.Context
import androidx.lifecycle.ViewModel
import com.diiage.template.system.SoundManager
import com.diiage.template.system.VibrationManager
import com.diiage.template.ui.core.theme.ThemeManager
import com.diiage.template.ui.core.theme.ThemeState

// ViewModel de la TopBar : déclenche un son quand on change le thème
class ThemeMenuViewModel : ViewModel() {

    fun onNextThemeClicked(context: Context) {
        SoundManager.playClick()
        VibrationManager.vibrateClick(context)
        ThemeManager.toggleTheme()
    }

    fun onThemeSelected(context: Context, theme: ThemeState) {
        SoundManager.playClick()
        VibrationManager.vibrateClick(context)
        ThemeManager.setTheme(theme)
    }
}