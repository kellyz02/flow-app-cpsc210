package model;

import java.util.HashMap;
import java.util.Map;

public class EntryManager {
    private Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();

    public void addFlowMonth(String monthName, FlowMonth monthObject) {
        flowMonthYearMap.putIfAbsent(monthName, monthObject);
    }

    public void getFlowMonth(String monthName) { // changed it to private void
        flowMonthYearMap.get(monthName);
    }
}


/*
public class EntryManager {
    private HashMap<String, FlowMonth> flowMonthYearMap;

    public EntryManager() {
        Map<String, FlowMonth> this.flowMonthYearMap = new HashMap<>();
    }

    public void addFlowMonth(String monthName, FlowMonth monthObject) {
        if (flowMonthYearMap.get(monthName) == null) {
            flowMonthYearMap.put(monthName, monthObject);
        }
    }

    public void getFlowMonth(String monthName) {
        flowMonthYearMap.get(monthName);
    }
}

 */

/*


public class EntryManager {
    private Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();

    public EntryManager() {
        Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();
    }

    public void addFlowMonth(String monthName, FlowMonth monthObject) {
        if (flowMonthYearMap.get(monthName) == null) {
            flowMonthYearMap.put(monthName, monthObject);
        }
    }

    public void getFlowMonth(String monthName) {
        flowMonthYearMap.get(monthName);
    }


}
*/