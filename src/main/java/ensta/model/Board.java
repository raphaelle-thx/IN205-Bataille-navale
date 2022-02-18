package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.ColorUtil;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private String name;
	private int size;
	private Boolean[][] hits;
	//private Character[][] ship;
	private ShipState[][] ship;
	

	public Board(String name, int size){
		this.name = name;
		this.size = size;
		this.hits = new Boolean[size][size];
		//this.ship = new Character[size][size];
		this.ship = new ShipState[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ship[i][j] = new ShipState();
				hits[i][j] = null;
			}
		}
	}
	
	public Board(String name) {
		this(name, 10);
	}

	public void jumpLine(int n){
		System.out.print(new String(new char[n]).replace("\0", "\n"));
	}

	public void printSpaces(int n){
		System.out.print(new String(new char[n]).replace("\0", " "));
	}

	public void init_display(){
		jumpLine(1);
		printSpaces(2 * getSize() + 6 - this.name.length()/2 + 2);
		System.out.println(this.name);
		jumpLine(1);
		
		printSpaces(getSize() + 3);
		System.out.print("SHIPS");
		printSpaces(2 * getSize());
		System.out.print("HITS");
		jumpLine(2);
		dotted_line();
	}

	public void letter_display(){
		char ch = 'A';
		for (int i = 0 ; i < getSize() ; i ++){
			System.out.print(ch);
			ch += 1;
			printSpaces(1);
		}
	}

	public void global_letters_display(){
		printSpaces(5);
		letter_display();
		printSpaces(6);
		letter_display();
		jumpLine(1);
	}

	public void dotted_line(){
		for (int k = 0 ; k < 4 * getSize() + 14 ; k ++){
			System.out.print("-");
		}
		jumpLine(1);
	}

	public void first_numbers_display(int i){
		if (i < 9) {
			printSpaces(1);
		}
		System.out.print(i+1);
		printSpaces(1);
		System.out.print("|");
		printSpaces(1);
	}

	public void ships_display(int i, int j){
		//System.out.print(this.ship[i][j]);
		//printSpaces(1);
		if (this.ship[i][j] == null){
			System.out.print(".");
			printSpaces(1);
		}
   		else { 
			System.out.print(this.ship[i][j].toString());
			printSpaces(1);
		}
	}

	public void hits_display(int i, int j){
		if (this.hits[i][j]==null) {
			System.out.print(".");
			printSpaces(1);
		}
		else if(this.hits[i][j] == true){
			System.out.print(ColorUtil.colorize("X", ColorUtil.Color.RED));
			printSpaces(1);
		}
		else {
			System.out.print ("X ");	    
		}
	}

	public void end_numbers_display(int i){
		System.out.print("|");
		if (i < 9) {
			printSpaces(1);
		}
		System.out.print(i+1);
		printSpaces(1);
		jumpLine(1);
	}

	public void affichage_grilles(){
			for (int i = 0 ; i < getSize() ; i++){
				first_numbers_display(i);
				
				for (int j = 0; j < getSize() ; j++) { ships_display(i,j); }
				
				printSpaces(1);
				System.out.print(" || ");
				printSpaces(1);
				
				for (int j = 0; j < getSize() ; j++) { hits_display(i,j);}
				
				end_numbers_display(i);
			}
	}

	public void print() {
		init_display();
		global_letters_display();
		dotted_line();
		affichage_grilles();
		dotted_line();
		jumpLine(2);
	}
	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}
	
	/*public Character[][] getMyGrid() {
		return ship;
	}*/
	public ShipState[][] getMyGrid() {
		return ship;
	}
	
	public Boolean[][] getEnemyGrid() {
		return hits;
	}

	public int getSize() {
		return this.size;
	}
	
	public ShipState[][] getShip() {
		return ship;
	}


	public void putShip(AbstractShip ship, Coords coords) {
		//Character[][] saveShips = saveShips();
		ShipState[][] saveShips = saveShips();
		Orientation o = ship.getOrientation();
		ship.initialized = true;
		int no = 0 , ea = 0 , so = 0, we = 0;
		if (o == Orientation.NORTH){ no = 1;}
		if (o == Orientation.EAST){ ea = 1;}
		if (o == Orientation.SOUTH){ so = 1;}
		if (o == Orientation.WEST){ we = 1;}
		try
		{
			if (no+so == 1){
				if (coords.getY() - 1 + (so-no) * ship.length > this.size || coords.getY() - 1 + (so-no) * ship.length < 0){
					throw new Exception("y coords too big or too small");
				}
				for (int i = 0; i < ship.length; i++){
					if (this.ship[coords.getY() - 1 + (so-no) * i][coords.getX() - 1 ].isStruck() != null)
					{
						throw new IllegalArgumentException ("A ship is already placed here: " + ship.name.toString() + " not placed.");
					}
					this.ship[coords.getY() - 1 + (so-no) * i][coords.getX() - 1 ] = new ShipState(ship);
				}
			}
			if (ea+we == 1){
				if (coords.getX() - 1 + (ea-we) * ship.length > this.size || coords.getX() - 1 + (ea-we) * ship.length < 0){
					throw new Exception("y coords too big or too small: " + ship.name.toString() + " not placed.");
				}
				for (int j = 0; j < ship.length; j++){
					if (this.ship[coords.getY() - 1][coords.getX() - 1 + (ea-we) * j].isStruck() != null)
					{
						throw new IllegalArgumentException ("A ship is already placed here: " + ship.name.toString() + " not placed.");
					}
					this.ship[coords.getY() - 1][coords.getX() - 1 + (ea-we) * j] = new ShipState(ship);
				}
			}
		}

		catch (IllegalArgumentException e)
		{
			ship.initialized = false;
			this.ship = saveShips;
			jumpLine(1);
			System.out.println("Problème de type : " + e.toString());
		}
		catch (Exception e)
		{
			ship.initialized = false;
			this.ship = saveShips;
			jumpLine(1);
			System.out.println("Problème d'indice de type : " + e.toString());
		}
	}

	/*private Character[][] saveShips() {
		Character[][] saveShips = new Character[this.size][this.size];
		for (int i = 0; i < this.size; i++)
		{
			for (int j = 0; j< this.size; j++)
			{
				saveShips[i][j] = this.ship[i][j];
			}
		}
		return saveShips;
	}*/
	
	public ShipState[][] saveShips() {
		ShipState[][] saveShips = new ShipState[this.size][this.size];
		for (int i = 0; i < this.size; i++){
			for (int j = 0; j< this.size; j++){
				if (this.ship[i][j].isStruck()==null){
					saveShips[i][j]=new ShipState();
				}
				else {
					saveShips[i][j]=this.ship[i][j];
				}
			}
		}
		return saveShips;
	}

	public boolean hasShip(Coords coords) {
		try
		{
		if (this.ship[coords.getY()-1][coords.getX()-1].isStruck() == null || this.ship[coords.getY()-1][coords.getX()-1].getShip().isSunk())
			return false;
		else
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Problème d'indice de type : " + e.toString() );
			System.out.println("Réponse à l'appel hasShip("+coords.getX()+","+coords.getY()+") impossible, false renvoyé par défaut\n");
			return false;
		}
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		try{
		this.hits[coords.getY()-1][coords.getX()-1] = hit;
		}
		catch (Exception e)
		{
			System.out.println("Problème d'indice de type : " + e.toString() );
			System.out.println("Frappe annulée\n");
		}		
	}

	public Boolean getHit(Coords coords) {
		try{
			return this.hits[coords.getY()][coords.getX()];
		}
		catch (Exception e)
		{
			System.out.println("Problème d'indice de type : " + e.toString() );
			System.out.println("Réponse à l'appel getHit("+coords.getX()+","+coords.getY()+") impossible, false renvoyé par défaut\n");
			return false;
		}
	}

	@Override
	public Hit sendHit(Coords res) {
		Hit hit;
		if (ship[res.getY()-1][res.getX()-1].isStruck() == null || ship[res.getY()-1][res.getX()-1].isStruck() == true) {
			//Ship already down
			return Hit.MISS;
		}
		else {
			ship[res.getY()-1][res.getX()-1].addStrike();
			if (ship[res.getY()-1][res.getX()-1].getShip().isSunk()){
				hit = Hit.fromInt(ship[res.getY()-1][res.getX()-1].getShip().length);
				System.out.println(hit.toString()+" ==> coulé\n");
				return hit;
			}
			else{ return Hit.STRIKE; }
		}
	}
}
