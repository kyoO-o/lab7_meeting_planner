package edu.sc.csce747.MeetingPlanner;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MeetingTest {

    @Test
    public void testDefaultConstructor() {
        Meeting m = new Meeting();
        assertNotNull(m);
    }

    @Test
    public void testMonthDayConstructor() {
        Meeting m = new Meeting(3, 15);
        assertEquals(3, m.getMonth());
        assertEquals(15, m.getDay());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
    }

    @Test
    public void testMonthDayDescriptionConstructor() {
        Meeting m = new Meeting(4, 20, "Vacation");
        assertEquals(4, m.getMonth());
        assertEquals(20, m.getDay());
        assertEquals("Vacation", m.getDescription());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
    }

    @Test
    public void testMonthDayStartEndConstructor() {
        Meeting m = new Meeting(5, 10, 9, 11);
        assertEquals(5, m.getMonth());
        assertEquals(10, m.getDay());
        assertEquals(9, m.getStartTime());
        assertEquals(11, m.getEndTime());
    }

    @Test
    public void testFullConstructor() {
        Room r = new Room("A101");
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("Alice"));
        Meeting m = new Meeting(6, 12, 10, 12, attendees, r, "Team Meeting");

        assertEquals(6, m.getMonth());
        assertEquals(12, m.getDay());
        assertEquals(10, m.getStartTime());
        assertEquals(12, m.getEndTime());
        assertEquals(r, m.getRoom());
        assertEquals("Team Meeting", m.getDescription());
        assertEquals(1, m.getAttendees().size());
        assertEquals("Alice", m.getAttendees().get(0).getName());
    }

    @Test
    public void testGettersAndSetters() {
        Meeting m = new Meeting();
        Room r = new Room("B202");
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("Bob"));

        m.setMonth(7);
        m.setDay(21);
        m.setStartTime(8);
        m.setEndTime(10);
        m.setRoom(r);
        m.setDescription("Planning");

        assertEquals(7, m.getMonth());
        assertEquals(21, m.getDay());
        assertEquals(8, m.getStartTime());
        assertEquals(10, m.getEndTime());
        assertEquals(r, m.getRoom());
        assertEquals("Planning", m.getDescription());
    }

    @Test
    public void testAddAndRemoveAttendee() {
        Meeting m = new Meeting();
        Person p1 = new Person("Charlie");
        Person p2 = new Person("Diana");

        m.addAttendee(p1);
        m.addAttendee(p2);

        assertEquals(2, m.getAttendees().size());

        m.removeAttendee(p1);
        assertEquals(1, m.getAttendees().size());
        assertEquals("Diana", m.getAttendees().getFirst().getName());
    }

    @Test
    public void testOverlaps() {
        Meeting m1 = new Meeting(3, 10, 10, 12);
        Meeting m2 = new Meeting(3, 10, 11, 13);
        Meeting m3 = new Meeting(3, 10, 13, 14);
        Meeting m4 = new Meeting(4, 10, 11, 13); // Different month

        assertTrue(m1.overlaps(m2));
        assertFalse(m1.overlaps(m3));
        assertFalse(m1.overlaps(m4));
    }

    @Test
    public void testToString() {
        Room r = new Room("C303");
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("Eve"));
        attendees.add(new Person("Frank"));

        Meeting m = new Meeting(8, 15, 9, 11, attendees, r, "Status Meeting");

        String str = m.toString();
        assertTrue(str.contains("8/15"));
        assertTrue(str.contains("9 - 11"));
        assertTrue(str.contains("C303"));
        assertTrue(str.contains("Status Meeting"));
        assertTrue(str.contains("Eve"));
        assertTrue(str.contains("Frank"));
    }
}
