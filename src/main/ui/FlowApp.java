package ui;

import model.FlowDay;
import model.FlowMonth;
import model.FlowTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

// Menstrual cycle tracker application
public class FlowApp {
    private static final String JSON_STORE = "./data/flowTracker.json";
    private Scanner input;
    private FlowTracker flowTracker;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the "FlowApp" application
    public FlowApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        flowTracker = new FlowTracker("My flow tracker");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFlowApp();
    }

    //MODIFIES: this
    // EFFECTS: processes user input
    private void runFlowApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nhave a wonderful day!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the app
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("e")) {
            enterFlowDay();
        } else if (command.equals("v")) {
            viewPreviousFlowDays();
        } else if (command.equals("s")) {
            saveFlowTracker();
        } else if (command.equals("l")) {
            loadFlowTracker();
        } else {
            System.out.println("selection not valid!");
        }
    }


    private void processInputCommand(String command, FlowDay newFlowDay) {
        if (command.equals("f")) {
            displayFlowMenu(newFlowDay);
        } else if (command.equals("s")) {
            displaySymptomsMenu(newFlowDay);
        } else if (command.equals("m")) {
            displayFeelingsMenu(newFlowDay);
        } else if (command.equals("d")) {
            printAttributes1(newFlowDay);
        } else {
            System.out.println("selection not valid! please choose one of the options listed above. \n");
            helpAttributes(newFlowDay);
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command when choosing whether to delete or view a Flow Day
    private void processViewDeleteCommand(String command, String monthName, String dayName) {
        if (command.equals("v")) {
            printAttributes1(flowTracker.getDay(monthName, dayName));
        } else if (command.equals("d")) {
            FlowMonth m = flowTracker.getMonth(monthName);
            FlowDay d = flowTracker.getDay(monthName, dayName);
            m.deleteFlowDay(d);
            if (flowTracker.emptyMonth(monthName)) {
                flowTracker.deleteMonth(monthName);
                System.out.println("this flow day has been successfully deleted");
            }
        } else {
            System.out.println("selection not valid! \n");
            String vdCommand = input.next();
            processViewDeleteCommand(vdCommand, monthName, dayName);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command when selecting feelings
    private void processFeelingCommand(String command, FlowDay newFlowDay) {
        if (command.equals("h")) {
            newFlowDay.enterMood("happy :)");
        } else if (command.equals("s")) {
            newFlowDay.enterMood("sad :(");
        } else if (command.equals("a")) {
            newFlowDay.enterMood("angry >:(");
        } else if (command.equals("u")) {
            newFlowDay.enterMood("unmotivated");
        } else {
            System.out.println("selection not valid! please choose one of the options listed above. \n");
            String feeling = input.next();
            processFeelingCommand(feeling, newFlowDay);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command when selecting flow level
    private void processFlowCommand(String command, FlowDay newFlowDay) {
        if (command.equals("s")) {
            newFlowDay.enterFlow("spotting");
        } else if (command.equals("l")) {
            newFlowDay.enterFlow("light");
        } else if (command.equals("m")) {
            newFlowDay.enterFlow("medium");
        } else if (command.equals("h")) {
            newFlowDay.enterFlow("heavy");
        } else {
            System.out.println("selection not valid! please choose one of the options listed above. \n");
            String flow = input.next();
            processFlowCommand(flow, newFlowDay);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command when selecting symptoms
    private void processSymptomsCommand(String command, FlowDay newFlowDay) {
        if (command.equals("c")) {
            newFlowDay.enterSymptom("cramps");
        } else if (command.equals("f")) {
            newFlowDay.enterSymptom("fatigue");
        } else if (command.equals("fc")) {
            newFlowDay.enterSymptom("food cravings");
        } else if (command.equals("h")) {
            newFlowDay.enterSymptom("headaches");
        } else if (command.equals("n")) {
            newFlowDay.enterSymptom("no symptoms");
        } else {
            System.out.println("selection not valid! please choose one of the options listed above. \n");
            String symptom = input.next();
            processSymptomsCommand(symptom, newFlowDay);
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to FlowApp!");
        System.out.println("\te -> enter a new flow day");
        System.out.println("\tv -> view or delete previously logged flow days");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit FlowApp");
    }

    // EFFECTS: displays the menu of options when user is accessing previously logged days
    private void displayViewDeleteMenu() {
        System.out.println("would you like to view or delete the selected flow day?");
        System.out.println("\tv -> view");
        System.out.println("\td -> delete");
    }

    // EFFECTS: displays the menu of attributes the user can add to the new day
    private void displayInputMenu() {
        System.out.println("select the attribute you would like to add to for the flow day:");
        System.out.println("\tf -> enter flow");
        System.out.println("\tm -> enter moods");
        System.out.println("\ts -> enter symptoms");
        System.out.println("\td -> done flow day entry!");
    }

    // EFFECTS: displays menu of feeling options to the user
    private void displayFeelingsMenu(FlowDay newFlowDay) {
        System.out.println("how are you feeling today?");
        System.out.println("\th -> happy!");
        System.out.println("\ts -> sad :(");
        System.out.println("\tu -> unmotivated");
        System.out.println("\ta -> angry >:(");
        String feeling = input.next();
        processFeelingCommand(feeling, newFlowDay);
        displayInputMenu();
        helpAttributes(newFlowDay);
    }

    // EFFECTS: displays menu of flow levels to the user
    private void displayFlowMenu(FlowDay newFlowDay) {
        System.out.println("enter the level of flow:");
        System.out.println("\ts -> spotting!");
        System.out.println("\tl -> light");
        System.out.println("\tm -> medium");
        System.out.println("\th -> heavy");
        String flow = input.next(); // add all these functions into displayFlowMenu
        processFlowCommand(flow, newFlowDay);
        displayInputMenu();
        helpAttributes(newFlowDay);
    }

    // EFFECTS: displays menu of symptoms to the user
    private void displaySymptomsMenu(FlowDay newFlowDay) {
        System.out.println("enter the symptoms you experienced");
        System.out.println("\tc -> cramps");
        System.out.println("\tf -> fatigue");
        System.out.println("\tfc -> food cravings");
        System.out.println("\th -> headaches");
        System.out.println("\tn -> no symptoms");
        String symptom = input.next();
        processSymptomsCommand(symptom, newFlowDay);
        displayInputMenu();
        helpAttributes(newFlowDay);
    }

    // MODIFIES: this
    // EFFECTS: creates a new FlowDay with its attributes or edits pre-existing one
    private void enterFlowDay() {
        System.out.println("please enter the date of the flow day as DD/MM/YYYY");
        String dateName = input.next();
        String dateNamePattern = "\\d\\d/\\d\\d\\/\\d\\d\\d\\d";
        if (Pattern.matches(dateNamePattern, dateName)) {
            System.out.println("please enter the month and year of the flow day as MM/YYYY");
            String monthName = input.next();
            String monthNamePattern = "\\d\\d\\/\\d\\d\\d\\d";
            if (Pattern.matches(monthNamePattern, monthName)) {
                displayInputMenu();
                helpAttributes(flowTracker.addEntry(dateName, monthName));
            } else {
                System.out.println("date is not properly formatted. please try again :)");
                enterFlowDay();
            }
        } else {
            System.out.println("date is not properly formatted. please try again :)");
            enterFlowDay();
        }
    }

    // EFFECTS: helper function
    private void helpAttributes(FlowDay newFlowDay) {
        String choose = input.next();
        processInputCommand(choose, newFlowDay);
    }

    // EFFECTS: allows user to access previously logged days
    private void viewPreviousFlowDays() {
        System.out.println("your previously logged months:");
        if (flowTracker.getKeys().isEmpty()) {
            System.out.println("You have not logged any months yet!");
        } else {
            for (String monthYear : flowTracker.getKeys()) {
                System.out.println(monthYear);
            }
            System.out.println("please enter the month and year you would like to access as MM/YYYY");
            String monthName = input.next();
            String monthNamePattern = "\\d\\d\\/\\d\\d\\d\\d";
            if (Pattern.matches(monthNamePattern, monthName)) {
                monthExists(monthName);
            } else {
                System.out.println("date is not properly formatted. please try again :)");
                viewPreviousFlowDays();
            }
        }
    }

    // EFFECTS: checks to see if the entered month and entered date exists in the hashmap
    private void monthExists(String monthName) {
        if (flowTracker.containsMonth(monthName)) {
            for (FlowDay flowDay : flowTracker.getFlowDaysFT(monthName)) {
                System.out.println("your previously logged days in the chosen month:");
                System.out.println(flowDay.getDayName());
            }
            System.out.println("please enter the flow day you would like to view/delete in detail as DD/MM/YYYY");
            String dayName = input.next();
            String dayNamePattern = "\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d";
            if (Pattern.matches(dayNamePattern, dayName)) {
                displayViewDeleteMenu();
                String viewDelete = input.next();
                processViewDeleteCommand(viewDelete, monthName, dayName);
            }
        } else {
            System.out.println("nothing has been logged for the entered date. please try again :)");
            viewPreviousFlowDays();
        }
    }

    // EFFECTS: returns the attributes for the selected FlowDay
    private void printAttributes1(FlowDay oldDay) {
        String listMood = String.join(" + ", oldDay.getMood());
        String listSymptom = String.join(" + ", oldDay.getSymptom());
        if (oldDay.getMood().isEmpty()) {
            if (oldDay.getSymptom().isEmpty()) {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow());
            } else {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow()
                        + ". You experienced " + listSymptom + ".");
            }
        } else {
            if (oldDay.getSymptom().isEmpty()) {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow()
                        + ". You were feeling " + listMood + ".");
            } else {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow()
                        + ". You were feeling " + listMood + ". You experienced " + listSymptom + ".");
            }
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveFlowTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(flowTracker);
            jsonWriter.close();
            System.out.println("Saved " + flowTracker.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFlowTracker() {
        try {
            flowTracker = jsonReader.read();
            System.out.println("Loaded " + flowTracker.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}