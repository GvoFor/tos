package ua.fam.tos.domain.boarditem.todolist;

public class ToDoListModificationException extends RuntimeException{
    public ToDoListModificationException() {
        super();
    }

    public ToDoListModificationException(String message) {
        super(message);
    }
}
