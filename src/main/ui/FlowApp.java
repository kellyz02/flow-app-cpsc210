package ui;

import model.FlowDay;
import model.FlowMonth;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

// Menstrual cycle tracker application
public class FlowApp {
    private Scanner input;
    private Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();

    // EFFECTS: runs the "FlowApp" application
    public FlowApp() {
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
        } else {
            System.out.println("selection not valid!");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command when choosing whether to delete or view a Flow Day
    private void processViewDeleteCommand(String command, String monthName, String dayName) {
        if (command.equals("v")) {
            printAttributes1(flowMonthYearMap.get(monthName).findFlowDay(dayName));
        } else if (command.equals("d")) {
            flowMonthYearMap.get(monthName).deleteFlowDay(flowMonthYearMap.get(monthName).findFlowDay(dayName));
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
        } else if (command.equals("m")) {
            newFlowDay.enterMood("angry >:(");
        } else if (command.equals("u")) {
            newFlowDay.enterMood("unmotivated");
        } else {
            System.out.println("selection not valid! please choose one of the options listed above. \n");
            String feeling = input.next();
            processFeelingCommand(feeling, newFlowDay);
        }
    }

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
        System.out.println("\tq -> quit FlowApp");
    }

    private void displayViewDeleteMenu() {
        System.out.println("would you like to view or delete the selected flow day?");
        System.out.println("\tv -> view");
        System.out.println("\td -> delete");
    }

    // MODIFIES: this
    // EFFECTS: ..
    private void displayFeelingsMenu() {
        System.out.println("how are you feeling today?");
        System.out.println("\th -> happy!");
        System.out.println("\ts -> sad :(");
        System.out.println("\tm -> moody");
        System.out.println("\ta -> angry >:(");
    }

    // MODIFIES: this
    // EFFECTS: ..
    private void displayFlowMenu() {
        System.out.println("enter the level of flow:");
        System.out.println("\ts -> spotting!");
        System.out.println("\tl -> light");
        System.out.println("\tm -> medium");
        System.out.println("\th -> heavy");
    }

    private void displaySymptomsMenu() {
        System.out.println("enter the symptoms you experienced");
        System.out.println("\tc -> cramps");
        System.out.println("\tf -> fatigue");
        System.out.println("\tfc -> food cravings");
        System.out.println("\th -> headaches");
        System.out.println("\tn -> no symptoms");
    }

    // MODIFIES: this
    // EFFECTS: creates a new day with its attributes or edits pre-existing one
    private void enterFlowDay() {
        System.out.println("please enter the date of the flow day as DD/MM/YYYY");
        String dateName = input.next();
        String dateNamePattern = "\\d\\d/\\d\\d\\/\\d\\d\\d\\d";
        if (Pattern.matches(dateNamePattern, dateName)) {
            System.out.println("please enter the month and year of the flow day as MM/YYYY");
            String monthName = input.next();
            String monthNamePattern = "\\d\\d\\/\\d\\d\\d\\d";
            if (Pattern.matches(monthNamePattern, monthName)) {
                FlowDay newFlowDay = new FlowDay(dateName);
                FlowMonth newFlowMonth = new FlowMonth(monthName);
                flowMonthYearMap.putIfAbsent(monthName, newFlowMonth);
                flowMonthYearMap.get(monthName).addFlowDay(newFlowDay);
                addAttributes(newFlowDay);
                printAttributes1(newFlowDay);
            }
        } else {
            System.out.println("date is not properly formatted. please try again :)");
            enterFlowDay();
        }
    }

    private void addAttributes(FlowDay newFlowDay) {
        displayFeelingsMenu();
        String feeling = input.next();
        processFeelingCommand(feeling, newFlowDay);
        displayFlowMenu();
        String flow = input.next();
        processFlowCommand(flow, newFlowDay);
        displaySymptomsMenu();
        String symptom = input.next();
        processSymptomsCommand(symptom, newFlowDay);
    }

    private void viewPreviousFlowDays() {
        System.out.println("your previously logged months:");
        for (String monthYear : flowMonthYearMap.keySet()) {
            System.out.println(monthYear);
        }
        System.out.println("please enter the month and year you would like to view/delete as MM/YYYY");
        String monthName = input.next();
        String monthNamePattern = "\\d\\d\\/\\d\\d\\d\\d";
        if (Pattern.matches(monthNamePattern, monthName)) {
            for (FlowDay flowDay : flowMonthYearMap.get(monthName).getFlowDays()) {
                System.out.println(flowDay.getDayName());
            } // might have to check if it exists 1st
            System.out.println("please enter the flow day you would like to view/delete in detail as DD/MM/YYYY");
            String dayName = input.next();
            String dayNamePattern = "\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d";
            if (Pattern.matches(dayNamePattern, dayName)) {
                displayViewDeleteMenu();
                String viewDelete = input.next();
                processViewDeleteCommand(viewDelete, monthName, dayName);
            }
        } else {
            System.out.println("date is not properly formatted. please try again :)");
            viewPreviousFlowDays();
        }
    }

    private void printAttributes1(FlowDay oldDay) {
        String listMood = String.join(", ", oldDay.getMoods());
        String listSymptom = String.join(", ", oldDay.getSymptoms());
        if (oldDay.getMoods().isEmpty()) {
            if (oldDay.getSymptoms().isEmpty()) {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow());
            } else {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow()
                        + ". You experienced " + listSymptom + ".");
            }
        } else {
            if (oldDay.getSymptoms().isEmpty()) {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow()
                        + ". You were feeling " + listMood + ".");
            } else {
                System.out.println("On " + oldDay.getDayName() + ", your flow was " + oldDay.getFlow()
                        + ". You were feeling " + listMood + ". You experienced " + listSymptom + ".");
            }
        }
    }
}