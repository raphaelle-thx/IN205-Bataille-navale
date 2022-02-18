package ensta.model.ship;

import ensta.util.Orientation;

public class Battleship extends AbstractShip 
{
	public Battleship(Orientation orientation){ super('B', "Battleship", 4, orientation); }

	public Battleship(){ this(Orientation.EAST); }
} 
