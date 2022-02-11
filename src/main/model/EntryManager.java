package model;

/*
getentriesformonth -> string
    hashmap -> map string to month

            make a new month or add onto an existing month
 */

import com.sun.tools.javac.comp.Flow;

import java.util.HashMap;
import java.util.Map;


public class EntryManager {
    private Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();

    public static void addFlowMonth(String monthName, FlowMonth monthObject) {
        if (flowMonthYearMap.get(monthName) == null) {
            flowMonthYearMap.put(monthName, monthObject);
        }
    }

    public static void getFlowMonth(String monthName) {
        flowMonthYearMap.get(monthName);
    }


/*

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
    private void addFlowDay(FlowDay monthName, FlowDay dayName) {
        ArrayList<FlowDay> month = flowMonthYearMap.get(monthName);
        month.add(FlowDay);
    }
                // Map<Treatment, ArrayList<String>> treatmentSpecialist = new HashMap
*/


}
