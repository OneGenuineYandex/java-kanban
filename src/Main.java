import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        //Мои "чудо-тесты")))
        TaskManager taskManager = new TaskManager();
        System.out.println("Проверка создания сущностьей");
        Task task = taskManager.create(new Task(taskManager.getSeq(), "Новая задача", Status.NEW, "Встать"));
        System.out.println("Create task: " + task);
        Task task1 = taskManager.create(new Task(taskManager.getSeq(), "Новая задача1", Status.NEW, "Сесть"));
        System.out.println("Create task1: " + task1);
        Epic epic = taskManager.createEpic(new Epic(taskManager.getSeq(), "Новый эпик", Status.NEW, "Приготовить суп"));
        System.out.println("Create epic: " + epic);
        Epic epic1 = taskManager.createEpic(new Epic(taskManager.getSeq(), "Новый эпик1", Status.NEW, "Прибраться"));
        System.out.println("Create epic1: " + epic1);
        SubTask subTask = taskManager.createSubTask(new SubTask(taskManager.getSeq(), "Новая подзадача", Status.NEW, "Купить курицу", epic));
        System.out.println("Create subTask: " + subTask);
        SubTask subTask1 = taskManager.createSubTask(new SubTask(taskManager.getSeq(), "Новая подзадача1", Status.NEW, "Купить картошку", epic));
        System.out.println("Create subTask1: " + subTask1);
        SubTask subTask2 = taskManager.createSubTask(new SubTask(taskManager.getSeq(), "Новая подзадача2", Status.NEW, "Вымыть посуду", epic1));
        System.out.println("Create subTask2: " + subTask2);
        SubTask subTask3 = taskManager.createSubTask(new SubTask(taskManager.getSeq(), "Новая подзадача3", Status.NEW, "Вымыть полы", epic1));
        System.out.println("Create subTask3: " + subTask3);
        System.out.println("_____________________");

        System.out.println("Проверка вывода всех задач, подзадач, эпиков");
        System.out.println("Вывод всех задач");
        System.out.println(taskManager.get());
        System.out.println("Вывод всех подзадач");
        System.out.println(taskManager.getSubTask());
        System.out.println("Вывод всех эпиков");
        System.out.println(taskManager.getEpic());
        System.out.println("Вывод всех подзадач у эпика");
        System.out.println(taskManager.getSubTasksEpic(taskManager.getEpics().get(3)));
        System.out.println("_____________________");

        System.out.println("Проверка обновления сущностей ");
        taskManager.update(new Task(1, "Обновленная новая задача", Status.IN_PROGRESS, "Уже стою"));
        System.out.println("Create updateTask: " + taskManager.getTasks().get(1));
        taskManager.updateSubTask(new SubTask(7, "Обновленная новая подзадача1", Status.IN_PROGRESS, "Начал мыть посуду ", epic1));
        System.out.println("Create updateSubTask: " + taskManager.getSubTasks().get(7));
        taskManager.updateEpic(new Epic(3, "Обновленный новый эпик", "Приготовить лапшу"));
        System.out.println("Create updateEpic: " + taskManager.getEpics().get(3));
        System.out.println("_____________________");

        System.out.println("Проверка получения по id");
        System.out.println(taskManager.get(1));
        System.out.println(taskManager.getSubTask(7));
        System.out.println(taskManager.getEpic(3));
        System.out.println("_____________________");

        System.out.println("Проверка удаления по сущности");
        taskManager.delete(1);
        System.out.println(taskManager.get());
        taskManager.deleteSubTask(6);
        System.out.println(taskManager.getSubTask());
        taskManager.deleteEpic(3);
        System.out.println(taskManager.getEpic());
        System.out.println("_____________________");

        System.out.println("Проверка работы вычисления статуса эпика:");
        System.out.println("Состав подзадач эпика  до изменение задач:");
        System.out.println(taskManager.getEpic(4).getSubTasks());
        System.out.println("Статус эпика после частичного начала задач: " + epic1.getStatus());
        taskManager.updateSubTask(new SubTask(8, "Обновленная подзадача3", Status.DONE, "Вымыл полы", epic1));
        taskManager.updateSubTask(new SubTask(7, "Обновленная подзадача2", Status.DONE, "Вымыл посуду", epic1));
        System.out.println("Состав подзадач эпика после изменение задач:");
        System.out.println(taskManager.getEpics().get(4).getSubTasks());
        System.out.println("Статус эпика после частичного начала задач: " + epic1.getStatus());
        System.out.println("Статус эпика который только что создан: " + epic.getStatus());
        System.out.println("_____________________");

        System.out.println("Проверка удаления по всех задач/подзадач/эпиков");
        System.out.println("Текущие задачи: " + taskManager.get());
        System.out.println("Удаляем задачи");
        taskManager.deleteAll();
        System.out.println("Текущие задачи после удаления : " + taskManager.get());

        System.out.println("Текущие эпики до удаления" + taskManager.getEpic());
//        System.out.println("Удаляем эпики");
//        taskManager.deleteAllEpic();
//        System.out.println("Текущие эпики после удаления"  + taskManager.getEpic());
//        System.out.println("Текущие подзадачи после удаления"  + taskManager.getSubTasks());
        System.out.println("Текущие подзадачи: " + taskManager.getSubTask());
        System.out.println("Удаляем подзадачи");
        taskManager.deleteAllSubTask();
        System.out.println("Текущие подзадачи после удаления : " + taskManager.getSubTask());
        System.out.println("Текущие эпики после подзадач удаления : " + taskManager.getEpic());

    }
}
