package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private int seq = 0;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> subTasks;
    private final HashMap<Integer, Epic> epics;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.subTasks = new HashMap<>();
        this.epics = new HashMap<>();
    }

    private int generateId() {
        return ++seq;
    }

    public Collection<Task> get() {
        return tasks.values();
    }

    public Collection<SubTask> getSubTask() {
        return subTasks.values();
    }

    public Collection<Epic> getEpic() {
        return epics.values();
    }

    public List<SubTask> getSubTasksEpic(Epic epic) {
        return epic.getSubTasks();
    }

    public void deleteAll() {
        tasks.clear();
    }

    public void deleteAllSubTask() {
        subTasks.clear();
    }

    public void deleteAllEpic() {
        epics.clear();
        subTasks.clear();
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public Task create(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        subTasks.put(subTask.getId(), subTask);
        Epic epic = subTask.getEpic();
        if (epics.get(subTask.getId()) == null) {
            epic.setSubTasks(new ArrayList<>());
        }
        epic.addSubTask(subTask);
        calculateStatus(subTask.getEpic());
        epics.put(subTask.getId(), epic);
        return subTask;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void update(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        subTask.getEpic().deleteSubtask(subTask.getId());
        subTask.getEpic().addSubTask(subTask);
        calculateStatus(subTask.getEpic());

    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            System.out.println("Эпик не найден");
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        epics.put(epic.getId(), saved);
    }

    public void delete(int id) {
        tasks.remove(id);
    }

    public void deleteSubTask(int id) {
        subTasks.remove(id);
    }

    public void deleteEpic(int id) {
        epics.remove(id);
    }

    private void calculateStatus(Epic epic) {
        int statusNewCount = 0;
        int statusDoneCount = 0;
        List<SubTask> subTasks = getSubTasksEpic(epic);
        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() == Status.NEW) {
                statusNewCount++;
            } else if (subTask.getStatus() == Status.DONE) {
                statusDoneCount++;
            }
        }
        if (statusNewCount == subTasks.size()) {
            epic.setStatus(Status.NEW);
        } else if (statusDoneCount == subTasks.size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    public int getSeq() {
        return seq;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return subTasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }
}
