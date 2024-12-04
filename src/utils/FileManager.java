package utils;

import models.Event;
import models.Attendee;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager {


    public static void exportAttendeeList(Event event, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Attendee List for Event: " + event.getEventName() + "\n");
            writer.write("Name, Email, RSVP Status\n");
            for (Attendee attendee : event.getAttendees()) {
                writer.write(attendee.getName() + ", " + attendee.getEmail() + ", " + attendee.getRsvpStatus() + "\n");
            }
            System.out.println("Attendee list exported successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while exporting the attendee list.");
            e.printStackTrace();
        }
    }
}
