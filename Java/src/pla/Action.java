package pla;


public abstract class Action {
	private int id;
	
	
	protected abstract void executer();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
