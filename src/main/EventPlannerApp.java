package main;

import models.Event;
import models.Attendee;
import enums.RSVPResponse;
import utils.Analytics;

import java.util.List;
import java.util.Scanner;


public class EventPlannerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Event event = null;

        System.out.println("Welcome to the Event Planner App!");

        // Create a new event
        System.out.println("Let's set up your event.");
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter event date (e.g., 2024-12-31): ");
        String eventDate = scanner.nextLine();

        System.out.print("Enter event time (e.g., 18:00): ");
        String eventTime = scanner.nextLine();

        System.out.print("Enter event location: ");
        String location = scanner.nextLine();

        System.out.print("Enter event capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline


        event = new Event(eventName, eventDate, eventTime, location, capacity);

        // menu loop
        int choice = -1;
        while (choice != 0) {
            System.out.println("\nMenu:");
            System.out.println("1. View Event Details");
            System.out.println("2. Add Attendee");
            System.out.println("3. Remove Attendee");
            System.out.println("4. Search Attendees");
            System.out.println("5. Sort and Display Attendees");
            System.out.println("6. Display RSVP Statistics");
            System.out.println("7. Export Attendee List");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    event.displayEventDetails();
                    break;
                case 2:
                    addAttendee(event, scanner);
                    break;
                case 3:
                    removeAttendee(event, scanner);
                    break;
                case 4:
                    searchAttendees(event, scanner);
                    break;
                case 5:
                    event.sortAttendeesByRSVP();
                    event.displaySortedAttendeesByRSVP();
                    System.out.println("Attendees sorted and displayed");
                    break;
                case 6:
                    Analytics.displayRSVPStats(event);
                    break;
                case 7:
                    System.out.print("Enter file name to export (e.g., attendees.csv): ");
                    String fileName = scanner.nextLine();
                    event.exportAttendees(fileName);
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // add an attendee
    private static void addAttendee(Event event, Scanner scanner) {
        if (event.isAtCapacity()) {
            System.out.println("Cannot add more attendees. Event is at full capacity.");
            return;
        }

        System.out.print("Enter attendee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter attendee email: ");
        String email = scanner.nextLine();

        System.out.print("Enter RSVP status (YES/NO/MAYBE): ");
        String rsvpInput = scanner.nextLine().toUpperCase();

        RSVPResponse rsvpStatus;
        try {
            rsvpStatus = RSVPResponse.valueOf(rsvpInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid RSVP status. Defaulting to MAYBE.");
            rsvpStatus = RSVPResponse.MAYBE;
        }

        Attendee attendee = new Attendee(name, email, rsvpStatus);
        event.addAttendee(attendee);
        System.out.println("Attendee added successfully.");
    }

    // remove an attendee
    private static void removeAttendee(Event event, Scanner scanner) {
        System.out.print("Enter attendee name to remove: ");
        String name = scanner.nextLine();

        List<Attendee> foundAttendees = event.searchByName(name);
        if (foundAttendees.isEmpty()) {
            System.out.println("No attendee found with that name.");
        } else {
            Attendee attendeeToRemove = foundAttendees.get(0); // Assuming first match
            event.removeAttendee(attendeeToRemove);
            System.out.println("Attendee removed successfully.");
        }
    }

    //search attendees
    private static void searchAttendees(Event event, Scanner scanner) {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Attendee> foundAttendees = event.searchByName(name);
        if (foundAttendees.isEmpty()) {
            System.out.println("No attendees found with that name.");
        } else {
            System.out.println("Attendees found:");
            for (Attendee attendee : foundAttendees) {
                attendee.displayUserInfo();
            }
        }
    }
}
