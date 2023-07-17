import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.services.*;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchExistingTask() {
        SimpleTask task1 = new SimpleTask(1, "Позвонить другу");
        SimpleTask task2 = new SimpleTask(2, "Сходить в магазин");
        SimpleTask task3 = new SimpleTask(3, "Позвонить маме");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = {task1};
        Task[] actual = todos.search("другу");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNonExistingTask() {
        SimpleTask task1 = new SimpleTask(1, "Позвонить другу");
        SimpleTask task2 = new SimpleTask(2, "Сходить в магазин");
        SimpleTask task3 = new SimpleTask(3, "Позвонить маме");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = {};
        Task[] actual = todos.search("работа");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBySubtask() {
        String[] subtasks1 = {"Позвонить другу", "Сходить в магазин", "Позвонить маме"};
        String[] subtasks2 = {"Собрать отчет", "Завести будильник", "Почитать книгу"};
        Epic epic1 = new Epic(1, subtasks1);
        Epic epic2 = new Epic(2, subtasks2);

        Todos todos = new Todos();
        todos.add(epic1);
        todos.add(epic2);

        Task[] expected = {epic1};
        Task[] actual = todos.search("Сходить в магазин");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnSubtasks() {
        String[] subtasks = {"Позвонить другу", "Сходить в магазин", "Позвонить маме"};
        Epic epic = new Epic(1, subtasks);

        String[] expected = {"Позвонить другу", "Сходить в магазин", "Позвонить маме"};
        String[] actual = epic.getSubtasks();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnTitle() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить другу");

        String expected = "Позвонить другу";
        String actual = simpleTask.getTitle();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTopic() {
        Meeting meeting = new Meeting(1, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");

        String expected = "Выкатка 3й версии приложения";
        String actual = meeting.getTopic();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnProject() {
        Meeting meeting = new Meeting(1, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");

        String expected = "Приложение НетоБанка";
        String actual = meeting.getProject();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnStart() {
        Meeting meeting = new Meeting(1, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");

        String expected = "Во вторник после обеда";
        String actual = meeting.getStart();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMatch() {
        Meeting meeting = new Meeting(1, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");

        boolean result = meeting.matches("Выкатка");

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldNotFindMatch() {
        Meeting meeting = new Meeting(1, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");

        boolean result = meeting.matches("среда");

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnId() {
        Task task = new Task(1);
        int expectedId = 1;
        int actualId = task.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEqual() {
        Task task1 = new Task(5);
        Task task2 = new Task(5);

        boolean result = task1.equals(task2);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfObjectIsNull() {
        Task task = new Task(5);
        Object obj = null;

        boolean result = task.equals(obj);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfObjectsAreNotEqual() {
        Task task1 = new Task(5);
        Task task2 = new Task(15);

        boolean result = task1.equals(task2);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfSameObject() {
        Task task = new Task(5);

        boolean result = task.equals(task);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueIfTasksHaveSameHashCode() {
        Task task1 = new Task(5);
        Task task2 = new Task(5);

        int hashCode1 = task1.hashCode();
        int hashCode2 = task2.hashCode();

        Assertions.assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void shouldReturnFalseIfTasksHaveDifferentHashCode() {
        Task task1 = new Task(5);
        Task task2 = new Task(10);

        int hashCode1 = task1.hashCode();
        int hashCode2 = task2.hashCode();

        Assertions.assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void shouldReturnFalseForEmptyQuery() {
        Task task = new Task(5);

        boolean result = task.matches("");

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForNullObject() {
        Task task = new Task(5);

        boolean result = task.equals(null);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForDifferentClass() {
        Task task = new Task(5);
        String otherObject = "";

        boolean result = task.equals(otherObject);

        Assertions.assertFalse(result);
    }

}
