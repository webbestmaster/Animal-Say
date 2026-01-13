package com.statlex.animalsay.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

private val audioAttributes = AudioAttributes.Builder()
    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
    .build()

private val soundPool = SoundPool.Builder()
    .setMaxStreams(1)
    .setAudioAttributes(audioAttributes)
    .build()

fun playShortSound(context: Context, assetPath: String) {
    val afd = context.assets.openFd(assetPath)

    val soundId = soundPool.load(afd, 1)

    soundPool.setOnLoadCompleteListener { _, _, _ ->
        soundPool.play(
            soundId,
            1f, // left volume
            1f, // right volume
            1,  // priority
            0,  // loop (0 = no loop)
            1f  // rate
        )
    }
}


