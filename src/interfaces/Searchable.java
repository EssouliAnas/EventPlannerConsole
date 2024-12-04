package interfaces;

import models.Attendee;
import java.util.List;


public interface Searchable {

    List<Attendee> searchByName(String name);


    void sortAttendeesByName();
}
