package com.statlex.animalsay.util

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

private val audioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()

private val soundPool =
    SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build()

fun playShortSound(context: Context, assetPath: String) {
    val afd = context.assets.openFd(assetPath)

    val soundId = soundPool.load(afd, 1)

    soundPool.setOnLoadCompleteListener { _, _, _ ->
        soundPool.play(
            soundId, 1f, // left volume
            1f, // right volume
            1,  // priority
            0,  // loop (0 = no loop)
            1f  // rate
        )
    }
}


suspend fun playSoundAndWait(context: Context, assetPath: String) {/*
    CoroutineScope(Dispatchers.Main).launch {
        playSoundAndWait(context, "sounds/one.wav")
        playSoundAndWait(context, "sounds/two.wav")
        playSoundAndWait(context, "sounds/three.wav")
    }


        val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch {
            playSoundAndWait(context, "sounds/click.wav")
        }
    }) {
        Text("Play")
    }
    */

    suspendCancellableCoroutine<Unit> { cont ->
        val afd = context.assets.openFd(assetPath)

        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
            )
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            prepare()
            start()
        }


        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
            cont.resume(Unit)
        }

        cont.invokeOnCancellation {
            mediaPlayer.release()
        }
    }
}
