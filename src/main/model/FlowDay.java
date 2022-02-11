package model;

import java.util.ArrayList;

// Represents a day on the menstrual cycle that has assigned date, flow, moods, and symptoms
public class FlowDay {
    private String dayName;                    // string identifier for date
    private ArrayList<String> moods;           // select from: happy, sad, moody, angry, unmotivated
    private String flow;                       // flow measured by spotting, light, medium, heavy
    private ArrayList<String> symptoms;        // select from cramps, fatigue, food cravings, headaches, no symptoms

   // Adds mood, flow, and symptom(s) to a day of the menstruation period
    // OR Constructs a day with a date, no flow entered, no moods entered, and no symptoms entered
    //public FlowDay(String flowDate, String flowLog, ArrayList<String> moodLog, ArrayList<String> symptomsLog) {
        // stub for constructor;
    public FlowDay(String day) {
        this.dayName = day;
        this.moods = new ArrayList<>();
        this.flow = "";
        this.symptoms = new ArrayList<>();
    }

    public void enterFlow(String flow) {
        this.flow = flow;
    }

    public void enterMood(String mood) {
        if (!moods.contains(mood)) {
            moods.add(mood);
        }
    }

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
