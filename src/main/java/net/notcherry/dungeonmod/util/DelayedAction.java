package net.notcherry.dungeonmod.util;

public class DelayedAction {
    private int delayTicks;
    private int currentTick;

    public DelayedAction(int delayTicks) {
        this.delayTicks = delayTicks;
        this.currentTick = 0;
    }

    public boolean isReady() {
        return currentTick >= delayTicks;
    }

    public void tick() {
        if (currentTick < delayTicks) {
            currentTick++;
        }
    }
}
