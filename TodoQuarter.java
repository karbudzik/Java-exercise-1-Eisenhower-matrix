import java.util.ArrayList;
import java.time.LocalDate;


public class TodoQuarter {
    
    private ArrayList<TodoItem> todoItems;  
    
    public TodoQuarter() {
        todoItems = new ArrayList<TodoItem>();
    }

    public void addItem(String title, LocalDate deadline, boolean isImportant) {
        TodoItem newItem = new TodoItem(title, deadline, isImportant);
        
        if (todoItems.size() == 0) {
            todoItems.add(newItem);
        } 
        else {
            for (TodoItem item :todoItems) {
                if (newItem.getDeadline().isBefore(item.getDeadline())) {
                    int index = todoItems.indexOf(item);
                    todoItems.add(index, newItem);
                    break;
                } 
            }
            if (!todoItems.contains(newItem)) {
                todoItems.add(newItem);
            }
        }
        
    }

    public void removeItem(int index) {
        todoItems.remove(index);
    }

    public void archiveItems() {
        ArrayList<TodoItem> toArchive = new ArrayList<TodoItem>();
        for (TodoItem item :todoItems) {
            if (item.getIsDone() == true) {
                toArchive.add(item);
            }
        todoItems.removeAll(toArchive);
        }
    }

    public TodoItem getItem(int index) throws IndexOutOfBoundsException {
        if (index >= todoItems.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return todoItems.get(index);
        }
    }

    public ArrayList<TodoItem> getItems() {
        return todoItems;
    }

    public String toString() {
        String itemsToPrint = "";
        for (int i = 1; i <= todoItems.size(); i++) {
            itemsToPrint += Integer.toString(i) + ". " + todoItems.get(i-1).toString() + "\n";
        }
        return itemsToPrint;
    }
}