package com.app.alodokter.ui.activity.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.app.alodokter.R;
import com.app.alodokter.ui.activity.auth.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 20) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {

                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progressStatus == 20) {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    SplashScreenActivity.this.startActivity(intent);
                    SplashScreenActivity.this.finish();

                }
            }
        }).start();

    }

}
