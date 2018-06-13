package e.ziweiliu.retrogame2018s1;

import e.ziweiliu.retrogame2018s1.WelcomeView;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class WelcomeActivity extends AppCompatActivity {

    private WelcomeView welcomeView;
    MediaPlayer shoot_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeView = (WelcomeView)findViewById(R.id.welcomeView);
        shoot_sound = MediaPlayer.create(getApplicationContext(),R.raw.shoot);
    }

    public void playGame(View view) { // switches to the GameActivity
        Log.d("game","button clicked");
        shoot_sound.start();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
