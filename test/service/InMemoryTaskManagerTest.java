package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для InMemoryTaskManager")
public class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;
    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager(historyManager);
    }

    @Test
    @DisplayName("Создание задачи")
    void testCreateTask() {
        Task task = new Task(0, "Task 1", Status.NEW, "Test task creation");

        Task createdTask = taskManager.create(task);

        assertNotNull(createdTask, "Задача должна быть создана");
        assertEquals(1, createdTask.getId(), "ID должен быть равен 1");
        assertEquals(Status.NEW, createdTask.getStatus(), "Статус должен быть NEW");
    }

    @Test
    @DisplayName("Создание сабтаска")
    void testCreateSubTask() {
        Epic epic = new Epic(0, "Epic 1", Status.NEW, "Test epic creation");
        taskManager.createEpic(epic);

        SubTask subTask = new SubTask(0, "SubTask 1", Status.NEW, "Test subtask creation", epic);
        SubTask createdSubTask = taskManager.createSubTask(subTask);

        assertNotNull(createdSubTask, "Сабтаск должен быть создан");
        assertEquals(2, createdSubTask.getId(), "ID сабтаска должен быть 2");
        assertEquals(epic, createdSubTask.getEpic(), "Epic должен быть связан с сабтаском");
    }

    @Test
    @DisplayName("Создание эпика")
    void testCreateEpic() {
        Epic epic = new Epic(0, "Epic 1", Status.NEW, "Test epic creation");

        Epic createdEpic = taskManager.createEpic(epic);

        assertNotNull(createdEpic, "Эпик должен быть создан");
        assertEquals(1, createdEpic.getId(), "ID эпика должен быть 1");
        assertTrue(createdEpic.getSubTasks().isEmpty(), "Сабтаски должны быть пустыми вначале");
    }

    @Test
    @DisplayName("Удаление задачи")
    void testDeleteTask() {
        Task task = new Task(0, "Task 1", Status.NEW, "Test task deletion");
        taskManager.create(task);

        taskManager.delete(1);

        assertNull(taskManager.get(1), "Задачи не должно быть после удаления");
    }

    @Test
    @DisplayName("Удаление сабтаска")
    void testDeleteSubTask() {
        Epic epic = new Epic(0, "Epic 1", Status.NEW, "Test epic creation");
        taskManager.createEpic(epic);
        SubTask subTask = new SubTask(0, "SubTask 1", Status.NEW, "Test subtask creation", epic);
        taskManager.createSubTask(subTask);

        taskManager.deleteSubTask(2);

        assertNull(taskManager.getSubTask(2), "Сабтаска не должно быть после удаления");
        assertTrue(epic.getSubTasks().isEmpty(), "Список сабтасков эпика должен быть пустым");
    }

    @Test
    @DisplayName("Удаление эпика")
    void testDeleteEpic() {
        Epic epic = new Epic(0, "Epic 1", Status.NEW, "Test epic creation");
        taskManager.createEpic(epic);

        taskManager.deleteEpic(1);

        assertNull(taskManager.getEpic(1), "Эпика не должно быть после удаления");
    }

    @Test
    @DisplayName("Проверка обновления задачи")
    void testUpdateTask() {
        Task task = new Task(0, "Task 1", Status.NEW, "Test task");
        taskManager.create(task);

        task.setName("Updated Task");
        taskManager.update(task);

        assertEquals("Updated Task", taskManager.get(1).getName(), "Имя должно быть обновлено");
    }
}
