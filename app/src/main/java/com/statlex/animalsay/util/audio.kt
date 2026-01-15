package com.statlex.animalsay.util

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import java.util.EnumMap

/*
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val context = this

    // fix volume buttons
    volumeControlStream = AudioManager.STREAM_MUSIC

    initAppLanguage(context)
*/

private val TAG = "Audio"

private val soundPool = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(
    AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
).build()

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

enum class SoundTrackEnum {
    background, main, sfx;
}

val playerMap = EnumMap<SoundTrackEnum, MediaPlayer>(SoundTrackEnum::class.java).apply {
    this[SoundTrackEnum.background] = makeMediaPlayer()
    this[SoundTrackEnum.main] = makeMediaPlayer()
    this[SoundTrackEnum.sfx] = makeMediaPlayer()
}

fun playSoundByTrack(context: Context, assetPath: String, trackEnum: SoundTrackEnum): MediaPlayer {
    val mediaPlayer = playerMap.get(trackEnum)

    if (mediaPlayer == null) {
        error("trackEnum: $trackEnum is not exists");
    }

    try {
        mediaPlayer.reset()

        val assetFileDescriptor = context.assets.openFd(assetPath)

        mediaPlayer.setDataSource(
            assetFileDescriptor.fileDescriptor,
            assetFileDescriptor.startOffset,
            assetFileDescriptor.length
        )

        assetFileDescriptor.close()

        mediaPlayer.prepare()
        mediaPlayer.start()
    } catch (exception: Exception) {
        exception.printStackTrace()
    }

    return mediaPlayer
}

fun getIsMediaPlayerPlaying(mediaPlayer: MediaPlayer): Boolean {
    return try {
        mediaPlayer.isPlaying
    } catch (_: IllegalStateException) {
        false
    }
}

fun playSoundListByTrack(
    context: Context,
    assetPathList: MutableList<String>,
    trackEnum: SoundTrackEnum,
): MediaPlayer {
    val mediaPlayer = playerMap.get(trackEnum)

    if (mediaPlayer == null) {
        error("trackEnum: $trackEnum is not exists");
    }

    val assetPath = assetPathList.removeFirstOrNull()

    if (assetPath == null) {
        return mediaPlayer
    }

    playSoundByTrack(context, assetPath, trackEnum);

    mediaPlayer.setOnCompletionListener {
        playSoundListByTrack(context, assetPathList, trackEnum)
    }

    return mediaPlayer
}
