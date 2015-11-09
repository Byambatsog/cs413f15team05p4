package edu.luc.etl.cs413.android.alarmclock.model;

import edu.luc.etl.cs413.android.alarmclock.common.AlarmclockUIListener;
import edu.luc.etl.cs413.android.alarmclock.common.AlarmclockUIUpdateSource;

/**
 * Created by byambatsog on 11/9/15.
 */
public interface AlarmclockModelFacade extends AlarmclockUIListener, AlarmclockUIUpdateSource {

    void onStart();
}
