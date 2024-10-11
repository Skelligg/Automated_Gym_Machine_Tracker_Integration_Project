package be.kdg.integration3.easyrep.domain;

public class Machine {
    private long id;
    private String name;
    private MaintenanceAlarm alarm;
    private String imageAddress;

    public void alarmSet() {
        alarm.alarmSet();
    };
}
