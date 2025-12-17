package com.diiage.template.ui.core.extensions

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

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