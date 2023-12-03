package ua.fam.tos.domain.boarditem.todolist;
import ua.fam.tos.domain.boarditem.BoardItem;
import java.util.List;

public class ToDoList extends BoardItem{
    private final List<ToDo> todolist;

    public ToDoList(List<ToDo> todolist) {
        this.todolist = todolist;
    }

    public void addToDo(ToDo todo){
        todolist.add(todo);
    }

    public void removeToDo(int index){
        if (index >= 0 && index < todolist.size()) {
            todolist.remove(index);
        }
        else{
            throw new ToDoListModificationException("Index out of bounds");
        }
    }

    public List<ToDo> getToDoList() {
        return todolist;
    }

    public ToDo getToDoByIndex(int index) {
        if (index >= 0 && index < todolist.size()) {
            return todolist.get(index);
        }
        return null;
    }

    @Override
    public boolean isKnowAboutContributorWithUsername(String username) {
        return (getCreator().getUsername().equals(username));
    }
}
