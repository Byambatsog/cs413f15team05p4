package edu.luc.etl.cs413.android.alarmclock.android;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import edu.luc.etl.cs413.android.alarmclock.R;
import edu.luc.etl.cs413.android.alarmclock.common.AlarmclockUIUpdateListener;
import edu.luc.etl.cs413.android.alarmclock.model.AlarmclockModelFacade;
import edu.luc.etl.cs413.android.alarmclock.model.ConcreteAlarmclockModelFacade;

public class AlarmclockAdapter extends Activity implements AlarmclockUIUpdateListener {

    private static String TAG = "alarmclock-android-activity";

    /**
     * The state-based dynamic model.
     */
    private AlarmclockModelFacade model;
    private  MediaPlayer mediaPlayer ;

    protected void setModel(final AlarmclockModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteAlarmclockModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setUIUpdateListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    // TODO remaining lifecycle methods

    /**
     * Updates the seconds and minutes in the UI.
     * @param time
     */
    public void updateTime(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView tvS = (TextView) findViewById(R.id.seconds);


                //final TextView tvM = (TextView) findViewById(R.id.minutes);
                //final int seconds = time % Constants.SEC_PER_MIN;
                //final int minutes = time / Constants.SEC_PER_MIN;
                tvS.setText(Integer.toString(time / 10) + Integer.toString(time % 10));
                //tvM.setText(Integer.toString(minutes / 10) + Integer.toString(minutes % 10));
            }
        });
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView stateName = (TextView) findViewById(R.id.stateName);
                stateName.setText(getString(stateId));
            }
        });
    }

    @Override
    public void playDefaultNotification() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mediaPlayer.start();
    }

    @Override public void stopNotification() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    // forward event listener methods to the model
    public void onClick(final View view) {
        model.onClick();
    }
}
