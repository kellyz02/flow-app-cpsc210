package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Represents a tracker that has a collection of months with tracked days
public class FlowTracker {
    private Map<String, FlowMonth> flowMonthYearMap;

    public FlowTracker() {
        flowMonthYearMap = new HashMap<>();
    }

    public FlowDay addEntry(String dateName, String monthName) {
        FlowDay newFlowDay = new FlowDay(dateName);
        FlowMonth newFlowMonth = new FlowMonth(monthName);
        flowMonthYearMap.putIfAbsent(monthName, newFlowMonth);
        flowMonthYearMap.get(monthName).addFlowDay(newFlowDay);
        return newFlowDay;
    }

    public FlowMonth getMonth(String monthName) {
        return flowMonthYearMap.get(monthName);
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

}
