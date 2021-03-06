package com.example.swissarmy.models;

import android.os.CountDownTimer;

/**
 * @author Jannis Thaler
 * @version 1.0.0
 * @date 16.11.2020
 */
public abstract class CountUpTimer extends CountDownTimer {
    private final long duration;

    protected CountUpTimer(long durationMs, long interval_ms) {
        super(durationMs, interval_ms);
        this.duration = durationMs;
    }

    public abstract void onTickMs(long second);

    @Override
    public void onTick(long msUntilFinished) {
        long msecond = duration - msUntilFinished;
        onTickMs(msecond);
    }

    @Override
    public void onFinish() {
        onTick(duration / 1000);
    }
}
