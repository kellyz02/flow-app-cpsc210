package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Represents a month that contains a list of Flow Days
public class FlowMonth {
    private String monthName;
    private ArrayList<FlowDay> flowDays;
    private Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();

    // EFFECTS: constructs a month with an empty list of Flow Days
    public FlowMonth(String month) {
        this.monthName = month;
        this.flowDays = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if the date doesn't already exist, it adds the given Flow Day to the list.
    // If the date does already exist, it replaces the new Flow Day for that date.
    public void addFlowDay(FlowDay day) {
        for (int i = 0; i < flowDays.size(); i++) {
            FlowDay fd = flowDays.get(i);
            if (fd.getDayName().equals(day.getDayName())) {
                flowDays.set(i, day);
                return;
            }
        }
        flowDays.add(day);
    }

    // MODIFIES: this
    // EFFECTS: deletes given Flow Day from the list
    public void deleteFlowDay(FlowDay day) {
        for (int i = 0; i < flowDays.size(); i++) {
            FlowDay fd = flowDays.get(i);
            if (fd.getDayName().equals(day.getDayName())) {
                flowDays.remove(day);
            }
        }
    }

    // EFFECTS: returns the Flow Day whose dayName matches the given String. Otherwise, returns null.
    public FlowDay findFlowDay(String dayName) {
        for (FlowDay fd : flowDays) {
            if (fd.getDayName().equals(dayName)) {
                return fd;
            }
        }
        return null;
    }

    public String getMonthName() {
        return this.monthName;
    }

    public ArrayList<FlowDay> getFlowDays() {
        return this.flowDays;
    }

    public void deleteFromMap(String dayName) {
        flowMonthYearMap.get(monthName).deleteFlowDay(flowMonthYearMap.get(monthName).findFlowDay(dayName));
        if (flowMonthYearMap.get(monthName).getFlowDays().isEmpty()) {
            flowMonthYearMap.remove(monthName);
            System.out.println("this flow day has been successfully deleted");
        }
    }
}


