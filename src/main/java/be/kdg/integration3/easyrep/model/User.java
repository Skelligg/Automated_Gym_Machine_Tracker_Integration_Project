package be.kdg.integration3.easyrep.domain;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public User(long id, String firstName, String lastName, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
