package be.kdg.integration3.easyrep.model;

import java.time.LocalDate;

public class MaintenanceAlarm {
    private boolean isActive;
    private LocalDate lastMaintenance;
    private int usageLimit;
    private LocalDate dateLimit;

    public MaintenanceAlarm(boolean isActive, LocalDate lastMaintenance, int usageLimit, LocalDate dateLimit) {
        this.isActive = isActive;
        this.lastMaintenance = lastMaintenance;
        this.usageLimit = usageLimit;
        this.dateLimit = dateLimit;
    }

    public void alarmNotify() {
        System.out.println("MAINTAIN THIS MACHINE");
    }

    public void alarmSet() {
        System.out.println("the alarm is set");
    }
}
