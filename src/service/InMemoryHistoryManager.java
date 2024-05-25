package service;

import model.Node;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final HashMap<Integer, Node> histories;
    Node first;
    Node last;

    public InMemoryHistoryManager() {
        this.histories = new HashMap<>();
    }

    //просмотр задачи
    private List<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node current = first;
        while (current != null) {
            tasks.add(current.getTask());
            current = current.getNext();
        }
        return tasks;
    }

    // удаление узла из списка
    private void removeNode(Node node) {
        final Node prev = node.getPrev();
        final Node next = node.getNext();

        if (prev == null) {
            first = next;
        } else {
            prev.setNext(next);
        }

        if (next == null) {
            last = prev;
        } else {
            next.setPrev(prev);
        }
    }


    //добавление задачи
    @Override
    public void add(Task task) {
        if (task != null) {
            if (histories.containsKey(task.getId())) {
                remove(task.getId());
            }
            linkLast(task);
        }
    }

    // удаление задачи из просмотра
    @Override
    public void remove(int id) {
        Node node = histories.get(id);
        if (node != null) {
            removeNode(node);
            histories.remove(id);
        }
    }

    //добавление в конец
    private void linkLast(Task task) {
        final Node l = last;
        final Node newNode = new Node(l, task, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        histories.put(task.getId(), newNode);
    }

    //перекладываем из хешмапы в ArrayList
    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}
