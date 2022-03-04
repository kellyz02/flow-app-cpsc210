package persistence;

import model.FlowMonth;
import model.FlowDay;
import model.FlowTracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads the user's cycles from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlowTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlowTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FlowTracker from JSON object and returns it
    private FlowTracker parseFlowTracker(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        FlowTracker ft = new FlowTracker(name);
        addMonths(ft, jsonObject);
        return ft;
    }

    // MODIFIES: m
    // EFFECTS: parses days from JSON object and adds them to flow tracker
    private void addDays(FlowMonth m, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("logged days");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(m, nextDay);
        }
    }

    // MODIFIES: m
    // EFFECTS: parses day from JSON object and adds it to tracker
    private void addDay(FlowMonth m, JSONObject jsonObject) {
        String name = jsonObject.getString("day");
        String flow = jsonObject.getString("flow");
        String mood = jsonObject.getString("mood");
        String symptom = jsonObject.getString("symptom");
        FlowDay flowDay = new FlowDay(name);
        flowDay.enterFlow(flow);
        flowDay.enterMood(mood);
        flowDay.enterSymptom(symptom);
        m.addFlowDay(flowDay);
    }


    // MODIFIES: tr
    // EFFECTS: parses months from JSON object and adds it to the tracker
    private void addMonths(FlowTracker tr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("months tracked");
        for (Object json : jsonArray) {
            JSONObject nextMonth = (JSONObject) json;
            addMonth(tr, nextMonth);
        }
    }

    // MODIFIES: tr
    // EFFECTS: parses month from JSON object and adds it to the tracker
    private void addMonth(FlowTracker tr, JSONObject jsonObject) {
        String name = jsonObject.getString("month");
        FlowMonth month = new FlowMonth(name);
        tr.addMonth(month, month.getMonthName());
        addDays(month, jsonObject);
    }

}



