package com.example.dimuch.mafia_clock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyView {

  @BindView(R.id.b30sec) Button b30sec;
  @BindView(R.id.b60sec) Button b60sec;
  @BindView(R.id.bPause) Button bPause;
  @BindView(R.id.bReset) Button bReset;
  @BindView(R.id.bTime) Button bTime;

  private MyPresenter myPresenter;
  private SoundHelper soundHelper;
  private int time;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    myPresenter = new MyPresenter();
    myPresenter.bind(this);

    soundHelper = new SoundHelper(getApplicationContext());

    time = 60;

    b30sec.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        makeToast("30sec");
        time = 30;

        if (!myPresenter.isActiveTimer()) {
          bTime.setText(String.valueOf(time));
        }

        b30sec.setEnabled(false);
        b60sec.setEnabled(true);
      }
    });

    b60sec.setEnabled(false);
    b60sec.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        makeToast("60sec");
        time = 60;

        if (!myPresenter.isActiveTimer()) {
          bTime.setText(String.valueOf(time));
        }

        b60sec.setEnabled(false);
        b30sec.setEnabled(true);
      }
    });

    bPause.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        myPresenter.pause();
      }
    });

    bReset.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        myPresenter.reset();
      }
    });

    bTime.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (!myPresenter.isActiveTimer()) {
          makeToast("doSomeThing");
          soundHelper.loadSignal();
          myPresenter.doSomeThing(time);
        }
      }
    });
  }

  @Override public void changeBtnPause(String status) {
    bPause.setText(status);
  }

  @Override public void makeToast(String text) {
    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
  }

  @Override public void changeBtnTime(String time) {
    bTime.setText(time);
  }

  @Override public void disableButtons() {
    b30sec.setEnabled(false);
    b60sec.setEnabled(false);
  }

  @Override public void enableButton30Sec() {
    b30sec.setEnabled(true);
  }

  @Override public void enableButton60Sec() {
    b60sec.setEnabled(true);
  }

  @Override public void playWarningSignal() {
    soundHelper.stopSignal();
  }

  @Override public void pauseWarningSignal() {
    soundHelper.pauseWarningSignal();
  }

  @Override public void unpauseWarningSignal() {
    soundHelper.unpauseWarningSignal();
  }

  @Override public void stopSignal() {
    soundHelper.playEndSignal();
  }
}
