package com.example.dimuch.mafia_clock;

/**
 * Created by Dimuch on 03.05.2017.
 */

public interface MyView {

    void changeBtnTime(String time);

    void changeBtnPause(String status);

    void makeToast(String text);

    void disableButtons();

    void enableButton30Sec();

    void enableButton60Sec();

    void playWarningSignal();

    void pauseWarningSignal();

    void unpauseWarningSignal();

    void stopSignal();
}
