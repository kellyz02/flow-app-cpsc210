package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a tracker that has a collection of months with tracked days
public class FlowTracker implements Writable {
    private String name;
    private Map<String, FlowMonth> flowMonthYearMap;

    public FlowTracker(String name) {
        this.name = name;
        flowMonthYearMap = new HashMap<>();
    }

    public FlowDay addEntry(String dateName, String monthName) {
        FlowDay newFlowDay = new FlowDay(dateName);
        FlowMonth newFlowMonth = new FlowMonth(monthName);
        flowMonthYearMap.putIfAbsent(monthName, newFlowMonth);
        flowMonthYearMap.get(monthName).addFlowDay(newFlowDay);
        return newFlowDay;
    }

    public void addMonth(FlowMonth month, String monthName) {
        flowMonthYearMap.putIfAbsent(monthName, month);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tracker name", name);
        json.put("months tracked", flowMonthsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray flowMonthsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FlowMonth fm : getMonths()) {
            jsonArray.put(fm.toJson());
        }
        return jsonArray;
    }

    public FlowMonth getMonth(String monthName) {
        return flowMonthYearMap.get(monthName);
    }

    public Collection<FlowMonth> getMonths() {
        return flowMonthYearMap.values();
    }

    public FlowDay getDay(String monthName, String dayName) {
        return flowMonthYearMap.get(monthName).findFlowDay(dayName);
    }

    public ArrayList<FlowDay> getFlowDaysFT(String monthName) {
        return flowMonthYearMap.get(monthName).getFlowDays();
    }

    public Boolean emptyMonth(String monthName) {
        return getFlowDaysFT(monthName).isEmpty();
    }

    public void deleteMonth(String monthName) {
        flowMonthYearMap.remove(monthName);
    }

    public Set<String> getKeys() {
        return flowMonthYearMap.keySet();
    }

    public Boolean containsMonth(String monthName) {
        return flowMonthYearMap.containsKey(monthName);
    }

    public String getName() {
        return this.name;
    }

}
