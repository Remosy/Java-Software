package e.ziweiliu.retrogame2018s1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import static android.graphics.Typeface.createFromAsset;
import static java.lang.Boolean.TRUE;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class WinView extends AlertDialog.Builder{ // end screen

    private Context context;
    public Boolean is_RedWin;
    private Typeface fontFamily;
    public AlertDialog winDialog;
    public Button bt_start;
    public TextView text_countdown;

    protected WinView(Context context, Boolean is_play1Win) {
        super(context);
        this.context = context;
        this.is_RedWin = is_play1Win;
    }

    @Override
    public AlertDialog show() { // show end screen
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.win_view, null);
        this.setView(dialogView);
        AssetManager am = context.getApplicationContext().getAssets();
        fontFamily = Typeface.createFromAsset(am, "Circuitboard.ttf");
        TextView text_p1 = dialogView.findViewById(R.id.player1_result);
        TextView text_p2 = dialogView.findViewById(R.id.player2_result);
        text_p1.setTypeface(fontFamily);
        text_p2.setTypeface(fontFamily);
        String s1 = "Winner";
        String s2 = "Lose";
        if (is_RedWin == false) {
            text_p1.setText(s1);
            text_p2.setText(s2);
        } else {
            text_p1.setText(s2);
            text_p2.setText(s1);
        }
        bt_start = (Button) dialogView.findViewById(R.id.bt_start);
        text_countdown = (TextView) dialogView.findViewById(R.id.countdown);
        text_countdown.setTypeface(fontFamily);
        winDialog = this.create();
        return winDialog;
    }

}
