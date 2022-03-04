package model;

import static org.junit.jupiter.api.Assertions.*;

import model.FlowDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FlowDayTest {
    private FlowDay testFlowDay;

    @BeforeEach
    void runBefore() {
        testFlowDay = new FlowDay("14");
    }

    @Test
    void testConstructor() {
        assertEquals("14", testFlowDay.getDayName());
        assertEquals("", testFlowDay.getFlow());
        String mood = testFlowDay.getMood();
        assertEquals("", mood);
        String symptom = testFlowDay.getSymptom();
        assertEquals("", symptom);
    }

    @Test
    void testEnterFlow() {
        testFlowDay.enterFlow("Heavy");
        assertEquals("Heavy", testFlowDay.getFlow());
    }

    @Test
    void testEnterMood() {
        testFlowDay.enterMood("Happy");
        String mood = testFlowDay.getMood();
        assertEquals("Happy", mood);

        testFlowDay.enterMood("Melancholy");
        String mood1 = testFlowDay.getMood();
        assertEquals("Melancholy", mood1);
    }

    @Test
    void testEnterSymptom() {
        testFlowDay.enterSymptom("Cramps");
        String symptom = testFlowDay.getSymptom();
        assertEquals("Cramps", symptom);

        testFlowDay.enterSymptom("Headaches");
        String symptom1 = testFlowDay.getSymptom();
        assertEquals("Headaches", symptom1);
    }
}