package ui;

import model.EntryManager;
import model.FlowDay;
import model.FlowMonth;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class FlowApp {
    private Map<String, FlowMonth> flowMonthYearMap = new HashMap<>();
    private Scanner input;

    // EFFECTS: runs the menstrual cycle tracker application
    public FlowApp() {
        runFlowApp();
    }

    //MODIFIES: this
    // EFFECTS: processes user input
    private void runFlow() {
        boolean keepGoing = true;
        String command = null;

        init();

        while(keepGoing) {
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
            displayFlowDayMenu();
        } else if (command.equals("v")) {
            viewPreviousFlowDays();
        } else {
            System.out.println("Selection not valid...");
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
    // EFFECTS: ...
    private void displayFlowDayMenu() {
        System.out.println("\nselect a field to log your flow day:");
        System.out.println("\td -> date");
        System.out.println("\tf -> flow");
        System.out.println("\tm -> mood");
        System.out.println("\ts -> symptoms");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: ...
    private void enterDate() {
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
                EntryManager.addFlowMonth(monthName, newFlowMonth);
            }

        }
    }

    /*

    // MODIFIES: this
    // EFFECTS: creates a flow day by entering date
    private void enterDate(Integer command) { // WHAT TYPE IS THE DATE....
        System.out.println("Enter the date for your flow day");
        FlowDay newfd = new FlowDay(2022);
        addFlowDay(newfd); // it's okay to have it like this.'

    // MODIFIES: this
    // EFFECTS: enters the flow in for that day
    private void enterFlow(String command) {
        System.out.println("Enter the flow for your flow day");


    }

    // MODIFIES: this
    // EFFECTS: adds a mood to list of moods
    private void enterMood() {

    }

    // MODIFIES: this
    // EFFECTS: adds a symptom to list of symptoms
    private void enterSymptom() {

    }

    // EFFECTS: prompts user to select from the saved years to view the cycles that have occurred
    private FlowMonth selectOverview() {
        return null;
    }


    // EFFECTS: prompts user to select from a day that has data logged on it
    private FlowDay selectFlowDay() {
        return null;
    }

    // EFFECTS: prints the data entered in for the selected day to the screen
    private void printFlowDay(FlowDay selected) {

    }

     */
}