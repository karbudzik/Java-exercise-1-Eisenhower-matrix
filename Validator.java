import java.time.LocalDate;

public class Validator {

    private View myView;

    public Validator() {
        myView = new View();
    }

    public TodoItem getNewItemDetails() {
        String title = getItemTitle();
        LocalDate deadline = getItemDeadline();
        boolean isImportant = getIsImportant();
        
        TodoItem temporaryItem = new TodoItem(title, deadline, isImportant);
        return temporaryItem;
    }

    private String getItemTitle() {
        String title = myView.getInput("Type the item you want to have on your list: ");
        while (title.length() < 1 || title.length() > 34) {
            title = myView.getInput("Your item can't be empty or longer than 34 characters. Try again: ");
        }
        return title;
    }

    private LocalDate getItemDeadline() {
        int year = getAndValidateInt("Type in the year of your deadline: ", 2020, 2030);
        int month = getAndValidateInt("Type in the month of your deadline in numerical format (e.g. 10): ", 1, 12);
        int day = getAndValidateInt("Type in the day of your deadline: ", 1, 31);
        LocalDate deadline;

        deadline = LocalDate.of(year, month, day);
        
        return deadline;
    }

    private int getAndValidateInt(String message, int fromInt, int toInt) {
        String input = myView.getInput(message);
        boolean isInteger = isInteger(input);
        while (isInteger == false || Integer.parseInt(input) < fromInt || Integer.parseInt(input) > toInt) {
            myView.printMessage(String.format("Your answer should be a valid number from %d to %d. Try again!", fromInt, toInt));
            input = myView.getInput(message);
            isInteger = isInteger(input);
        }

        return Integer.parseInt(input);
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    private boolean getIsImportant() {
        String input = myView.getInput("Is this task especially important to you? Type Y if yes and N if not: ");
        while (!input.equals("Y") && !input.equals("y") && !input.equals("n") && !input.equals("N")) {
            input = myView.getInput("Please type Y or N: ");
        }
        boolean isImportant = (input.equals("Y") || input.equals("y")) ? true : false;
        return isImportant;
    }

    public String getQuarterName() {
        String input = myView.getInput("Choose the quarter among: IU, IN, NU, NN: ");
        while (!input.equals("IU") && !input.equals("IN") && !input.equals("NU") && !input.equals("NN") &&
               !input.equals("iu") && !input.equals("in") && !input.equals("nu") && !input.equals("nn")) {
            input = myView.getInput("Wrong answer! Choose the quarter among: IU, IN, NU, NN:");
        }
        return input.toUpperCase();
    }

    public int getIndex() {
        String input = myView.getInput("Type the index of the item in chosen quarter: ");
        boolean isInteger = isInteger(input);
        while (isInteger == false || Integer.parseInt(input) < 1) {
            input = myView.getInput("Wrong input. Type the correct number: ");
        }
        return (Integer.parseInt(input) - 1);
    }
}