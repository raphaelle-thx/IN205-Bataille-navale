package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private String name;
	private int size;
	private Boolean[][] hits;
	private Character[][] ship;
	

	public Board(String name, int size){
		this.name = name;
		this.size = size;
		this.hits = new Boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ship[i][j] = '.';
				hits[i][j] = false;
			}
		}
		this.ship = new Character[size][size];
	}
	
	public Board(String name) {
		this(name, 10);
	}


	public void printspaces(int n){
		for (int i = 0 ; i < n ; i++){
			System.out.print(" ");
		}
	}

	public void init_display(){
		System.out.println(" ");
		printspaces(2 * getSize() + 6 - this.name.length()/2 + 2);
		System.out.println(this.name);
		System.out.println(" ");
		
		printspaces(getSize() + 3);
		System.out.print("SHIPS");
		printspaces(2 * getSize());
		System.out.print("HITS");
		for (int i = 0 ; i < 2 ; i++){
			System.out.println(" ");
		}	
		dotted_line();
	}

	public void letter_display(){
		char ch = 'A';
		for (int i = 0 ; i < getSize() ; i ++){
			System.out.print(ch);
			ch += 1;
			printspaces(1);
		}
	}

	public void global_letters_display(){
		printspaces(5);
		letter_display();
		printspaces(6);
		letter_display();
		System.out.println(" ");
	}

	public void dotted_line(){
		for (int k = 0 ; k < 4 * getSize() + 14 ; k ++){
			System.out.print("-");
		}
		System.out.println(" ");
	}

	public void first_numbers_display(int i){
		if (i < 9) {
			printspaces(1);
		}
		System.out.print(i+1);
		printspaces(1);
		System.out.print("|");
		printspaces(1);
	}

	public void ships_display(int i, int j){
		System.out.print(this.ship[i][j]);
		printspaces(1);
	}

	public void hits_display(int i, int j){
		if (this.hits[i][j]) {
			System.out.print("x");
			printspaces(1);
		}
		else {
			System.out.print(".");
			printspaces(1);
		}
	}

	public void end_numbers_display(int i){
		System.out.print("|");
		if (i < 9) {
			printspaces(1);
		}
		System.out.print(i+1);
		printspaces(1);
		System.out.println(" ");
	}

	public void affichage_grilles(){
			for (int i = 0 ; i < getSize() ; i++){
				first_numbers_display(i);
				
				for (int j = 0; j < getSize() ; j++) { ships_display(i,j); }
				
				System.out.print(" ");
				System.out.print(" || ");
				System.out.print(" ");
				
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
		for (int i = 0 ; i < 2 ; i++){
			System.out.println(" ");
		}	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Character[][] getMyGrid() {
		return ship;
	}
	
	public Boolean[][] getEnemyGrid() {
		return hits;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean putShip(AbstractShip ship, Coords coords) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasShip(Coords coords) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getHit(Coords coords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hit sendHit(Coords res) {
		// TODO Auto-generated method stub
		return null;
	}
}
