package edu.sc.csce747.MeetingPlanner;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrganizationTest {

    @Test
    public void testDefaultConstructorAndGetters() {
        Organization org = new Organization();

        ArrayList<Person> employees = org.getEmployees();
        ArrayList<Room> rooms = org.getRooms();

        assertEquals(5, employees.size());
        assertEquals(5, rooms.size());
        assertEquals("Greg Gay", employees.get(0).getName());
        assertEquals("2A01", rooms.get(0).getID());
    }

    @Test
    public void testGetRoomSuccess() throws Exception {
        Organization org = new Organization();
        Room r = org.getRoom("2A03");
        assertEquals("2A03", r.getID());
    }

    @Test(expected = Exception.class)
    public void testGetRoomFailure() throws Exception {
        Organization org = new Organization();
        org.getRoom("InvalidRoom"); // should throw Exception
    }

    @Test
    public void testGetEmployeeSuccess() throws Exception {
        Organization org = new Organization();
        Person p = org.getEmployee("John Rose");
        assertEquals("John Rose", p.getName());
    }

    @Test(expected = Exception.class)
    public void testGetEmployeeFailure() throws Exception {
        Organization org = new Organization();
        org.getEmployee("Nonexistent Employee"); // should throw Exception
    }
}
