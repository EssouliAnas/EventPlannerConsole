package models;

import enums.RSVPResponse;

// Attendee class extending User
public class Attendee extends User {
    private RSVPResponse rsvpStatus;

    // initialize attendee details
    public Attendee(String name, String email, RSVPResponse rsvpStatus) {
        super(name, email); // Call to parent class constructor
        this.rsvpStatus = rsvpStatus;
    }

    // abstract method from User class
    @Override
    public void displayUserInfo() {
        System.out.println("Attendee Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("RSVP Status: " + rsvpStatus);
    }

    // Getter
    public RSVPResponse getRsvpStatus() {
        return rsvpStatus;
    }

    // Setter
    public void setRsvpStatus(RSVPResponse rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
