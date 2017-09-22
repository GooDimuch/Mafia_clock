package com.example.dimuch.mafia_clock;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

/**
 * Created by Dimuch on 22.09.2017.
 */

public class SoundHelper {

    Context context;
    final int MAX_STREAMS = 2;

    SoundPool sp;
    int warningSignal;
    int endSignal;

    public SoundHelper(Context context) {
        this.context = context;

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);

        warningSignal = sp.load(context, R.raw.warning_signal, 1);
        endSignal = sp.load(context, R.raw.finish, 1);
    }

    public void playWarningSignal() {
        Log.d(Constants.LOG_TAG, "warningSignal");
        sp.play(warningSignal, 1, 1, 0, 0, 1);
    }

    public void pauseWarningSignal() {
        Log.d(Constants.LOG_TAG, "pauseWarningSignal");
        sp.pause(warningSignal);
    }

    public void unpauseWarningSignal() {
        Log.d(Constants.LOG_TAG, "unpauseWarningSignal");
        sp.resume(warningSignal);
    }

    public void playEndSignal() {
        Log.d(Constants.LOG_TAG, "endSignal");
        sp.play(endSignal, 1, 1, 0, 0, 1);
    }

}
