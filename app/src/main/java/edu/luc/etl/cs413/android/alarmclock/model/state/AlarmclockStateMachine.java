package edu.luc.etl.cs413.android.alarmclock.model.state;

import edu.luc.etl.cs413.android.alarmclock.common.AlarmclockUIListener;
import edu.luc.etl.cs413.android.alarmclock.common.AlarmclockUIUpdateSource;
import edu.luc.etl.cs413.android.alarmclock.model.clock.OnTickListener;

/**
 * Created by byambatsog on 11/8/15.
 */
public interface AlarmclockStateMachine extends AlarmclockUIListener, OnTickListener, AlarmclockUIUpdateSource, AlarmclockSMStateView {
}
