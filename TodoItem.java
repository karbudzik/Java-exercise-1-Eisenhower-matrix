import java.time.LocalDate;

public class TodoItem {
    
    private String title;
    private LocalDate deadline;
    private boolean isDone;
    private boolean isImportant;

    public TodoItem(String title, LocalDate deadline, boolean isImportant) {
        this.title = title;
        this.deadline = deadline;
        this.isImportant = isImportant;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public boolean getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(boolean isImportant) {
        this.isImportant = isImportant;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        String checkedSign = ((isDone) ? "[x]" : "[ ]");
        String day = Integer.toString(deadline.getDayOfMonth());
        String month = Integer.toString(deadline.getMonthValue());
        String formattedString = checkedSign + " " + day + "-" + month + " " + title;
        return formattedString;
    }
}
  
