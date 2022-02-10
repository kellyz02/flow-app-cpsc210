package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

class FlowDayTest {
    private FlowDay testFlowDay;

    @BeforeEach
    void runBefore() {
        testFlowDay = new FlowDay("February 9, 2022");
    }

    @Test
    void testConstructor() {
        assertEquals("February 9, 2022", testFlowDay.getDate());
        // assertTrue(flow.isEmpty()); // do i need this case???
        ArrayList<String> moods = testFlowDay.getMoods();
        assertTrue(moods.isEmpty());
        ArrayList<String> symptoms = testFlowDay.getSymptoms();
        assertTrue(symptoms.isEmpty());
    }

    @Test
    void testEnterFlow() {
        testFlowDay.enterFlow("Heavy");
        assertEquals("Heavy", testFlowDay.getFlow());
    }

    @Test
    void testEnterMood() {
        testFlowDay.enterMood("Happy");
        ArrayList<String> moods = testFlowDay.getMoods();
        assertEquals(1, moods.size());

        testFlowDay.enterMood("Melancholy");
        assertEquals(2, moods.size());

        testFlowDay.enterMood("Happy"); // shouldn't be able to repeat
        assertEquals(2, moods.size());
    }

    @Test
    void testEnterSymptom() {
        testFlowDay.enterSymptom("Cramps");
        ArrayList<String> symptoms = testFlowDay.getSymptoms();
        assertEquals(1, symptoms.size());

        testFlowDay.enterSymptom("Headaches");
        assertEquals(2, symptoms.size());

        testFlowDay.enterSymptom("Cramps");
        assertEquals(2, symptoms.size());
    }

    @Test
    void testToString() {
        assertTrue(testFlowDay.toString().contains("On February 9, 2022"));
    }



}