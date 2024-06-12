package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("Тестирование что элементы не повторяются")
    public void testNoDuplicateTasks() {
        // Добавляем две задачи
        historyManager.add(task1);
        historyManager.add(task2);
        // Повторно добавляем первую задачу
        historyManager.add(task1);
        List<Task> history = historyManager.getHistory();

        assertEquals(2, history.size(), "Проверка, что в истории осталось две задачи");
        // Проверяем, что в истории есть задача task1 и task2
        assertTrue(history.contains(task1), "Проверка, что в истории есть задача task1");
        assertTrue(history.contains(task2), "Проверка, что в истории есть задача task2");
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
