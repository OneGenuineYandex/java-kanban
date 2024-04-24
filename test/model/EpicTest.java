package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {

    private Epic epic;

    @BeforeEach
    @DisplayName("Подготовка данных для тестов Epic")
    public void setUp() {
        epic = new Epic(1, "Main Epic", Status.IN_PROGRESS, "Main Epic Description");
    }

    @Test
    @DisplayName("Тестирование добавления подзадачи")
    public void testAddSubTask() {
        SubTask subTask = new SubTask(2, "SubTask 1", Status.IN_PROGRESS, "Description for SubTask");
        epic.addSubTask(subTask);
        List<SubTask> subTasks = epic.getSubTasks();
        assertTrue(subTasks.contains(subTask), "Проверка, что подзадача добавлена в список");
    }

    @Test
    @DisplayName("Тестирование удаления подзадачи по объекту")
    public void testDeleteSubTaskByObject() {
        SubTask subTask = new SubTask(3, "SubTask 2", Status.DONE, "Another SubTask");
        epic.addSubTask(subTask);
        epic.deleteSubtask(subTask);
        assertFalse(epic.getSubTasks().contains(subTask), "Проверка, что подзадача удалена из списка");
    }

    @Test
    @DisplayName("Тестирование удаления подзадачи по ID")
    public void testDeleteSubTaskById() {
        SubTask subTask = new SubTask(4, "SubTask 3", Status.NEW, "Description for SubTask");
        epic.addSubTask(subTask);
        epic.deleteSubtask(4);
        assertFalse(epic.getSubTasks().contains(subTask), "Проверка, что подзадача удалена по ID");
    }

    @Test
    @DisplayName("Тестирование получения списка подзадач")
    public void testGetSubTasks() {
        SubTask subTask1 = new SubTask(5, "SubTask 4", Status.IN_PROGRESS, "Another Description");
        SubTask subTask2 = new SubTask(6, "SubTask 5", Status.DONE, "Description for SubTask");
        epic.addSubTask(subTask1);
        epic.addSubTask(subTask2);

        List<SubTask> subTasks = epic.getSubTasks();
        assertEquals(2, subTasks.size(), "Проверка, что список подзадач содержит нужное количество элементов");
        assertTrue(subTasks.contains(subTask1), "Проверка, что список подзадач содержит первый элемент");
        assertTrue(subTasks.contains(subTask2), "Проверка, что список подзадач содержит второй элемент");
    }
}

// два для менеджеров
// 3 для тасков
// +статического класса манеджерс
//тесты писать на каждый метод интерфейса (говорил еще про публричный)
