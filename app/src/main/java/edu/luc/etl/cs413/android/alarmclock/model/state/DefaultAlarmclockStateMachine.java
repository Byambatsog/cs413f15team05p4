package edu.luc.etl.cs413.android.alarmclock.model.state;

import edu.luc.etl.cs413.android.alarmclock.common.AlarmclockUIUpdateListener;
import edu.luc.etl.cs413.android.alarmclock.model.clock.ClockModel;
import edu.luc.etl.cs413.android.alarmclock.model.time.TimeModel;
import static edu.luc.etl.cs413.android.alarmclock.common.Constants.*;

/**
 * Created by byambatsog on 11/8/15.
 */
public class DefaultAlarmclockStateMachine implements AlarmclockStateMachine {

    public DefaultAlarmclockStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private AlarmclockState state;

    protected void setState(final AlarmclockState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    private AlarmclockUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final AlarmclockUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    @Override public synchronized void onClick() { state.onClick(); }
    @Override public synchronized void onTick() { state.onTick(); }

    @Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.getRuntime()); }
    @Override public void beep()            { uiUpdateListener.playDefaultNotification();}
    @Override public void stopBeep()        { uiUpdateListener.stopNotification();}

    // known states
    private final AlarmclockState STOPPED   = new StoppedState(this);
    private final AlarmclockState INCREMENT = new IncrementState(this);
    private final AlarmclockState DECREMENT = new DecrementState(this);
    private final AlarmclockState ALARM     = new AlarmState(this);

    // transitions
    @Override public void toIncrementState() { setState(INCREMENT); }
    @Override public void toStoppedState()   { setState(STOPPED); }
    @Override public void toDecrementState() { setState(DECREMENT); }
    @Override public void toAlarmState()     { setState(ALARM); }

    // actions
    @Override public void actionInit()       { toStoppedState(); actionReset(); }
    @Override public void actionReset()      { timeModel.resetRuntime(); timeModel.resetWaittime(); actionUpdateView(); }
    @Override public void actionStart()      { clockModel.start(); }
    @Override public void actionStop()       { clockModel.stop(); }
    @Override public void actionRestart()    { clockModel.stop(); clockModel.start(); }
    @Override public void actionIncRuntime() { timeModel.incRuntime();
                                               actionUpdateView();
                                               if(timeModel.getRuntime()==MAX_RUN_TIME) {
                                                   toDecrementState();
                                                   timeModel.resetWaittime();
                                                   beep(); }
                                             }
    @Override public void actionIncWaittime(){ timeModel.incWaittime();
                                               if(timeModel.getWaittime()==MAX_WAIT_TIME) {
                                                   toDecrementState();
                                                   timeModel.resetWaittime();
                                                   beep();
                                               }
                                               else toIncrementState(); }
    @Override public void actionDec()        { timeModel.decRuntime();
                                               actionUpdateView();
                                               if (timeModel.getRuntime()==0) {
                                                   toAlarmState();
                                                   beep();
                                               }
                                               else toDecrementState(); }
    @Override public void actionUpdateView() { state.updateView(); }
}
