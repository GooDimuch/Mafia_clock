package com.example.dimuch.mafia_clock;

/**
 * Created by Dimuch on 03.05.2017.
 */

public class MyPresenter {

  public static final long TEN_SECOND = 10;
  private MyView myView;
  private CountDownTimerPausable countDownTimerPausableTime;

  private boolean isActiveTimer;
  private boolean isActiveSignal;
  private boolean isPause;
  private boolean isReset;

  public MyPresenter() {

  }

  public void bind(MyView myView) {
    this.myView = myView;
  }

  public boolean isActiveTimer() {
    return isActiveTimer;
  }

  public void doSomeThing(final int time) {
    //if (isActiveTimer) return;

    myView.disableButtons();

    countDownTimerPausableTime = new CountDownTimerPausable(time * 1000, 100) {
      @Override public void onTick(long millisUntilFinished) {
        myView.changeBtnTime(String.valueOf(millisUntilFinished / 1000 + 1));
        if (millisUntilFinished / 100 == TEN_SECOND * 10) {
          isActiveSignal = true;
          myView.playWarningSignal();
        }
      }

      @Override public void onFinish() {
        myView.changeBtnTime(String.valueOf(time));
        myView.stopSignal();
        isActiveTimer = false;
        isActiveSignal = false;

        if (time == 30) {
          myView.enableButton60Sec();
        }
        if (time == 60) {
          myView.enableButton30Sec();
        }
      }
    }.start();

    isActiveTimer = true;
  }

  public void pause() {
    if (!isPause && isActiveTimer) {
      isPause = true;
      myView.makeToast("Pause");
      myView.changeBtnPause("Unpause");
      countDownTimerPausableTime.pause();
      if (isActiveSignal) myView.pauseWarningSignal();
      return;
    }
    if (isPause && isActiveTimer) {
      isPause = false;
      myView.makeToast("Unpause");
      myView.changeBtnPause("Pause");
      countDownTimerPausableTime.start();
      if (isActiveSignal) myView.unpauseWarningSignal();
      return;
    }
  }

  public void reset() {
    isReset = true;
    if (isActiveTimer) {
      countDownTimerPausableTime.onFinish();
      countDownTimerPausableTime.cancel();
      if (isPause) myView.changeBtnPause("Pause");
    }
    isReset = false;
  }
}
