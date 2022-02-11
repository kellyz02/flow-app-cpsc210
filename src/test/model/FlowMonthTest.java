
package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FlowMonthTest {
    private FlowMonth testFlowMonth;

    @BeforeEach
    void runBefore() {
        testFlowMonth = new FlowMonth("02/2022");
    }

    @Test
    void testConstructor() {
        assertEquals("02/2022", testFlowMonth.getMonthName());
        ArrayList<FlowDay> flowDays = testFlowMonth.getFlowDays();
        assertTrue(flowDays.isEmpty());
    }

    @Test
    void testAddFlowDay() {
        FlowDay testFlowDay1 = new FlowDay("2");
        testFlowMonth.addFlowDay(testFlowDay1);
        ArrayList<FlowDay> flowDays = testFlowMonth.getFlowDays();
        assertEquals(1, flowDays.size());

        FlowDay testFlowDay2 = new FlowDay("3");
        testFlowMonth.addFlowDay(testFlowDay2);
        assertEquals(2, flowDays.size());

        FlowDay testFlowDay3 = new FlowDay("2");
        testFlowMonth.addFlowDay(testFlowDay3);
        assertEquals(2, flowDays.size());
    }

    @Test
    void testDeleteFlowDay() {
        FlowDay testFlowDay1 = new FlowDay("2");
        testFlowMonth.addFlowDay(testFlowDay1);
        ArrayList<FlowDay> flowDays = testFlowMonth.getFlowDays();
        assertEquals(1, flowDays.size());

        FlowDay testFlowDay2 = new FlowDay("3");
        testFlowMonth.deleteFlowDay(testFlowDay2);
        assertEquals(1, flowDays.size());

        testFlowMonth.deleteFlowDay(testFlowDay1);
        assertEquals(0, flowDays.size());
    }

    @Test
    void testFindFlowDay() {
        assertEquals(null, testFlowMonth.findFlowDay("2"));

        FlowDay testFlowDay1 = new FlowDay("2");
        testFlowMonth.addFlowDay(testFlowDay1);
        assertEquals(testFlowDay1, testFlowMonth.findFlowDay("2"));
    }


}
