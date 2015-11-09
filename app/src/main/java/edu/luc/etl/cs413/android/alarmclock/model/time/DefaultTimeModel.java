package edu.luc.etl.cs413.android.alarmclock.model.time;

import static edu.luc.etl.cs413.android.alarmclock.common.Constants.*;
/**
 * Created by byambatsog on 11/8/15.
 */
public class DefaultTimeModel implements TimeModel {

    private int runtime = 0;

    private int waittime = 0;

    @Override
    public void resetRuntime() {
        runtime = 0;
    }

    @Override
    public void incRuntime() {
        if(runtime < MAX_RUN_TIME) runtime++;
    }

    @Override
    public void decRuntime() {
        if(runtime!=0) runtime--;
    }

    @Override
    public void resetWaittime(){
        waittime = 0;
    }

    @Override
    public void incWaittime(){
        if( waittime < MAX_WAIT_TIME ) waittime++;
    }

    @Override
    public int getRuntime(){
        return runtime;
    }

    @Override
    public int getWaittime(){
        return waittime;
    }
}
