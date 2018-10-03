package edu.wctc.eligrow.test;

import edu.wctc.eligrow.Ticket;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketTest extends TestCase {

    Ticket lostLicket = new Ticket(true);
    Ticket checkIn = new Ticket(7,23,15);

    @Test
    public void testWrite() {
        assertEquals("true,25", lostLicket.Write());
        assertEquals("7,23,15", checkIn.Write());
    }
}