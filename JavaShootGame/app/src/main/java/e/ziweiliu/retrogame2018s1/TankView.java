package e.ziweiliu.retrogame2018s1;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class TankView extends View implements View.OnClickListener, Runnable {

    Handler timer;
    Paint paint;
    ArrayList<End> observers;
    ArrayList<Restart> observers_restart;
    Game game = new Game(/*getContext()*/);
    WinView winView;
    boolean closeDialog = false;
    int count_down = 5;
    private static final String TAG = "@@@@@@@@@";
    boolean end;
    public TankView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3.0f);
        observers = new ArrayList<>();
        observers_restart = new ArrayList<>();
        timer = new Handler();
        timer.postDelayed(this,10);
        winView = new WinView(getContext(),true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        game.draw(canvas, paint);
    }

    public boolean step() {
        if (game.gameEnd) { // if game has ended
            if (closeDialog == true && count_down == 0) { // when countdown is over close end screen and go back to menu
                winView.winDialog.dismiss();
                timer.postDelayed(this, 5000);

                for (End e : observers) {
                    e.end();
                }
            }

            if(closeDialog == false && count_down == 5){ // game has ended, display end screen and begin countdown
                notifyEnd();
                String time =   String.valueOf(count_down);
                winView.text_countdown.setText(time);
                timer.postDelayed(this, 1000);
            }

            if(closeDialog == true && count_down < 5 && count_down > 0){ // changing text of countdown
                String time =   String.valueOf(count_down);
                winView.text_countdown.setText(time);
                timer.postDelayed(this, 1000);
            }

            closeDialog = true;
            count_down--;

        }else{
            game.step();
            this.invalidate();
            timer.postDelayed(this,0);
        }

        return true;
    }

    @Override
    public void run() {
        step();
    }

    private void notifyEnd(){ // show end screen, notify restart listeners if restart button clicked

        if(winView.show().isShowing()==false && count_down == 5) {
            winView.is_RedWin = game.player1Wins();
            winView.show().show();
            winView.bt_start.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Restart r : observers_restart) {
                        winView.winDialog.dismiss();
                        r.restart();
                    }
                }
            });
        }
    }

    public void registerEnd(End e) {
        observers.add(e);
    } // register methods for observer pattern

    public void registerRestart(Restart r) {
        observers_restart.add(r);
    }
    @Override
    public void onClick(View view) { // updates tanks based on button input
        switch (view.getId()) {
            case R.id.UP1: game.tank1.update(Direction.N, game.blocks, game.tank2);
                break;
            case R.id.UP2: game.tank2.update(Direction.S, game.blocks, game.tank1);
                break;
            case R.id.DOWN1: game.tank1.update(Direction.S, game.blocks, game.tank2);
                break;
            case R.id.DOWN2: game.tank2.update(Direction.N, game.blocks, game.tank1);
                break;
            case R.id.LEFT1: game.tank1.update(Direction.W, game.blocks, game.tank2);
                break;
            case R.id.LEFT2: game.tank2.update(Direction.E, game.blocks, game.tank1);
                break;
            case R.id.RIGHT1: game.tank1.update(Direction.E, game.blocks, game.tank2);
                break;
            case R.id.RIGHT2: game.tank2.update(Direction.W, game.blocks, game.tank1);
                break;
            case R.id.FIRE1: game.fireAmmo(game.tank1);
                break;
            case R.id.FIRE2: game.fireAmmo(game.tank2);
                break;
        }
    }

}

