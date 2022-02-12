package model;

import java.util.ArrayList;

// Represents a day on the menstrual cycle that has assigned date, flow, moods, and symptoms
public class FlowDay {
    private String dayName;                    // string identifier for date
    private ArrayList<String> moods;           // select from: happy, sad, moody, angry, unmotivated
    private String flow;                       // flow measured by spotting, light, medium, heavy
    private ArrayList<String> symptoms;        // select from cramps, fatigue, food cravings, headaches, no symptoms

    // EFFECTS: constructs a FlowDay with a given date, empty moods list, flow field, and empty symptoms list
    public FlowDay(String day) {
        this.dayName = day;
        this.moods = new ArrayList<>();
        this.flow = "";
        this.symptoms = new ArrayList<>();
    }

    //EFFECTS: enters a string into the flow field
    public void enterFlow(String flow) {
        this.flow = flow;
    }

    // EFFECTS: enters a string into the moods list
    public void enterMood(String mood) {
        if (!moods.contains(mood)) {
            moods.add(mood);
        }
    }

    // EFFECTS: enters a symptom into the symptom list
    public void enterSymptom(String symptom) {
        if (!symptoms.contains(symptom)) {
            symptoms.add(symptom);
        }
    }

    public ArrayList<String> getMoods() {
        return moods;
    }

    public String getFlow() {
        return flow;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public String getDayName() {
        return dayName;
    }
}
