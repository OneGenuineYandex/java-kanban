package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<SubTask> subTasks;

    public Epic(int id, String name, Status status, String description) {
        super(id, name, status, description);
        this.subTasks = new ArrayList<>();
    }

    public Epic(int id, String name, String description) {
        super(id, name, null, description);
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void deleteSubtask(SubTask subTask) {
        subTasks.remove(subTask);
    }

    public void deleteSubtask(int id) {
        for (int i = 0; i < subTasks.size(); i++) {
            SubTask subTask = subTasks.get(i);
            if (subTask.getId() == id) {
                subTasks.remove(i);
                break;
            }
        }
    }


    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}
