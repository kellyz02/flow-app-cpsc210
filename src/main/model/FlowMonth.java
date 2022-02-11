package model;

import java.util.ArrayList;


public class FlowMonth {
    private String monthName;
    private ArrayList<FlowDay> flowDays;

    // EFFECTS: constructor.
    public FlowMonth(String month) {
        this.monthName = month;
        this.flowDays = new ArrayList<>();
    }

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

    public void deleteFlowDay(FlowDay day) {
        for (int i = 0; i < flowDays.size(); i++) {
            FlowDay fd = flowDays.get(i);
            if (fd.getDayName().equals(day.getDayName())) {
                flowDays.remove(day);
            }
        }
    }

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

}


