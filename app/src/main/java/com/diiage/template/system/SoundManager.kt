package com.diiage.template.system

import android.media.AudioManager
import android.media.ToneGenerator

/**
 * Manager système responsable du son de l’application.
 *
 * Ce manager utilise un [ToneGenerator] Android pour produire un son
 *
 *
 * Le choix de [ToneGenerator] permet :
 * - de générer un son simple sans embarquer de fichier audio,
 * - d’éviter la gestion de ressources audio externes,
 *
 * toneGenerator crée le son
 * playClick lire le sont
 */
object SoundManager {

    private val toneGenerator: ToneGenerator by lazy {
        ToneGenerator(AudioManager.STREAM_NOTIFICATION, 80)
    }

    fun playClick() {
        toneGenerator.startTone(ToneGenerator.TONE_PROP_ACK, 120)
    }
}