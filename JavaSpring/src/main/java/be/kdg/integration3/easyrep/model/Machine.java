package be.kdg.integration3.easyrep.model;

public class Machine {
    private int id;
    private String name;
    private MaintenanceAlarm alarm;
    private String imageAddress;


    public Machine(int id, String name, MaintenanceAlarm alarm, String imageAddress) {
        this.id = id;
        this.name = name;
        this.alarm = alarm;
        this.imageAddress = imageAddress;
    }

    public void alarmSet() {
        alarm.alarmSet();
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MaintenanceAlarm getAlarm() {
        return alarm;
    }

    public void setAlarm(MaintenanceAlarm alarm) {
        this.alarm = alarm;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
