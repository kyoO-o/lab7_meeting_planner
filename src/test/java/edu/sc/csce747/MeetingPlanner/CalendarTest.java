package edu.sc.csce747.MeetingPlanner;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CalendarTest {
	// Add test methods here. 
	// You are not required to write tests for all classes.

    @Test
    public void testAddMeeting_ValidInput_Success() throws TimeConflictException {
        Calendar cal = new Calendar();
        Meeting m = new Meeting(3, 10, 10, 12, new ArrayList<>(), new Room("A101"), "Team Meeting");

        cal.addMeeting(m);
        assertEquals("Team Meeting", cal.getMeeting(3, 10, 0).getDescription());
    }

    @Test
    public void testIsBusy_NoMeeting_ReturnsFalse() throws TimeConflictException {
        Calendar cal = new Calendar();
        boolean result = cal.isBusy(3, 10, 10, 12);
        assertFalse(result);
    }

    @Test
    public void testIsBusy_HasMeeting_ReturnsTrue() throws TimeConflictException {
        Calendar cal = new Calendar();
        Meeting m = new Meeting(3, 10, 10, 12, new ArrayList<>(), new Room("A101"), "Team Meeting");
        cal.addMeeting(m);
        assertTrue(cal.isBusy(3, 10, 10, 11));
    }

    @Test
    public void testAddMeeting_InvalidMonth_ThrowsException() {
        Calendar cal = new Calendar();
        Meeting m = new Meeting(13, 10, 10, 12, new ArrayList<>(), new Room("A101"), "Invalid");
        assertThrows(TimeConflictException.class, () -> cal.addMeeting(m));
    }

    @Test
    public void testAddMeeting_InvalidDay_ThrowsException() {
        Calendar cal = new Calendar();
        Meeting m = new Meeting(2, 35, 10, 12, new ArrayList<>(), new Room("A101"), "Invalid Day");
        assertThrows(TimeConflictException.class, () -> cal.addMeeting(m));
    }

    @Test
    public void testAddMeeting_InvalidTime_ThrowsException() {
        Calendar cal = new Calendar();
        Meeting m = new Meeting(3, 10, 15, 10, new ArrayList<>(), new Room("A101"), "Backwards Time");
        assertThrows(TimeConflictException.class, () -> cal.addMeeting(m));
    }

    @Test
    public void testAddMeeting_OverlappingMeeting_ThrowsException() throws TimeConflictException {
        Calendar cal = new Calendar();
        Meeting m1 = new Meeting(3, 10, 10, 12, new ArrayList<>(), new Room("A101"), "First");
        Meeting m2 = new Meeting(3, 10, 11, 13, new ArrayList<>(), new Room("A101"), "Overlap");

        cal.addMeeting(m1);
        assertThrows(TimeConflictException.class, () -> cal.addMeeting(m2));
    }

    @Test
    public void testPrintAgenda_HasMeeting_PrintsCorrectly() throws TimeConflictException {
        Calendar cal = new Calendar();
        Meeting m = new Meeting(3, 10, 10, 12, new ArrayList<>(), new Room("A101"), "Review");
        cal.addMeeting(m);
        String result = cal.printAgenda(3, 10);
        assertTrue(result.contains("Review"));
    }
}
