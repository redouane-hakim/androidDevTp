package com.example.androiddev;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity_vibration extends AppCompatActivity {

    private Vibrator vibrator;

    // demo variables (you can talk about these in class)
    private int durationMs = 200;
    private int amplitude = 180;     // 1–255 (some devices ignore)
    private int onMs = 80;
    private int offMs = 120;
    private int repeats = 4;

    private TextView tvDuration, tvAmplitude, tvOnOff, tvRepeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration);

        vibrator = getVibrator(this);

        tvDuration = findViewById(R.id.tvDuration);
        tvAmplitude = findViewById(R.id.tvAmplitude);
        tvOnOff = findViewById(R.id.tvOnOff);
        tvRepeats = findViewById(R.id.tvRepeats);

        SeekBar sbDuration = findViewById(R.id.sbDuration);
        SeekBar sbAmplitude = findViewById(R.id.sbAmplitude);
        SeekBar sbOn = findViewById(R.id.sbOn);
        SeekBar sbOff = findViewById(R.id.sbOff);
        SeekBar sbRepeats = findViewById(R.id.sbRepeats);

        Button btnOneShot = findViewById(R.id.btnOneShot);
        Button btnPattern = findViewById(R.id.btnPattern);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnHapticClick = findViewById(R.id.btnHapticClick);
        Button btnClick = findViewById(R.id.btnClick);
        Button btnTick = findViewById(R.id.btnTick);
        Button btnHeavyClick = findViewById(R.id.btnHeavyClick);
        Button btnMagic = findViewById(R.id.btnMagic);

        sbDuration.setProgress(durationMs);
        sbAmplitude.setProgress(amplitude);
        sbOn.setProgress(onMs);
        sbOff.setProgress(offMs);
        sbRepeats.setProgress(repeats);

        updateLabels();

        sbDuration.setOnSeekBarChangeListener(simpleListener(progress -> {
            durationMs = Math.max(progress, 10);
            updateLabels();
        }));

        sbAmplitude.setOnSeekBarChangeListener(simpleListener(progress -> {
            amplitude = Math.max(progress, 1);
            updateLabels();
        }));

        sbOn.setOnSeekBarChangeListener(simpleListener(progress -> {
            onMs = Math.max(progress, 10);
            updateLabels();
        }));

        sbOff.setOnSeekBarChangeListener(simpleListener(progress -> {
            offMs = Math.max(progress, 10);
            updateLabels();
        }));

        sbRepeats.setOnSeekBarChangeListener(simpleListener(progress -> {
            repeats = Math.max(progress, 1);
            updateLabels();
        }));

        btnOneShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canVibrate()) return;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // First function (ONESHOT == ONE vibration)
                    VibrationEffect effect = VibrationEffect.createOneShot(durationMs, amplitude);
                    vibrator.vibrate(effect);
                } else {

                    vibrator.vibrate(durationMs);
                }
            }
        });

        btnPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canVibrate()) return;

                // Pattern using ON/OFF timing repeated N times
                // timings: [off, on, off, on, ...] (first value is "delay before start")
                int segments = repeats * 2 + 1;
                long[] timings = new long[segments];
                int[] amps = new int[segments];

                timings[0] = 0;
                amps[0] = 0;

                for (int i = 0; i < repeats; i++) {
                    // ON segment
                    timings[1 + i * 2] = onMs;
                    amps[1 + i * 2] = amplitude;

                    // OFF segment
                    timings[2 + i * 2] = offMs;
                    amps[2 + i * 2] = 0;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //second function
                    // makes a wave using timing and amps param to  vibrate -> pause -> vibrate ....
                    // repeat to start a loop from the index specified (0 : array start , 1: array 2nd element ...)
                    VibrationEffect effect = VibrationEffect.createWaveform(timings, amps, -1);
                    vibrator.vibrate(effect);
                } else {
                    // old fallback: timings only (no amplitude array)
                    vibrator.vibrate(timings, -1);
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cancel to stop the vibration
                if (vibrator != null) vibrator.cancel();
            }
        });

        //  “UI haptic click” (doesn't need VIBRATE permission)
        btnHapticClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                v.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK);
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            }
        });

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    VibrationEffect effect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK);
                    vibrator.vibrate(effect);
                }
            }
        });
        btnTick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    VibrationEffect effect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK);
                    vibrator.vibrate(effect);
                }
            }
        });
        btnHeavyClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    VibrationEffect effect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK);
                    vibrator.vibrate(effect);
                }
            }
        });

        // VibrationEffect.Composition.Primitives_CLICK/QUICK_RISE
        // primitives

        btnMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] steps = {
                        200, 0, 160, 0, 255, 0, 160, 0,
                        200, 0, 160, 0, 255, 0, 160, 0,
                        200, 160, 200, 0, 255, 0, 160, 0,
                        200, 0, 160, 0, 255, 160, 200, 0
                };

                int tempoBpm = 150;  // change this: 120..180 is nice
                int stepMs = Math.max(40, (int) Math.round(60000.0 / tempoBpm / 4.0)); // 16th-note
                int pulseMs = Math.min(25, stepMs); // change this: 15..40

                // Build timings + amplitudes arrays
                ArrayList<Long> t = new ArrayList<>();
                ArrayList<Integer> a = new ArrayList<>();

                t.add(0L); a.add(0); // start immediately

                for (int amp : steps) {
                    if (amp <= 0) {
                        t.add((long) stepMs); a.add(0); // rest
                    } else {
                        int clamped = Math.max(1, Math.min(255, amp));
                        t.add((long) pulseMs); a.add(clamped);          // buzz
                        t.add((long) (stepMs - pulseMs)); a.add(0);     // rest to finish the step
                    }
                }

                long[] timings = new long[t.size()];
                int[] amps = new int[a.size()];
                for (int i = 0; i < t.size(); i++) {
                    timings[i] = t.get(i);
                    amps[i] = a.get(i);
                }

                VibrationEffect effect = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    effect = VibrationEffect.createWaveform(timings, amps, -1);
                    vibrator.vibrate(effect);
                }
            }
        });

        Button btn_retour = findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMain = new Intent(Activity_vibration.this , Main1.class);
                startActivity(iMain);
                finish();
            }
        });

    }

    private void updateLabels() {
        tvDuration.setText("Duration: " + durationMs + " ms");
        tvAmplitude.setText("Amplitude: " + amplitude + " (1–255)");

        double duty = (double) onMs / (double) (onMs + offMs);
        int dutyPercent = (int) Math.round(duty * 100.0);

        tvOnOff.setText("Pattern: ON " + onMs + " ms / OFF " + offMs + " ms  (ratio ~ " + dutyPercent + "% ON)");
        tvRepeats.setText("Repeats: " + repeats);
    }

    private boolean canVibrate() {
        if (vibrator == null) {
            Toast.makeText(this, "No vibrator service", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!vibrator.hasVibrator()) {
            Toast.makeText(this, "This phone has no vibrator", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Vibrator getVibrator(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            VibratorManager vm = (VibratorManager) context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
            return vm.getDefaultVibrator();
        } else {
            return (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
    }

    private SeekBar.OnSeekBarChangeListener simpleListener(OnProgressChanged cb) {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { cb.onChange(progress); }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        };
    }

    interface OnProgressChanged {
        void onChange(int progress);
    }
}
