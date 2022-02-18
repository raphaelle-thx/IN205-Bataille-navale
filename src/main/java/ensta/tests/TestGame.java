package ensta.tests;

import java.util.Random;

import ensta.ai.BattleShipsAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.IBoard;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.Battleship;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;

public class TestGame {
	private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IBoard board = new Board("Player 1", 15);
        board.print();
    	int counter = 5;
    	Hit testHit;
        Random rnd = new Random();
        AbstractShip[] ships = {
                new Destroyer(),
                new Battleship(),
                new Carrier(), new Submarine(),
                new Submarine() };
        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships);
        while (counter != 0)
    	{

    		Coords coords = new Coords();
    		testHit =ai.sendHit(coords);
    		System.out.println(testHit.toString());
    		board.print();

    		if (testHit.getValue() > 0)
    			counter--;

    		sleep(100);
    	}
    }
}