package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для Managers")
public class ManagersTest {

    @Test
    @DisplayName("Проверка создания TaskManager по умолчанию")
    void testGetDefaultTaskManager() {
        TaskManager taskManager = Managers.getDefault();

        assertNotNull(taskManager, "TaskManager не должен быть null");
        assertInstanceOf(InMemoryTaskManager.class, taskManager, "TaskManager должен быть экземпляром InMemoryTaskManager");
    }

    @Test
    @DisplayName("Проверка создания HistoryManager по умолчанию")
    void testGetDefaultHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(historyManager, "HistoryManager не должен быть null");
        assertInstanceOf(InMemoryHistoryManager.class, historyManager, "HistoryManager должен быть экземпляром InMemoryHistoryManager");
    }

    @Test
    @DisplayName("Проверка, что TaskManager может добавлять и получать задачи")
    void testTaskManagerAddAndGetTasks() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task(1, "Test Task", Status.NEW, "Testing the TaskManager");

        taskManager.create(task);

        assertEquals(1, taskManager.get().size(), "Должен быть один добавленный Task");
        assertEquals(task, taskManager.get(task.getId()), "Добавленный Task должен быть в TaskManager");
    }
}
