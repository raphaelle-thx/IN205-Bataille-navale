package ensta.model.ship;

import ensta.util.ColorUtil;

public class ShipState {
	private AbstractShip ship;
	private Boolean struck;
	
	public ShipState() {
		struck = null;
	}
	
	public ShipState(AbstractShip ship){
		this.ship = ship;
		this.struck = false;
	}
	
	public void addStrike(){
		if (struck != null && struck != true){
			this.struck = true;
			ship.addStrike();
		}
	}
	
	public Boolean isStruck(){
		return struck;
	}
	
	public String toString(){
		if (struck == null){ return "."; }
		else if (struck == true){ return ColorUtil.colorize(Character.toString(ship.label), ColorUtil.Color.RED);}
		else{ return Character.toString(ship.label); }
	}
	
	public Boolean isSunk() {
		boolean sunk = ship.strikeCount == ship.getLength() ? true : false ;
		return sunk;
	}

	public AbstractShip getShip(){
		return ship;
	}
}
