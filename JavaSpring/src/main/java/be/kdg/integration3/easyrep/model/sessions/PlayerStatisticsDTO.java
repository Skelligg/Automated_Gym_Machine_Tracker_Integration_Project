package be.kdg.integration3.easyrep.model.sessions;

public class PlayerStatisticsDTO {
    private String username;
    private long totalSessions;
    private long totalWeight;


    public PlayerStatisticsDTO(String username, long totalSessions, long totalWeight) {
        this.username = username;
        this.totalSessions = totalSessions;
        this.totalWeight = totalWeight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(long totalSessions) {
        this.totalSessions = totalSessions;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(long totalWeight) {
        this.totalWeight = totalWeight;
    }
}
