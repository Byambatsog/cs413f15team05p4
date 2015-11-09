package edu.luc.etl.cs413.android.alarmclock.model.clock;

/**
 * Created by byambatsog on 11/7/15.
 */
public interface ClockModel extends OnTickSource {

    void start();
    void stop();
}
