package com.diiage.template.ui.core.extensions

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
/**
 * Déclenche une vibration courte
 *
 * Cette extension sur [Context] centralise l’accès à l’API de vibration Android
 * et masque les différences d’implémentation entre les versions du système.
 *
 * ot varie la methode seulont la version android
 *
 * @see android.os.Vibrator
 * @see android.os.VibratorManager
 */
fun Context.vibrateClick() {
    val durationMs = 30L

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = getSystemService(VibratorManager::class.java)
        manager?.defaultVibrator?.vibrate(
            VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    } else {
        @Suppress("DEPRECATION")
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        @Suppress("DEPRECATION")
        vibrator?.vibrate(durationMs)
    }
}