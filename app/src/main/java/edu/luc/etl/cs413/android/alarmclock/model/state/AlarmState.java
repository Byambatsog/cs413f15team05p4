package edu.luc.etl.cs413.android.alarmclock.model.state;

import edu.luc.etl.cs413.android.alarmclock.R;

/**
 * Created by byambatsog on 11/8/15.
 */
public class AlarmState implements AlarmclockState {

    public AlarmState(final AlarmclockSMStateView sm) {
        this.sm = sm;
    }

    private final AlarmclockSMStateView sm;

    @Override
    public void onClick() {
        sm.actionStop();
        sm.actionReset();
        sm.stopBeep();
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        sm.beep();
        sm.toAlarmState();
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.ALARM;
    }
}
