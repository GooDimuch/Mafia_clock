package com.example.dimuch.mafia_clock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyView{

    private Button b30sec;
    private Button b60sec;
    private Button bPause;
    private Button bReset;
    private Button bTime;
    private MyPresenter myPresenter;
    private int time;
    private SoundHelper soundHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPresenter = new MyPresenter();
        myPresenter.bind(this);

        time = 60;

        b30sec = (Button) findViewById(R.id.b30sec);
        b60sec = (Button) findViewById(R.id.b60sec);
        bPause = (Button) findViewById(R.id.bPause);
        bReset = (Button) findViewById(R.id.bReset);
        bTime = (Button) findViewById(R.id.bTime);

        b30sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            @Override
            public void onClick(View v) {
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
            @Override
            public void onClick(View v) {
                myPresenter.pause();
            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPresenter.reset();
            }
        });

        bTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPresenter.doSomeThing(time);
            }
        });

        soundHelper = new SoundHelper(getApplicationContext());
    }

    @Override
    public void changeBtnPause(String status) {
        bPause.setText(status);
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeBtnTime(String time) {
        bTime.setText(time);
    }

    @Override
    public void disableButtons() {
        b30sec.setEnabled(false);
        b60sec.setEnabled(false);
    }

    @Override
    public void enableButton30Sec() {
        b30sec.setEnabled(true);
    }

    @Override
    public void enableButton60Sec() {
        b60sec.setEnabled(true);
    }

    @Override
    public void playWarningSignal() {
        soundHelper.playWarningSignal();
    }

    @Override
    public void pauseWarningSignal() {
        soundHelper.pauseWarningSignal();
    }

    @Override
    public void unpauseWarningSignal() {
        soundHelper.unpauseWarningSignal();
    }

    @Override
    public void playEndSignal() {
        soundHelper.playEndSignal();
    }
}
