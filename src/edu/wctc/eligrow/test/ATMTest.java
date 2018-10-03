package edu.wctc.eligrow.test;

import edu.wctc.eligrow.ATM;
import edu.wctc.eligrow.Ticket;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class ATMTest extends TestCase {

    ATM atm = new ATM();

    @Test
    public void testReadPrevReport() {
        assertNotNull(atm.ReadPrevReport("tickets.csv"));
    }
}