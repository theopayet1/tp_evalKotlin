package com.diiage.template.system

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

/**
 * Manager système : centralise la vibration (feedback UI).
 * Appelé depuis un ViewModel (pas depuis les Composables).
 */
object VibrationManager {

    fun vibrateClick(context: Context) {
        val durationMs = 30L

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = context.getSystemService(VibratorManager::class.java)
            val vibrator = manager?.defaultVibrator
            vibrator?.vibrate(
                VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            @Suppress("DEPRECATION")
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            @Suppress("DEPRECATION")
            vibrator?.vibrate(durationMs)
        }
    }
}
