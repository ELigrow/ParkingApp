package edu.wctc.eligrow.test;

import edu.wctc.eligrow.FileInput;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileInputTest extends TestCase {

    FileInput file;
    FileInput badFile;

    @Before
    public void setUp() throws Exception {
        file = new FileInput("tickets.csv");
        badFile = new FileInput("asdf.txt");
    }

    @After
    public void tearDown() throws Exception {
        file.fileClose();
        badFile.fileClose();
    }

    @Test
    public void testFileReadLine() {
        assertNotNull("This will return a value", file.fileReadLine());
        assertNull("This will return null", badFile.fileReadLine());
    }
}