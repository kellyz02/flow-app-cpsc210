package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents a day on the menstrual cycle that has assigned date, flow, moods, and symptoms
public class FlowDay implements Writable {
    private String dayName;                    // string identifier for date
    private String mood;           // select from: happy, sad, moody, angry, unmotivated
    private String flow;                       // flow measured by spotting, light, medium, heavy
    private String symptom;        // select from cramps, fatigue, food cravings, headaches, no symptoms

    // EFFECTS: constructs a FlowDay with a given date, empty moods list, flow field, and empty symptoms list
    public FlowDay(String day) {
        this.dayName = day;
        this.mood = "";
        this.flow = "";
        this.symptom = "";
    }

    //EFFECTS: enters a string into the flow field
    public void enterFlow(String flow) {
        this.flow = flow;
    }

    // EFFECTS: enters a string into the moods list
    public void enterMood(String mood) {
        this.mood = mood;
    }

    // EFFECTS: enters a symptom into the symptom list
    public void enterSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getMood() {
        return mood;
    }

    public String getFlow() {
        return flow;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getDayName() {
        return dayName;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", dayName);
        json.put("mood", mood);
        json.put("flow", flow);
        json.put("symptom", symptom);
        return json;
    }
}
