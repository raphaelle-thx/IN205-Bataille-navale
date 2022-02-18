package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {
	
	public char label;
	public String name;
	public int length;
	public Orientation orientation;
	public int strikeCount;
		
	
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
		this.strikeCount=0;
	}
	
	public void addStrike(){
		strikeCount ++;
	}
	
	public boolean isSunk() {
		boolean sunk = strikeCount == length ? true : false ;
		return sunk;
	}

}
