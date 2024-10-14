package be.kdg.integration3.easyrep.domain;

import java.time.LocalDate;

public class MaintenanceAlarm {
    private boolean isActive;
    private LocalDate lastMaintenance;
    private int usageLimit;
    private LocalDate dateLimit;

    public void alarmNotify() {
        System.out.println("MAINTAIN THIS MACHINE");
    }

    public void alarmSet() {
        System.out.println("the alarm is set");
    }
}
