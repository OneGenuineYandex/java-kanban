package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private int seq = 0;

    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> subTasks;
    private final HashMap<Integer, Epic> epics;
    private final HistoryManager historyManager;


    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
        this.tasks = new HashMap<>();
        this.subTasks = new HashMap<>();
        this.epics = new HashMap<>();
    }

    private int generateId() {
        return ++seq;
    }

    @Override
    public Collection<Task> get() {
        return tasks.values();
    }

    @Override
    public Collection<SubTask> getSubTask() {
        return subTasks.values();
    }

    @Override
    public Collection<Epic> getEpic() {
        return epics.values();
    }

    @Override
    public List<SubTask> getSubTasksEpic(Epic epic) {
        return epic.getSubTasks();
    }

    @Override
    public void deleteAll() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubTask() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTasks().clear();
            if (epic.getSubTasks().isEmpty()) {
                calculateStatus(epic);
            }
        }
    }

    @Override
    public void deleteAllEpic() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public Task get(int id) {
        return tasks.get(id);
    }

    @Override
    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        return epics.get(id);
    }

    @Override
    public Task create(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        subTasks.put(subTask.getId(), subTask);
        Epic epic = subTask.getEpic();
        epic.addSubTask(subTask);
        calculateStatus(subTask.getEpic());
        return subTask;
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        calculateStatus(epic);
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public void update(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        Epic epic = subTask.getEpic();
        epic.deleteSubtask(subTask.getId());
        epic.addSubTask(subTask);
        calculateStatus(epic);

    }

    @Override
    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            System.out.println("Эпик не найден");
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        epics.put(epic.getId(), saved);
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteSubTask(int id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = subTask.getEpic();
        epic.deleteSubtask(subTask);
        calculateStatus(epic);
        subTasks.remove(id);
    }

    @Override
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
}
