package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {
	
	private char label;
	private String name;
	private int length;
	private Orientation orientation;
		
	
	public int getLabel() {
		return this.label;
	}
	
	public int getLength() {
		return this.length;
	}

	public Object getName() {
		return this.name;
	}

	public Orientation getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(Orientation orientation) {this.orientation = orientation; }
	
	public AbstractShip(char label, String name, int length, Orientation orientation){
		this.label = label;
		this.name = name;
		this.length = length;
		this.orientation = orientation;
	}
	public boolean isSunk() {
		// TODO Auto-generated method stub
		return false;
	}

}
