package com.statlex.animalsay.util

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.EnumMap
import kotlin.coroutines.resume

/*
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val context = this

    // fix volume buttons
    volumeControlStream = AudioManager.STREAM_MUSIC

    initAppLanguage(context)
*/

private val TAG = "Audio"

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

fun makeMediaPlayer(): MediaPlayer {
    return MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        )
//        setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
//        prepare()
//        start()
    }
}

enum class SoundType {
    CAT, DOG, COW;
}

val players = EnumMap<SoundType, MediaPlayer>(SoundType::class.java).apply {
    this[SoundType.CAT] = makeMediaPlayer()
    this[SoundType.DOG] = makeMediaPlayer()
    this[SoundType.COW] = makeMediaPlayer()
}

fun playSoundByPath(context: Context, assetPath: String, pathBy: SoundType): MediaPlayer {
//    suspendCancellableCoroutine<Unit> { cont ->

    val mediaPlayer = players.get(pathBy)

    if (mediaPlayer == null) {
        error("pathBy: $pathBy is not exists");
    }

    try {
        mediaPlayer.reset()

        val afd = context.assets.openFd(assetPath)

        Log.d(TAG, "playSoundAndWaitByPath: 3")

        mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)

        afd.close()

        Log.d(TAG, "playSoundAndWaitByPath: 4")
        mediaPlayer.prepare()
        Log.d(TAG, "playSoundAndWaitByPath: 5")
        mediaPlayer.start()
        Log.d(TAG, "playSoundAndWaitByPath: 6")
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return mediaPlayer

//        mediaPlayer.setOnCompletionListener {
//            mediaPlayer.release()
//            cont.resume(Unit)
//        }

//        cont.invokeOnCancellation {
//            mediaPlayer.release()
//        }
//    }
}

fun getIsMediaPlayerPlaying(mediaPlayer: MediaPlayer): Boolean {
    return try {
        mediaPlayer.isPlaying
    } catch (_: IllegalStateException) {
        false
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
