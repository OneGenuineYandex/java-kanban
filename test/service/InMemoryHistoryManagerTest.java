package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    private InMemoryHistoryManager historyManager;
    private Task task1;
    private Task task2;

    @BeforeEach
    @DisplayName("Подготовка данных для тестов InMemoryHistoryManager")
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
        task1 = new Task(1, "Task 1", Status.NEW, "Description 1");
        task2 = new Task(2, "Task 2", Status.NEW, "Description 2");
    }

    @Test
    @DisplayName("Тестирование добавления задачи в историю")
    public void testAddTask() {
        historyManager.add(task1);
        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "Проверка, что история содержит одну задачу");
        assertTrue(history.contains(task1), "Проверка, что задача добавлена в историю");
    }

    @Test
    @DisplayName("Тестирование максимального размера истории")
    public void testMaxHistorySize() {
        // Заполнить историю
        for (int i = 1; i <= 10; i++) {
            historyManager.add(new Task(i, "Task_ " + i, Status.NEW, "Description " + i));
        }

        assertEquals(10, historyManager.getHistory().size(), "Проверка, что история заполнена до максимального размера");

        // Добавить задачу, чтобы проверить удаление
        historyManager.add(task1);
        assertEquals(10, historyManager.getHistory().size(), "Проверка, что размер истории остается 10");
        assertFalse(historyManager.getHistory().contains(new Task(1, "Task_ 1", Status.NEW, "Description 1")),
                "Проверка, что первая задача удалена при добавлении новой");
    }

    @Test
    @DisplayName("Тестирование получения истории")
    public void testGetHistory() {
        historyManager.add(task1);
        historyManager.add(task2);
        List<Task> history = historyManager.getHistory();

        assertEquals(2, history.size(), "Проверка, что в истории две задачи");
        assertTrue(history.contains(task1), "Проверка, что история содержит задачу 1");
        assertTrue(history.contains(task2), "Проверка, что история содержит задачу 2");
    }
}
