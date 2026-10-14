import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TaskService
{
	private final HashMap<Integer , Task> tasks;
	private int nextId = 1;

	public TaskService()
	{
		this.tasks = new HashMap<>();
	}
	public Task createTask(String title)
	{
		Task task = new Task(nextId, title);
		tasks.put(nextId, task);
		nextId++;
		return task;
	}
	
	public Task getTaskById(int ID)
	{
		Task task= tasks.get(ID);
		if (task == null)
		{
			throw new TaskNotFoundException("Task not found for ID "+ ID);
		}
		return task;
	}

	public void updateStatus(int ID, Task.Status newStatus)
	{
		Task task = getTaskById(ID);
		
		switch (newStatus)
		{
			case PENDING:
				task.reOpen();
				break;

			case ONGOING:
				task.ongoing();
				break;

			case COMPLETED:
				task.complete();
				break;
		}

	}




	public void deleteTask(int ID)
	{
		Task task = getTaskById(ID);
		tasks.remove(ID);
	}
	
	public List<Task> getAllTasks()
	{
		return new ArrayList<>(tasks.values()); 
	}

}