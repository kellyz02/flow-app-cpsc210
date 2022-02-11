package ui;

import model.FlowDay;
import model.FlowMonth;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class FlowApp {
    private Scanner input;
    // private EntryManager entryManager = new EntryManager();
    private Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();


    // EFFECTS: runs the menstrual cycle tracker application
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
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("e")) {
            enterFlowDay();
        } else if (command.equals("v")) {
            viewPreviousFlowDays();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    private void processFeelingCommand(String command, FlowDay newFlowDay) {
        if (command.equals("h")) {
            newFlowDay.enterMood("happy! :)");
        } else if (command.equals("s")) {
            newFlowDay.enterMood("sad :(");
        } else if (command.equals("m")) {
            newFlowDay.enterMood("angry >:(");
        } else if (command.equals("u")) {
            newFlowDay.enterMood("unmotivated");
        } else if (command.equals("n")) {
            newFlowDay.enterMood("");
        } else {
            System.out.println("selection not valid! please choose one of the options listed above.");
        }
    }

    private void processFlowCommand(String command, FlowDay newFlowDay) {
        if (command.equals("s")) {
            newFlowDay.enterFlow("spotting");
        } else if (command.equals("l")) {
            newFlowDay.enterFlow("light flow");
        } else if (command.equals("m")) {
            newFlowDay.enterFlow("medium flow");
        } else if (command.equals("h")) {
            newFlowDay.enterFlow("heavy flow");
        } else if (command.equals("n")) {
            newFlowDay.enterFlow("");
        } else {
            System.out.println("selection not valid! please choose one of the options listed above.");
        }
    }

    private void processSymptomsCommand(String command, FlowDay newFlowDay) {
        if (command.equals("c")) {
            newFlowDay.enterFlow("cramps");
        } else if (command.equals("f")) {
            newFlowDay.enterFlow("fatigue");
        } else if (command.equals("fc")) {
            newFlowDay.enterFlow("food cravings");
        } else if (command.equals("h")) {
            newFlowDay.enterFlow("headaches");
        } else if (command.equals("n")) {
            newFlowDay.enterFlow("");
        } else {
            System.out.println("selection not valid! please choose one of the options listed above.");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes the app
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("/n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to FlowApp!");
        System.out.println("\te -> enter a new flow day");
        System.out.println("\tv -> view previously logged flow days");
    }

    // MODIFIES: this
    // EFFECTS: ..
    private void displayFeelingsMenu() {
        System.out.print("how are you feeling today?");
        System.out.println("\th -> happy!");
        System.out.println("\ts -> sad :(");
        System.out.println("\tm -> moody");
        System.out.println("\ta -> angry >:(");
        System.out.println("\tn -> no feeling entry for today");
    }

    // MODIFIES: this
    // EFFECTS: ..
    private void displayFlowMenu() {
        System.out.print("enter the level of flow:");
        System.out.println("\ts -> spotting!");
        System.out.println("\tl -> light");
        System.out.println("\tm -> medium");
        System.out.println("\th -> heavy");
        System.out.println("\tn -> no flow entry for today");
    }

    private void displaySymptomsMenu() {
        System.out.print("enter the symptoms you experienced");
        System.out.println("\tc -> cramps");
        System.out.println("\tf -> fatigue");
        System.out.println("\tfc -> food cravings");
        System.out.println("\th -> headaches");
        System.out.println("\tn -> no symptoms");
    }

    // MODIFIES: this
    // EFFECTS: ...
    private void enterFlowDay() {
        System.out.print("please enter the date of the flow day as DD/MM/YYYY");
        String dateName = input.nextLine();
        String dateNamePattern = "\\d\\d\\p{/}\\d\\d\\p{/}\\d\\d\\d\\d";
        if (Pattern.matches(dateNamePattern, dateName)) {
            System.out.print("please enter the month and year of the flow day as MM/YYYY");
            String monthName = input.nextLine();
            String monthNamePattern = "\\d\\d\\p{/}\\d\\d\\d\\d";
            if (Pattern.matches(monthNamePattern, monthName)) {
                FlowDay newFlowDay = new FlowDay(dateName);
                FlowMonth newFlowMonth = new FlowMonth(monthName);
                flowMonthYearMap.putIfAbsent(monthName, newFlowMonth);
                flowMonthYearMap.get(monthName).addFlowDay(newFlowDay);
                addAttributes(newFlowDay);
            }
        } else {
            System.out.println("date is not properly formatted. please try again :)");
        }
    }

    private void addAttributes(FlowDay newFlowDay) {
        displayFeelingsMenu();
        String feeling = input.nextLine();
        processFeelingCommand(feeling, newFlowDay);
        displayFlowMenu();
        String flow = input.nextLine();
        processFlowCommand(flow, newFlowDay);
        displaySymptomsMenu();
        String symptom = input.nextLine();
        processSymptomsCommand(symptom, newFlowDay);
    }

    private void viewPreviousFlowDays() {
        System.out.print("your previously logged months:");
        for (String monthYear : flowMonthYearMap.keySet()) {
            System.out.println(monthYear);
        }
        System.out.print("please enter the month and year you would like to view as MM/YYYY");
        String monthName = input.nextLine();
        String monthNamePattern = "\\d\\d\\p{/}\\d\\d\\d\\d";
        if (Pattern.matches(monthNamePattern, monthName)) {
            System.out.println(flowMonthYearMap.get(monthName).getFlowDays()); // might have to check if it exists first.
            System.out.print("please enter the flow day you would like to view in detail as DD/MM/YYYY");
            String dayName = input.nextLine();
            String dayNamePattern = "\\d\\d\\p{/}\\d\\d\\p{/}\\d\\d\\d\\d";
            if (Pattern.matches(dayNamePattern, dayName)) {
                printAttributes1(flowMonthYearMap.get(monthName).findFlowDay(dayName));
            }

        } else {
            System.out.println("date is not properly formatted. please try again :)");
        }

    }

    private void printAttributes1(FlowDay oldDay) {
        System.out.print("On " + oldDay.getDayName() + ", you experienced:");
        if (oldDay.getFlow() == null) {
            if (oldDay.getMoods().isEmpty()) {
                if (oldDay.getSymptoms().isEmpty()) {
                    System.out.print("On " + oldDay.getDayName());
                } else {
                    System.out.print("On " + oldDay.getDayName() + ", You experienced: " + oldDay.getSymptoms() + ".");
                }
            } else {
                if (oldDay.getSymptoms().isEmpty()) {
                    System.out.print("On " + oldDay.getDayName() + ", You were feeling: " + oldDay.getMoods() + ".");
                } else {
                    System.out.print("On " + oldDay.getDayName() + ", You were feeling: " + oldDay.getMoods()
                            + ". You experienced: " + oldDay.getSymptoms() + ".");
                }
            }
        } else {
            printAttributes2(oldDay);
        }
    }

    private void printAttributes2(FlowDay oldDay) {
        if (oldDay.getMoods().isEmpty()) {
            if (oldDay.getSymptoms().isEmpty()) {
                System.out.print("On " + oldDay.getDayName() + ", My flow was: " + oldDay.getFlow() + ".");
            } else {
                System.out.print("On" + oldDay.getDayName() + ", My flow was: " + oldDay.getFlow()
                        + ". You experienced: "
                        + oldDay.getSymptoms() + ".");
            }
        } else {
            if (oldDay.getSymptoms().isEmpty()) {
                System.out.print("On " + oldDay.getDayName() + ", My flow was: " + oldDay.getFlow()
                        + ". You were feeling: " + oldDay.getMoods() + ".");
            } else {
                System.out.print("On " + oldDay.getDayName() + ", My flow was: " + oldDay.getFlow()
                        + ". You were feeling: " + oldDay.getMoods()
                        + "You experienced: " + oldDay.getSymptoms() + ".");
            }
        }
    }
}