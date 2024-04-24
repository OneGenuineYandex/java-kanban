package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private Task task;

    @BeforeEach
    @DisplayName("Подготовка данных для тестов Task")
    public void setUp() {
        task = new Task(1, "Test Task", Status.NEW, "Test Task Description");
    }

    @Test
    @DisplayName("Тестирование получения и установки ID")
    public void testId() {
        task.setId(10);
        assertEquals(10, task.getId(), "Проверка, что ID установлен правильно");
    }

    @Test
    @DisplayName("Тестирование получения и установки имени задачи")
    public void testName() {
        task.setName("New Task Name");
        assertEquals("New Task Name", task.getName(), "Проверка, что имя задачи установлено правильно");
    }

    @Test
    @DisplayName("Тестирование получения и установки статуса")
    public void testStatus() {
        task.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS, task.getStatus(), "Проверка, что статус задачи установлен правильно");
    }

    @Test
    @DisplayName("Тестирование получения и установки описания")
    public void testDescription() {
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription(), "Проверка, что описание задачи установлено правильно");
    }

    @Test
    @DisplayName("Тестирование equals для задач")
    public void testEquals() {
        Task task2 = new Task(1, "Test Task", Status.NEW, "Test Task Description");
        assertEquals(task, task2, "Проверка, что задачи равны при одинаковых данных");

        task2.setId(2);
        assertNotEquals(task, task2, "Проверка, что задачи не равны при разных ID");
    }

    @Test
    @DisplayName("Тестирование toString для задач")
    public void testToString() {
        String expected = "Task{id=1, name='Test Task', status=NEW, description='Test Task Description'}";
        assertEquals(expected, task.toString(), "Проверка, что toString возвращает ожидаемый результат");
    }
}
