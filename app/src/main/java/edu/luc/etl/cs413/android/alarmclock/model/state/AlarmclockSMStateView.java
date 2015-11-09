package edu.luc.etl.cs413.android.alarmclock.model.state;

/**
 * Created by byambatsog on 11/8/15.
 */
public interface AlarmclockSMStateView {

    // transitions
    void toIncrementState();
    void toStoppedState();
    void toDecrementState();
    void toAlarmState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionRestart();
    void actionIncRuntime();
    void actionIncWaittime();
    void actionDec();
    void actionUpdateView();

    // state-dependent UI updates
    void updateUIRuntime();
    void beep();
    void stopBeep();
}
