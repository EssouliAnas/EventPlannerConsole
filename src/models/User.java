package models;

//abstract class representing a user
public abstract class User {
    protected String name;
    protected String email; // Email of the user


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract void displayUserInfo();
}
