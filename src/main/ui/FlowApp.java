package ui;

import model.FlowDay;

import java.util.Scanner;

/*

public class FlowApp {
    private FlowYear 2022;
    private Scanner input;

    // EFFECTS: runs the menstrual cycle tracker application
    public FlowApp() {
        runFlow();
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
            doEnterFlowDay();
        } else if (command.equals("v")) {
            viewPreviousFlowDays();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the year
    private void init() {
        2022 = new FlowYear(2022);
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
}
*/