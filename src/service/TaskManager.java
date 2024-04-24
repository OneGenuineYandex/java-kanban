package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.Collection;
import java.util.List;

public interface TaskManager {
    Collection<Task> get();

    Collection<SubTask> getSubTask();

    Collection<Epic> getEpic();

    List<SubTask> getSubTasksEpic(Epic epic);

    void deleteAll();

    void deleteAllSubTask();

    void deleteAllEpic();

    Task get(int id);

    SubTask getSubTask(int id);

    Epic getEpic(int id);

    Task create(Task task);

    SubTask createSubTask(SubTask subTask);

    Epic createEpic(Epic epic);

    void update(Task task);

    void updateSubTask(SubTask subTask);

    void updateEpic(Epic epic);

    void delete(int id);

    void deleteSubTask(int id);

    void deleteEpic(int id);

//    List<Task> getHistory();
}
