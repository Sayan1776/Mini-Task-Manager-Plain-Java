class Task
{
	private final int ID;
	private final String title ;
	private Status status;

	public enum Status
	{
		PENDING,
		ONGOING,
		COMPLETED
	}
	
	public Task(int ID, String title)
	{
		this.ID = ID;
		this.title = title;
		this.status = Status.PENDING;
	}
	public int getID()
	{
		return ID;
	}
	public String gettitle()
	{
		return title;
	}
	public Status getstatus()
	{
		return status;
	}
	
	public void complete()
	{
		this.status=Status.COMPLETED;
	}

	public void ongoing()
	{
		this.status=Status.ONGOING;
	}
	
	public void reOpen()
	{
		this.status=Status.PENDING;
	}

}