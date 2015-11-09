package edu.luc.etl.cs413.android.alarmclock.model.state;

import edu.luc.etl.cs413.android.alarmclock.R;

/**
 * Created by byambatsog on 11/8/15.
 */
public class IncrementState implements AlarmclockState {

    public IncrementState(final AlarmclockSMStateView sm) {
        this.sm = sm;
    }

    private final AlarmclockSMStateView sm;

    @Override
    public void onClick() {
        sm.actionRestart();
        sm.actionIncRuntime();
        sm.toIncrementState();
    }

    @Override
    public void onTick() {
        sm.actionIncWaittime();
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENT;
    }
}
