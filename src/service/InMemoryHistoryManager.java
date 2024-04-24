package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int MAX_SIZE_HISTORIES = 10;
    private final ArrayList<Task> histories;

    public InMemoryHistoryManager() {
        this.histories = new ArrayList<>(10);
    }

    @Override
    public void add(Task task) {
        if (histories.size() < MAX_SIZE_HISTORIES)
            histories.add(task);
        else {
            histories.removeFirst();
            histories.add(task);
        }
    }


    @Override
    public List<Task> getHistory() {
        return histories;
    }
}
