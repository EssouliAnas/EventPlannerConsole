package models;

import enums.RSVPResponse;
import interfaces.Searchable;
import interfaces.Exportable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Event implements Searchable, Exportable {
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String location;
    private int capacity;
    private List<Attendee> attendees;

    //initialize event details
    public Event(String eventName, String eventDate, String eventTime, String location, int capacity) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.location = location;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
    }

    //attendees list getter
    public List<Attendee> getAttendees() {
        return attendees;
    }

    //display event details
    public void displayEventDetails() {
        System.out.println("Event Details:");
        System.out.println("Name: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Time: " + eventTime);
        System.out.println("Location: " + location);
        System.out.println("Capacity: " + capacity);
        System.out.println("Attendees Confirmed: " + attendees.size());
    }

    //event capacity checker
    public boolean isAtCapacity() {
        return attendees.size() >= capacity;
    }

    //add an attendee
    public boolean addAttendee(Attendee attendee) {
        if (isAtCapacity()) {
            System.out.println("Cannot add attendee. Event is at full capacity.");
            return false;
        }
        attendees.add(attendee);
        System.out.println("Attendee added: " + attendee.getName());
        return true;
    }

    //remove  attendee
    public boolean removeAttendee(Attendee attendee) {
        if (attendees.remove(attendee)) {
            System.out.println("Attendee removed: " + attendee.getName());
            return true;
        }
        System.out.println("Attendee not found.");
        return false;
    }

    //search by name
    @Override
    public List<Attendee> searchByName(String name) {
        List<Attendee> matchingAttendees = new ArrayList<>();
        for (Attendee attendee : attendees) {
            if (attendee.getName().equalsIgnoreCase(name)) {
                matchingAttendees.add(attendee);
            }
        }
        return matchingAttendees;
    }

    //dort by name
    @Override
    public void sortAttendeesByName() {
        attendees.sort((attendee1, attendee2) -> attendee1.getName().compareToIgnoreCase(attendee2.getName()));
        System.out.println("Attendees sorted alphabetically by name:");
        for (Attendee attendee : attendees) {
            System.out.println("Name: " + attendee.getName() +
                    ", Email: " + attendee.getEmail() +
                    ", RSVP Status: " + attendee.getRsvpStatus());
        }
    }

    //edxoprt to a file
    @Override
    public void exportAttendees(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Attendee List for Event: " + eventName + "\n");
            writer.write("Name, Email, RSVP Status\n");
            for (Attendee attendee : attendees) {
                writer.write(attendee.getName() + ", " + attendee.getEmail() + ", " + attendee.getRsvpStatus() + "\n");
            }
            System.out.println("Attendee list exported successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while exporting the attendee list.");
            e.printStackTrace();
        }
    }
    public void sortAttendeesByRSVP() {
        attendees.sort((attendee1, attendee2) -> {
            if (attendee1.getRsvpStatus() == RSVPResponse.YES && attendee2.getRsvpStatus() != RSVPResponse.YES) {
                return -1;
            } else if (attendee1.getRsvpStatus() == RSVPResponse.MAYBE && attendee2.getRsvpStatus() == RSVPResponse.NO) {
                return -1;
            } else if (attendee1.getRsvpStatus() == attendee2.getRsvpStatus()) {
                return attendee1.getName().compareToIgnoreCase(attendee2.getName());
            } else {
                return 1;
            }
        });
    }
    public void displaySortedAttendeesByRSVP() {
        System.out.println("Sorted Attendees by RSVP Status:");
        for (Attendee attendee : attendees) {
            System.out.println("Name: " + attendee.getName() +
                    ", Email: " + attendee.getEmail() +
                    ", RSVP Status: " + attendee.getRsvpStatus());
        }
    }
    public String getEventName() {
        return eventName;
    }

}
