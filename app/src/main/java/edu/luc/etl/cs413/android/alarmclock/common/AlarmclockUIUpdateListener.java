package edu.luc.etl.cs413.android.alarmclock.common;

/**
 * Created by byambatsog on 11/7/15.
 */
public interface AlarmclockUIUpdateListener {

    void updateTime(int timeValue);
    void updateState(int stateId);
    //void playDefaultNotification(boolean looping);
    void playDefaultNotification();
    void stopNotification();
}
