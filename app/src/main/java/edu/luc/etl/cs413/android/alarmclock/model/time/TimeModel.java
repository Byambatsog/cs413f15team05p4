package edu.luc.etl.cs413.android.alarmclock.model.time;

/**
 * Created by byambatsog on 11/8/15.
 */
public interface TimeModel {

    void resetRuntime();
    void incRuntime();
    void decRuntime();
    void resetWaittime();
    void incWaittime();
    public int getRuntime();
    public int getWaittime();
}
