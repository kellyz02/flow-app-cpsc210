package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FlowTrackerTest {
    private FlowTracker testTracker;

    @BeforeEach
    void runBefore() {
        testTracker = new FlowTracker("test");
    }

    @Test
    void testConstructor() {
        assertEquals("test", testTracker.getName());
        Collection<FlowMonth> flowMonths = testTracker.getMonths();
        assertTrue(flowMonths.isEmpty());
    }

    @Test
    void testAddEntry() {
        assertEquals(0, testTracker.getMonths().size());
        testTracker.addEntry("03/03/2022", "03/2022");
        testTracker.addEntry("04/03/2022", "03/2022");
        testTracker.addEntry("04/04/2022", "04/2022");
        assertEquals(2, testTracker.getMonths().size());
        assertEquals(2, testTracker.getFlowDaysFT("03/2022").size());
        assertEquals(1, testTracker.getFlowDaysFT("04/2022").size());
    }

    @Test
    void testAddMonth() {
        FlowMonth m1 = new FlowMonth("03/2022");
        FlowMonth m2 = new FlowMonth("04/2022");
        assertEquals(0, testTracker.getMonths().size());
        testTracker.addMonth(m1, "03/2022");
        testTracker.addMonth(m2, "04/2022");
        assertEquals(2, testTracker.getMonths().size());
    }

    @Test
    void testEmptyMonth() {
        testTracker.addEntry("03/03/2022", "03/2022");
        testTracker.addEntry("04/03/2022", "03/2022");
        testTracker.addEntry("04/04/2022", "04/2022");
        assertFalse(testTracker.emptyMonth("03/2022"));
        assertFalse(testTracker.emptyMonth("04/2022"));
        FlowMonth march = testTracker.getMonth("04/2022");
        FlowDay day1 = testTracker.getDay("04/2022", "04/04/2022");
        march.deleteFlowDay(day1);
        assertTrue(testTracker.emptyMonth("04/2022"));
    }

    @Test
    void testDeleteMonth() {
        testTracker.addEntry("03/03/2022", "03/2022");
        testTracker.addEntry("04/04/2022", "04/2022");
        FlowMonth april = testTracker.getMonth("04/2022");
        FlowDay day1 = testTracker.getDay("04/2022", "04/04/2022");
        FlowMonth march = testTracker.getMonth("03/2022");
        FlowDay day2 = testTracker.getDay("03/2022", "03/03/2022");
        april.deleteFlowDay(day1);
        testTracker.deleteMonth("04/2022");
        assertEquals(1, testTracker.getMonths().size());
        march.deleteFlowDay(day2);
        testTracker.deleteMonth("03/2022");
        assertEquals(0, testTracker.getMonths().size());
    }

    @Test
    void testGetKeys() {
        assertEquals(0, testTracker.getKeys().size());
        testTracker.addEntry("03/03/2022", "03/2022");
        assertEquals(1, testTracker.getKeys().size());
        testTracker.addEntry("04/03/2022", "03/2022");
        assertEquals(1, testTracker.getKeys().size());
        testTracker.addEntry("04/04/2022", "04/2022");
        assertEquals(2, testTracker.getKeys().size());
    }

    @Test
    void testContainsMonth() {
        testTracker.addEntry("03/03/2022", "03/2022");
        testTracker.addEntry("04/03/2022", "03/2022");
        testTracker.addEntry("04/04/2022", "04/2022");
        assertTrue(testTracker.containsMonth("03/2022"));
        assertTrue(testTracker.containsMonth("04/2022"));
        assertFalse(testTracker.containsMonth("05/2022"));
    }

}
