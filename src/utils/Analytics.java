package utils;

import models.Event;
import models.Attendee;
import enums.RSVPResponse;
import java.util.Map;
import java.util.HashMap;


public class Analytics {

    public static Map<RSVPResponse, Double> calculateRSVPStats(Event event) {
        Map<RSVPResponse, Double> stats = new HashMap<>();
        int totalAttendees = event.getAttendees().size();
        int yesCount = 0, noCount = 0, maybeCount = 0;

        for (Attendee attendee : event.getAttendees()) {
            switch (attendee.getRsvpStatus()) {
                case YES:
                    yesCount++;
                    break;
                case NO:
                    noCount++;
                    break;
                case MAYBE:
                    maybeCount++;
                    break;
            }
        }

        //calculate percentages
        stats.put(RSVPResponse.YES, (yesCount * 100.0) / totalAttendees);
        stats.put(RSVPResponse.NO, (noCount * 100.0) / totalAttendees);
        stats.put(RSVPResponse.MAYBE, (maybeCount * 100.0) / totalAttendees);

        return stats;
    }

    //display RSVP statistics
    public static void displayRSVPStats(Event event) {
        Map<RSVPResponse, Double> stats = calculateRSVPStats(event);
        System.out.println("RSVP Statistics:");
        System.out.printf("Yes: %.2f%%\n", stats.get(RSVPResponse.YES));
        System.out.printf("No: %.2f%%\n", stats.get(RSVPResponse.NO));
        System.out.printf("Maybe: %.2f%%\n", stats.get(RSVPResponse.MAYBE));
    }
}
