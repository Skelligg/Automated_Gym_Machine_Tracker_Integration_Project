package be.kdg.integration3.easyrep.model.sessions;

public class MachineSet {
    private int setNumber;
    private int id; //does set need an ID?
    private String setTime; //make this a LocalTime
    private int repCount;
    private double weightCount; //-- commented cause we're testing for the Tracer Bullet

//    public MachineSet(int setNumber, int setTime, double weightCount, int repCount) {
//        this.setNumber = setNumber;
//        this.setTime = setTime;
//        this.weightCount = weightCount;
//        this.repCount = repCount;
//    }


    public MachineSet(int setNumber, String setTime, int repCount, double weightCount) {
        this.setNumber = setNumber;
        this.setTime = setTime;
        this.repCount = repCount;
        this.weightCount = weightCount;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public int getId() {
        return id;
    }

    public double getWeightCount() {
        return weightCount;
    }

    public void setWeightCount(double weightCount) {
        this.weightCount = weightCount;
    }

    public void setId(int id) {
        this.id = id;
    }
}
