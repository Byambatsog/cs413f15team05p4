package edu.luc.etl.cs413.android.alarmclock.model;

import edu.luc.etl.cs413.android.alarmclock.common.AlarmclockUIUpdateListener;
import edu.luc.etl.cs413.android.alarmclock.model.clock.ClockModel;
import edu.luc.etl.cs413.android.alarmclock.model.clock.DefaultClockModel;
import edu.luc.etl.cs413.android.alarmclock.model.state.AlarmclockStateMachine;
import edu.luc.etl.cs413.android.alarmclock.model.state.DefaultAlarmclockStateMachine;
import edu.luc.etl.cs413.android.alarmclock.model.time.DefaultTimeModel;
import edu.luc.etl.cs413.android.alarmclock.model.time.TimeModel;

/**
 * Created by byambatsog on 11/9/15.
 */
public class ConcreteAlarmclockModelFacade implements AlarmclockModelFacade {

    private AlarmclockStateMachine stateMachine;

    private ClockModel clockModel;

    private TimeModel timeModel;

    public ConcreteAlarmclockModelFacade() {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        stateMachine = new DefaultAlarmclockStateMachine(timeModel, clockModel);
        clockModel.setOnTickListener(stateMachine);
    }

    @Override
    public void onStart() {
        stateMachine.actionInit();
    }

    @Override
    public void setUIUpdateListener(final AlarmclockUIUpdateListener listener) {
        stateMachine.setUIUpdateListener(listener);
    }

    @Override
    public void onClick() {
        stateMachine.onClick();
    }
}
