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
            if (fd.getDay().equals(day.getDay())) {
                flowDays.set(i, day);
                return;
            }
        }
        flowDays.add(day);
    }

    public void deleteFlowDay(FlowDay day) {
        for (int i = 0; i < flowDays.size(); i++) {
            FlowDay fd = flowDays.get(i);
            if (fd.getDay().equals(day.getDay())) {
                flowDays.remove(day);
            }
        }
    }

    public String getMonthName() {
        return this.monthName;
    }

    public ArrayList<FlowDay> getFlowDays() {
        return this.flowDays;
    }
}


