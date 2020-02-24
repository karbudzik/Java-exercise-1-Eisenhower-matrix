import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.IOException;

public class TodoMatrix {

    public Map<String, TodoQuarter> todoQuarters;

    public TodoMatrix() {
        todoQuarters = new HashMap<String, TodoQuarter>();
        createQuarters();
    }

    private void createQuarters() {
        todoQuarters.put("IU", new TodoQuarter());
        todoQuarters.put("IN", new TodoQuarter());
        todoQuarters.put("NU", new TodoQuarter());
        todoQuarters.put("NN", new TodoQuarter());
    }

    public Map<String, TodoQuarter> getQuarters() {
        return todoQuarters;
    }

    public TodoQuarter getQuarter(String status) {
        return todoQuarters.get(status);
    }

    private String matchToQuarter(LocalDate deadline, boolean isImportant) {

        String key;
        LocalDate today = LocalDate.now();
        long toDeadline = ChronoUnit.DAYS.between(today, deadline);
        if (isImportant == true & toDeadline <= 3) {
            key = "IU";
        } else if (isImportant == true & toDeadline > 3) {
            key = "IN";
        } else if (isImportant == false & toDeadline <= 3) {
            key = "NU";
        } else {
            key = "NN";
        }

        return key;
    }

    public void addItem(String title, LocalDate deadline, boolean isImportant) {
        String key = matchToQuarter(deadline, isImportant);
        TodoQuarter quarter = todoQuarters.get(key);
        quarter.addItem(title, deadline, isImportant);
    }

    public void addItem(String title, LocalDate deadline) {
        boolean isImportant = false;
        String key = matchToQuarter(deadline, isImportant);
        TodoQuarter quarter = todoQuarters.get(key);
        quarter.addItem(title, deadline, isImportant);
    }

    public void addItemsFromFile(String fileName) {
        try {
            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String itemFromFile = myReader.nextLine();
                String[] itemFromFileArray = itemFromFile.split("\\|");

                String title = (String) Array.get(itemFromFileArray, 0);
                LocalDate deadline = LocalDate.parse((String) Array.get(itemFromFileArray, 1));
                boolean isImportant = (itemFromFileArray.length == 3) ? true : false;

                addItem(title, deadline, isImportant);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file");
        } 
    }

    public void saveItemsToFile(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (TodoQuarter quarter: todoQuarters.values()) {
                for (TodoItem item: quarter.getItems()) {
                    String title = item.getTitle();
                    String deadline = item.getDeadline().toString();
                    String isImportant = (item.getIsImportant()) ? "important" : "";
                
                    String stringToSave = title + "|" + deadline + "|" + isImportant + "\n";
                
                    myWriter.write(stringToSave);
                } 
            }
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }  
    }
    
    public void archiveItems() {
        for (TodoQuarter itemsList : todoQuarters.values()) {
            itemsList.archiveItems();
        }
    }

    public void markAsDone(String quarter, int numberIndex) {
        TodoQuarter chosenQuarter = todoQuarters.get(quarter);
        chosenQuarter.getItem(numberIndex).mark();
    }
}
