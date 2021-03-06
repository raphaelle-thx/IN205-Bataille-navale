package ensta.tests;

import java.util.ArrayList;
import java.util.List;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.Orientation;

public class TestBoard {
	public static void main(String[] args) {
		/*Test exo 1*/
		/*
		Board testBoard = new Board("Player 1", 15);
		testBoard.print();
		*/
		
		/*Test exo 2*/
		/*
		Submarine testSubmarine = new Submarine(Orientation.EAST);
        System.out.println(testSubmarine.orientation);
        testSubmarine.setOrientation(Orientation.WEST);
        System.out.println(testSubmarine.orientation);
		*/
		
		/*Test exo 3*/
        Coords coords = new Coords();
        
        // On essaie de placer un sous-marin en dehors de la grille ==> Erreur.
        /*
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
        coords.setY(8);
        testBoard.putShip(testCarrier, coords);
        coords.setX(2);
        coords.setY(10);
		testBoard.putShip(testCarrier, coords);
		testBoard.print();
		*/
        
        /*Test exo 4*/
        /*
        Destroyer testDestroyer = new Destroyer(Orientation.NORTH);
        Carrier	testCarrier = new Carrier(Orientation.WEST);
        Board testEnnemyBoard = new Board("Test", 15);
		List<AbstractShip> ships = new ArrayList();
        Player testPlayer = new Player(testBoard,testEnnemyBoard, ships);
        */
        
        /*Test exo 5*/
        /*
		Board testBoard = new Board("Player 1", 15);
		Destroyer testDestroyer = new Destroyer(Orientation.NORTH);
		coords.setX(5);
        coords.setY(14);
        testBoard.putShip(testDestroyer, coords);
        coords.setX(3);
        coords.setY(3);
		testBoard.setHit(true, coords);
		coords.setX(5);
        coords.setY(5);
		testBoard.setHit(false, coords);
        testBoard.print();
        */
        
        /*Test exo 6*/
        
        Hit testHit;
        Board testBoard = new Board("Player 1", 15);
        Destroyer testDestroyer = new Destroyer(Orientation.EAST);
        coords.setX(10);
        coords.setY(5);
        testBoard.putShip(testDestroyer, coords);
        testBoard.print();
		testHit = testBoard.sendHit(coords);
		System.out.println(testHit.toString());
        testBoard.print();
        coords.setX(9);
        testHit = testBoard.sendHit(coords);
		System.out.println(testHit.toString()); // doit renvoyer "Manqu??"
		testBoard.print();
		coords.setX(11);
		testHit = testBoard.sendHit(coords); // doit retourner la valeur TYPE_DU_NAVIRE
       	System.out.println(testHit.toString());
		testBoard.print();

        System.out.println("Navire coul??  ? ==> " + testDestroyer.isSunk());
        System.out.println("Derni??re valeur renvoy??e ? ==> " + testHit.toString());
        
        
	}
}
