package model;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EntryManagerTest {
    private Map<String, FlowMonth> testFlowMonthYearMap = new HashMap<>();

    @Test
    void testAddFlowMonth() {
        FlowMonth testFlowMonth1 = new FlowMonth("02/2002");
        FlowDay testFlowDay1 = new FlowDay("2");
        testFlowMonth1.addFlowDay(testFlowDay1);
        EntryManager.addFlowMonth(testFlowMonth1.getMonthName(), testFlowMonth1);
        assertEquals(1, testFlowMonthYearMap.size());
        assertEquals(1, getFlowMap

        FlowMonth testFlowMonth2 = new FlowMonth("02/2002");
        EntryManager.addFlowMonth(testFlowMonth2.getMonthName(), testFlowMonth2);
        assertEquals(1, testFlowMonthYearMap.size());

    }

}
