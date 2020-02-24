import java.util.Scanner;

public class View {

    public View() {
    }

    public void printMatrix(TodoMatrix myMatrix) {
        clearScreen();
        printMatrixTitle();
        System.out.println(" -----------------------------------------------------------------------------");
        printHalfMatrix(myMatrix, "IU", "IN", "IMPORTANT AND URGENT:", "IMPORTANT BUT NOT URGENT:");
        System.out.println(" -----------------------------------------------------------------------------");
        printHalfMatrix(myMatrix, "NU", "NN", "NOT IMPORTANT BUT URGENT:", "NOT IMPORTANT AND NOT URGENT:");
        System.out.println(" -----------------------------------------------------------------------------");
    }

    private void printHalfMatrix(TodoMatrix myMatrix, String key1, String key2, String title1, String title2) {
        TodoQuarter quarter1 = myMatrix.getQuarters().get(key1);
        TodoQuarter quarter2 = myMatrix.getQuarters().get(key2);
        int quarterHeight = Math.max(quarter1.getItems().size(), quarter2.getItems().size()) + 1;

        printQuartersHeadings(title1, title2);
        printQuartersContent(quarter1, quarter2, quarterHeight);
    }

    private void printQuartersHeadings(String title1, String title2) {
        System.out.print(" | ");
        System.out.printf("%-35s", title1);
        System.out.print(" | ");
        System.out.printf("%-35s", title2);
        System.out.print(" | " + "\n");
        System.out.println(" |                                     |                                     |");
    }

    private void printQuartersContent(TodoQuarter quarter1, TodoQuarter quarter2, int quarterHeight) {
        for (int i = 0; i < quarterHeight; i++) {
            System.out.print(" | ");
            printItemIfExists(quarter1, i);
            System.out.print(" | ");
            printItemIfExists(quarter2, i);
            System.out.print(" | " + "\n");
        }
    }

    private void printItemIfExists(TodoQuarter myQuarter, int index) {
        if (index >= myQuarter.getItems().size()) {
            System.out.printf("%-35s", "");
        } else {
            System.out.printf("%-35s", myQuarter.getItem(index).toString());
        }
    }

    private void printMatrixTitle() {
        String title = ("                                 MY TO_DO LIST:");
        System.out.println("");
        System.out.println(title);
    }

    public void printMainMenu() {
        String[] menuOptions = {
            "Save and quit",
            "Add new item to my matrix",
            "Mark an item as done",
            "Archive all done items"
        };

        System.out.println("");
        System.out.println(" WHAT DO YOU WANT TO DO?");


        for(int i = 0; i < menuOptions.length; i++){
            System.out.printf(" %d --> %s %n", i, menuOptions[i]);
        }
    }

    public String getInput(String intro) {
        System.out.println("\n" + " " + intro);
        System.out.print(" ");
        Scanner scannerFromUser = new Scanner(System.in);
        String input = scannerFromUser.nextLine();
        
        return input;
    }

    public void printMessage(String message) {
        System.out.println("\n" + " " + message);
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}