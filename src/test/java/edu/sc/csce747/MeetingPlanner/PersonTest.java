package edu.sc.csce747.MeetingPlanner;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void testDefaultConstructor() {
        Person p = new Person();
        assertEquals("", p.getName());
    }

    @Test
    public void testConstructorWithName() {
        Person p = new Person("Alice");
        assertEquals("Alice", p.getName());
    }

    @Test
    public void testAddAndRemoveMeeting() throws TimeConflictException {
        Person p = new Person("Bob");
        Meeting m1 = new Meeting(3, 10, 10, 12, new ArrayList<>(), new Room("A101"), "Morning");
        Meeting m2 = new Meeting(3, 10, 12, 14, new ArrayList<>(), new Room("A101"), "Afternoon");

        // Add meetings
        p.addMeeting(m1);
        p.addMeeting(m2);

        assertEquals("Morning", p.getMeeting(3, 10, 0).getDescription());
        assertEquals("Afternoon", p.getMeeting(3, 10, 1).getDescription());

        // Remove meeting
        p.removeMeeting(3, 10, 0);
        assertEquals("Afternoon", p.getMeeting(3, 10, 0).getDescription());
    }

    @Test
    public void testPrintAgenda() throws TimeConflictException {
        Person p = new Person("Charlie");
        Meeting m = new Meeting(4, 15, 9, 11, new ArrayList<>(), new Room("B202"), "Team Meeting");
        p.addMeeting(m);

        String agendaMonth = p.printAgenda(4);
        String agendaDay = p.printAgenda(4, 15);

        assertTrue(agendaMonth.contains("Team Meeting"));
        assertTrue(agendaDay.contains("Team Meeting"));
    }

    @Test
    public void testIsBusy() throws TimeConflictException {
        Person p = new Person("Diana");
        Meeting m = new Meeting(5, 20, 10, 12, new ArrayList<>(), new Room("C303"), "Status Meeting");
        p.addMeeting(m);

        assertTrue(p.isBusy(5, 20, 10, 11)); // overlaps start
        assertTrue(p.isBusy(5, 20, 11, 12)); // overlaps end
    }
}
