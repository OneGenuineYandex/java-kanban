package model;

public class SubTask extends Task {
    private Epic epic;

    public SubTask(int id, String name, Status status, String description, Epic epic) {
        super(id, name, status, description);
        this.epic = epic;
    }

    public SubTask(int id, String name, Status status, String description) {
        super(id, name, status, description);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
