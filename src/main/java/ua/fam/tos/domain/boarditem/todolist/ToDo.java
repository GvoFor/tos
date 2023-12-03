package ua.fam.tos.domain.boarditem.todolist;
import ua.fam.tos.domain.boarditem.BoardItem;

public class ToDo{
    private ToDoStatus status;
    private String text;

    public void updateStatus() {
        if(status == ToDoStatus.INCOMPLETED){
            this.status = ToDoStatus.COMPLETED;
        }
        else{
            this.status = ToDoStatus.INCOMPLETED;
        }
    }

    public ToDoStatus getStatus() {
        return status;
    }
    public void setStatus(ToDoStatus status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
