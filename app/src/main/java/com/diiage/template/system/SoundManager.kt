package com.diiage.template.system

import android.media.AudioManager
import android.media.ToneGenerator

/**
 * Manager système : centralise le son
 * usage direct depuis un ViewModel / usecase.
 */
object SoundManager {

    private val toneGenerator: ToneGenerator by lazy {
        ToneGenerator(AudioManager.STREAM_NOTIFICATION, 80)
    }

    fun playClick() {
        toneGenerator.startTone(ToneGenerator.TONE_PROP_ACK, 120)
    }

}