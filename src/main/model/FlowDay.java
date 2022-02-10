package model;

import java.time.LocalDate;
import java.util.ArrayList;

// Represents a day on the menstrual cycle that has recorded flow, mood, and symptoms
public class FlowDay {
    private String date;
    private ArrayList<String> moods;                     // select from: happy, sad, moody, angry, unmotivated
    private String flow;                        // flow measured by spotting, light, medium, heavy
    private ArrayList<String> symptoms;                 // select from cramps, fatigue, food cravings, headaches, no symptoms

   // Adds mood, flow, and symptom(s) to a day of the menstruation period
    // OR Constructs a day with a date, no flow entered, no moods entered, and no symptoms entered
    //public FlowDay(String flowDate, String flowLog, ArrayList<String> moodLog, ArrayList<String> symptomsLog) {
        // stub for constructor;
    public FlowDay(String flowDate) {
        this.date = flowDate;
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

    // EFFECTS: returns a string representation the day logged
    public String toString() {
        if (flow == null) {
            if (moods.isEmpty()) {
                if (symptoms.isEmpty()) {
                    return "On " + date;
                } else {
                    return "On " + date + ", You experienced: " + symptoms + ".";
                }
            } else {
                if (symptoms.isEmpty()) {
                    return "On " + date + ", You were feeling: " + moods + ".";
                } else {
                    return "On " + date + ", You were feeling: " + moods + ". You experienced: " + symptoms + ".";
                }
            }
        } else {
            if (moods.isEmpty()) {
                if (symptoms.isEmpty()) {
                    return "On " + date + ", My flow was: " + flow + ".";
                } else {
                    return "On" + date + ", My flow was: " + flow + ". You experienced: " + symptoms + ".";
                }
            } else {
                if (symptoms.isEmpty()) {
                    return "On " + date + ", My flow was: " + flow + ". You were feeling: " + moods + ".";
                } else {
                    return "On " + date + ", My flow was: " + flow + ". You were feeling: " + moods
                            + "You experienced: " + symptoms + ".";
                }
            }
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

    public String getDate() {
        return date;
    }
}
