package pla.action;

import pla.Personnage;


public abstract class Action {
	private int id;
	
	
	protected abstract void executer(Personnage p, int delta);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
}
