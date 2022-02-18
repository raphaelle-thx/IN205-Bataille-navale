package ensta.tests;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.Orientation;

public class TestBoard {
	public static void main(String[] args) {
		/*Test exo 1*/
		Board testBoard = new Board("Player 1", 15);
		//testBoard.print();

		/*Test exo 2*/
		Submarine testSubmarine = new Submarine(Orientation.EAST);
        //System.out.println(testSubmarine.orientation);
        testSubmarine.setOrientation(Orientation.WEST);
        //System.out.println(testSubmarine.orientation);

		/*Test exo 3*/
        Coords coords = new Coords();
        
        // On essaie de placer un sous-marin en dehors de la grille ==> Erreur.
        coords.setX(15);
        coords.setY(15);
        testBoard.putShip(testSubmarine, coords);
        coords.setX(0);
        System.out.println(testBoard.hasShip(coords));

		Destroyer testDestroyer = new Destroyer(Orientation.NORTH);
		coords.setX(1);
        testBoard.putShip(testDestroyer, coords);
		testDestroyer.setOrientation(Orientation.EAST);
		coords.setY(1);
        testBoard.putShip(testDestroyer, coords);
		testDestroyer.setOrientation(Orientation.SOUTH);
		coords.setX(1);
        testBoard.putShip(testDestroyer, coords);
		testDestroyer.setOrientation(Orientation.WEST);
		coords.setX(15);
        coords.setY(15);
        testBoard.putShip(testDestroyer, coords);
		testDestroyer.setOrientation(Orientation.EAST);
		coords.setX(13);
		testBoard.putShip(testDestroyer, coords);
		Carrier	testCarrier = new Carrier(Orientation.WEST);
		coords.setX(10);
        coords.setY(10);
        testBoard.putShip(testCarrier, coords);
        coords.setX(2);
        coords.setY(10);
		testBoard.putShip(testCarrier, coords);
		testBoard.print();
	}
}
