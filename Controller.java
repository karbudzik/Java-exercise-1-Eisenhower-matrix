import java.time.DateTimeException;

public class Controller {
    
    private TodoMatrix myMatrix;
    private Validator myValidator;
    private View myView;
    
    public Controller() {
        myMatrix = new TodoMatrix();
        myView = new View();
        myValidator = new Validator();

        boolean isRunning = true;
        myMatrix.addItemsFromFile("todo_items_read_test.csv");
        myView.printMatrix(myMatrix);
        myView.printMainMenu();
        
        while (isRunning) {
            String input = myView.getInput("Your choice: ");
            try {
                if (input.equals("0")) {
                    isRunning = false;
                    myMatrix.saveItemsToFile("todo_items_write_test.csv");
                    myView.printMessage("See you next time!");
                } 
                else if (input.equals("1")) {
                    TodoItem temporaryItem = myValidator.getNewItemDetails();
                    myMatrix.addItem(temporaryItem.getTitle(), temporaryItem.getDeadline(), temporaryItem.getIsImportant());
                    myView.printMatrix(myMatrix);
                    myView.printMainMenu();
                } 
                else if (input.equals("2")) {
                    String quarterName = myValidator.getQuarterName(); 
                    int index = myValidator.getIndex();
                    myMatrix.markAsDone(quarterName, index);
                    myView.printMatrix(myMatrix);
                    myView.printMainMenu();
                } 
                else if (input.equals("3")) {
                    myMatrix.archiveItems();
                    myView.printMessage("Done! If you had any finished items in this quarter, they have been archived!");
                }  
                else {
                    myView.printMessage("Wrong input, try again!");
                }
            } catch (DateTimeException e) {
                myView.printMessage("There is no such date!");
            } catch (IndexOutOfBoundsException e) {
                myView.printMessage("There is no item with given index in this quarter.");
            } 
        }
    }
}



        
