package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubTaskTest {

    private SubTask subTask;
    private Epic epic;

    @BeforeEach
    @DisplayName("Подготовка данных для тестов")
    public void setUp() {
        epic = new Epic(1, "Main Epic", "Description of the Epic");
        subTask = new SubTask(7, "SubTask 6", Status.IN_PROGRESS, "SubTask with toString Test", epic);
    }

    @Test
    @DisplayName("Тестирование метода toString")
    public void testToString() {
        String expectedToString = "Task{id=7, name='SubTask 6', status=IN_PROGRESS, description='SubTask with toString Test'}";
        assertEquals(expectedToString, subTask.toString(), "Проверка, что метод toString возвращает правильное значение");
    }

    @Test
    @DisplayName("Тестирование установки ID")
    public void testSetId() {
        subTask.setId(10);
        assertEquals(10, subTask.getId(), "Проверка, что метод setId устанавливает правильное значение");
    }

    @Test
    @DisplayName("Тестирование установки названия")
    public void testSetName() {
        subTask.setName("New SubTask Name");
        assertEquals("New SubTask Name", subTask.getName(), "Проверка, что метод setName устанавливает правильное название");
    }

    @Test
    @DisplayName("Тестирование установки и получения эпика")
    public void testSetEpic() {
        Epic newEpic = new Epic(2, "Another Epic", "Another description");
        subTask.setEpic(newEpic);
        assertEquals(newEpic, subTask.getEpic(), "Проверка, что метод setEpic устанавливает правильный эпик");
    }

    @Test
    @DisplayName("Тестирование установки и получения статуса")
    public void testSetStatus() {
        subTask.setStatus(Status.DONE);
        assertEquals(Status.DONE, subTask.getStatus(), "Проверка, что метод setStatus устанавливает правильный статус");
    }
}