package world.termination.impl;

import world.termination.api.Termination;

public class TerminationCombined implements Termination {
    private final TerminationByTime byTime;
    private final TerminationByTicks byTicks;

    public TerminationCombined(int ticksToTerminate, int maxSeconds) {
        this.byTicks = new TerminationByTicks(ticksToTerminate);
        this.byTime = new TerminationByTime(maxSeconds);
    }



    public TerminationByTime getByTime() {
        return byTime;
    }

    public TerminationByTicks getByTicks() {
        return byTicks;
    }

    @Override
    public boolean isNotTerminated(int tickNumber, int elapsedSeconds) {
        return byTime.isNotTerminated(tickNumber, elapsedSeconds) && byTicks.isNotTerminated(tickNumber, elapsedSeconds);
    }

    @Override
    public String getTerminationMessage() {
        if (byTicks.getTerminationMessage() != null)
            return byTicks.getTerminationMessage();
        if (byTime.getTerminationMessage() != null)
            return byTime.getTerminationMessage();
        return null;

    }

    @Override
    public String toString() {
        return "TerminationCombined{" +
                "byTime=" + byTime +
                ", byTicks=" + byTicks +
                '}';
    }
}
