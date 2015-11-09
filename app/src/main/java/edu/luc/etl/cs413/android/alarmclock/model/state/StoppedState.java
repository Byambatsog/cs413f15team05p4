package edu.luc.etl.cs413.android.alarmclock.model.state;

import edu.luc.etl.cs413.android.alarmclock.R;

/**
 * Created by byambatsog on 11/8/15.
 */
public class StoppedState implements AlarmclockState {

    public StoppedState(final AlarmclockSMStateView sm) {
        this.sm = sm;
    }

    private final AlarmclockSMStateView sm;

    @Override
    public void onClick() {
        sm.actionStart();
        sm.actionIncRuntime();
        sm.toIncrementState();
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
