package e.ziweiliu.retrogame2018s1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Typeface;

import static android.graphics.Typeface.*;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class GameActivity extends AppCompatActivity implements End, Restart{

    TankView tankView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tankView = (TankView) findViewById(R.id.tankView);
        tankView.registerEnd(this); // registering end observers
        tankView.registerRestart(this);
        Button UP1 = (Button) findViewById(R.id.UP1);
        Button DOWN1 = (Button) findViewById(R.id.DOWN1);
        Button LEFT1 = (Button) findViewById(R.id.LEFT1);
        Button RIGHT1 = (Button) findViewById(R.id.RIGHT1);
        Button FIRE1 = (Button) findViewById(R.id.FIRE1);
        Button UP2 = (Button) findViewById(R.id.UP2);
        Button DOWN2 = (Button) findViewById(R.id.DOWN2);
        Button LEFT2 = (Button) findViewById(R.id.LEFT2);
        Button RIGHT2 = (Button) findViewById(R.id.RIGHT2);
        Button FIRE2 = (Button) findViewById(R.id.FIRE2);

        UP1.setOnClickListener(tankView); // link buttons to the tankView
        DOWN1.setOnClickListener(tankView);
        LEFT1.setOnClickListener(tankView);
        RIGHT1.setOnClickListener(tankView);
        FIRE1.setOnClickListener(tankView);
        UP2.setOnClickListener(tankView);
        DOWN2.setOnClickListener(tankView);
        LEFT2.setOnClickListener(tankView);
        RIGHT2.setOnClickListener(tankView);
        FIRE2.setOnClickListener(tankView);

    }

    public void end() { // end method for when End observers are notified
        setResult(AppCompatActivity.RESULT_OK);
        finish();
    }


    private void startTimer(final long time, final AlertDialog.Builder b){

        //startTimer(3000,winView);
        CountDownTimer counter = new CountDownTimer(time,1000){
            public void onTick(long millisUntilDone){
                System.out.println("@@@@@@@@@@@seconds remaining: " + millisUntilDone / 1000);
            }

            public void onFinish() {
                b.show().cancel();
                finish();

            }
        }.start();
    }

    public void restart() { // restart method for when Restart observers are notified
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
