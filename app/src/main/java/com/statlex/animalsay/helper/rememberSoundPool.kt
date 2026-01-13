package com.statlex.animalsay.helper

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberSoundPool(soundRes: Int): Pair<SoundPool, Int> {
    val context = LocalContext.current

    val audioAttributes = remember {
        AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
    }

    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    val soundId = remember {
        soundPool.load(context, soundRes, 1)
    }

/*
    soundPool.apply {
        if (isLoaded.value) {
            play(
                soundId,
                1f,
                1f,
                1,
                0,
                1f
            )
        }
    }
*/

    DisposableEffect(Unit) {
/*
        soundPool.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                isLoaded.value = true
            }
        }
*/

        onDispose {
            soundPool.release()
        }
    }

    return soundPool to soundId
}
