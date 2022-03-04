package persistence;

import model.FlowDay;
import model.FlowMonth;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDay(String name, String symptom, String mood, String flow, FlowDay flowDay) {
        assertEquals(name, flowDay.getDayName());
        assertEquals(symptom, flowDay.getSymptom());
        assertEquals(mood, flowDay.getMood());
        assertEquals(flow, flowDay.getFlow());
    }

    protected void checkMonth(String name, List<FlowDay> flowDays, FlowMonth flowMonth) {
        assertEquals(name, flowMonth.getMonthName());
        assertEquals(flowDays, flowMonth.getFlowDays());
    }
}
