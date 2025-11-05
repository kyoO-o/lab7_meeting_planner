package edu.sc.csce747.MeetingPlanner;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void testDefaultConstructor() {
        Room r = new Room();
        assertEquals("", r.getID());
    }

    @Test
    public void testConstructorWithID() {
        Room r = new Room("A101");
        assertEquals("A101", r.getID());
    }

    @Test
    public void testAddAndRemoveMeeting() throws TimeConflictException {
        Room r = new Room("B202");
        Meeting m1 = new Meeting(3, 10, 10, 12, new ArrayList<>(), r, "Morning");
        Meeting m2 = new Meeting(3, 10, 12, 14, new ArrayList<>(), r, "Afternoon");

        // Add meetings
        r.addMeeting(m1);
        r.addMeeting(m2);

        assertEquals("Morning", r.getMeeting(3, 10, 0).getDescription());
        assertEquals("Afternoon", r.getMeeting(3, 10, 1).getDescription());

        // Remove meeting
        r.removeMeeting(3, 10, 0);
        assertEquals("Afternoon", r.getMeeting(3, 10, 0).getDescription());
    }

    @Test
    public void testPrintAgenda() throws TimeConflictException {
        Room r = new Room("C303");
        Meeting m = new Meeting(4, 15, 9, 11, new ArrayList<>(), r, "Team Meeting");
        r.addMeeting(m);

        String agendaMonth = r.printAgenda(4);
        String agendaDay = r.printAgenda(4, 15);

        assertTrue(agendaMonth.contains("Team Meeting"));
        assertTrue(agendaDay.contains("Team Meeting"));
    }

    @Test
    public void testIsBusy() throws TimeConflictException {
        Room r = new Room("D404");
        Meeting m = new Meeting(5, 20, 10, 12, new ArrayList<>(), r, "Status Meeting");
        r.addMeeting(m);

        assertTrue(r.isBusy(5, 20, 10, 11)); // overlaps start
        assertTrue(r.isBusy(5, 20, 11, 12)); // overlaps end
    }
}
