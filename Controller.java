import java.time.DateTimeException;

public class Controller {
    
    private TodoMatrix myMatrix;
    private Validator validator;
    private View myView;
    
    public Controller() {
        myMatrix = new TodoMatrix();
        myView = new View();
        validator = new Validator();

        boolean isRunning = true;
        
        // czy mogę wyjątki uwzględnione w todoMatrix wyłapać tutaj? i jak?
        myMatrix.addItemsFromFile("todo_items_read_test.csv");
        
        myView.printMatrix(myMatrix);
        myView.printMainMenu();
        
        while (isRunning) {
            String input = myView.getInput("Your choice: ");
            
            // czy podzielić te bloki kodu na mniejsze w jakiś sposób?
            if (input.equals("0")) {
                isRunning = false;
                myMatrix.saveItemsToFile("todo_items_write_test.csv");
                myView.printMessage("See you next time!");
            } 
            else if (input.equals("1")) {
                try {
                    TodoItem temporaryItem = validator.getNewItemDetails();
                    myMatrix.addItem(temporaryItem.getTitle(), temporaryItem.getDeadline(), temporaryItem.getIsImportant());
                    myView.printMatrix(myMatrix);
                    myView.printMainMenu();
                } catch (DateTimeException e) {
                    myView.printMessage("qqq");
                } 
            } 
            else if (input.equals("2")) {
                try {
                    String quarterName = validator.getQuarterName(); 
                    int index = validator.getIndex();
                    myMatrix.markAsDone(quarterName, index);
                    myView.printMatrix(myMatrix);
                    myView.printMainMenu();
                } catch (IndexOutOfBoundsException e) {
                    myView.printMessage("There is no item with given index in this quarter.");
                } 
            } 
            else if (input.equals("3")) {
                myMatrix.archiveItems();
                // jaki wyjątek mogę podnieść tutaj, żeby zakomunikować, że nie mamy co archiwizować?
            }  
            else {
                myView.printMessage("Wrong input!");
            }
        }
    }
}



        
