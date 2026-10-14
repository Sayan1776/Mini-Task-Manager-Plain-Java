import java.util.List;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		TaskService taskService = new TaskService();
		Scanner sc = new Scanner(System.in);
		int choice;
		do
		{
			System.out.println("Welcome to your Task Manager");
			System.out.println("===== TASK MANAGER =====");
			System.out.println("1. Create task");
			System.out.println("2. List all tasks");
			System.out.println("3. Find task by ID");
			System.out.println("4. Update task status");
			System.out.println("5. Delete task");
			System.out.println("0. Exit");
			System.out.println("");

			System.out.print("Choose task operation(0 to 5): ");
			choice = sc.nextInt();
			sc.nextLine();
			System.out.println("");
			
			switch(choice)
			{
				case 1:

					System.out.println("Create task");
					System.out.println("==================================");
					System.out.println("");

					System.out.print("Enter task title: ");
					String title = sc.nextLine();
					System.out.println("");

					Task createdTask = taskService.createTask(title);

					System.out.println("New task created");
					System.out.println("ID: "+ createdTask.getID() + " task: "+ createdTask.gettitle() + " Status: "+ createdTask.getstatus());
					System.out.println("");
					
					break;

				case 2:
					System.out.println(" List all tasks");
					System.out.println("==================================");
					System.out.println("");


					List<Task> allTasks = taskService.getAllTasks();

					if (allTasks.isEmpty())
					{
						System.out.println("No tasks found");
						System.out.println("");
					}
					else
					{
						for (Task task : allTasks)
						{
							System.out.println(task.getID() + ": " + task.gettitle() + "	|	" + task.getstatus());
							System.out.println("");
						}
					}

					break;

				case 3:
					System.out.println(" Find task by ID");
					System.out.println("==================================");
					System.out.println("");
					
					System.out.print("Enter the ID for the task to be lookedup: ");
					int id = sc.nextInt();
					System.out.println("");
					
					try
					{

						Task lookupTask = taskService.getTaskById(id);
						System.out.println("ID: "+lookupTask.getID() +" task: " + lookupTask.gettitle() + " Status: " + lookupTask.getstatus());
						System.out.println("");

					}catch (TaskNotFoundException e)
					{

						System.out.println(e.getMessage());
						System.out.println("");

					}

					break;

				case 4:
					System.out.println("Status Update");
					System.out.println("==================================");
					System.out.println("");
					
					System.out.print("Enter the ID for the task to be updated: ");
					int taskIdToUpdate  = sc.nextInt();
					sc.nextLine();
					try
					{
						Task newStateTask = taskService.getTaskById(taskIdToUpdate);
						System.out.println("===== UPDATE TASK STATUS =====");
						System.out.println("");
						System.out.println("1. PENDING");
						System.out.println("2. ONGOING");
						System.out.println("3. COMPLETED");
					
						System.out.print("Choose new status: ");
						int statusChoice = sc.nextInt();
						System.out.println("");
					
						Task.Status newStatus;

						switch(statusChoice)
						{
							case 1:
								newStatus = Task.Status.PENDING;
							
								break;

							case 2:
								newStatus = Task.Status.ONGOING;
							
								break;

							case 3:
								newStatus = Task.Status.COMPLETED;
							
								break;
							default:
								System.out.println("Invalid choice!!");
								continue;
						}

						taskService.updateStatus(taskIdToUpdate, newStatus);
					
						System.out.println("Status Updated...........");
						System.out.println("");
						System.out.println("Current status: " + newStateTask.getstatus());
						System.out.println("");
					}catch(TaskNotFoundException e)
					{

						System.out.println(e.getMessage());
						System.out.println("");

					}
					

					break;
				case 5:
					System.out.println(" Delete Task by ID");
					System.out.println("==================================");
					System.out.println("");

					System.out.print("Enter the ID for the task to be deleted: ");
					int taskIdToDelete  = sc.nextInt();
					sc.nextLine();

					try
					{
						taskService.deleteTask(taskIdToDelete);
						System.out.println("Deletion Succesful");
					
					}catch(TaskNotFoundException e)
					{
						
						System.out.println(e.getMessage());
						System.out.println("");
						
					}
					
					break;
				case 0:
					System.out.println("Exiting Task Manager...");
					break;
				default:
					System.out.println("Invalid choice. Choose between 0 to 5");

			}

		}while(choice!=0);
		sc.close();
	}
}