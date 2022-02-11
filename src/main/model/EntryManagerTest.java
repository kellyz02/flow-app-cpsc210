package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.HashMap;
import java.util.Map;

public class EntryManagerTest {
    private EntryManager testEntryManager = new EntryManager();

    @Test
    void testAddFlowMonth() {
        FlowMonth testFlowMonth1 = new FlowMonth("02/2002");
        FlowDay testFlowDay1 = new FlowDay("2");
        testFlowMonth1.addFlowDay(testFlowDay1);
        testEntryManager.addFlowMonth(testFlowMonth1.getMonthName(), testFlowMonth1);
        assertEquals(1, testEntryManager.getMapSize());

        FlowMonth testFlowMonth2 = new FlowMonth("02/2022");
        testEntryManager.addFlowMonth(testFlowMonth1.getMonthName(), testFlowMonth2);
        assertEquals(1, testEntryManager.getMapSize());

    }
}