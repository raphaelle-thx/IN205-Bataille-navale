package ensta.tests;

import ensta.model.Board;

public class TestBoard {
	public static void main(String[] args) {
		Board b1 = new Board("Player 1",15);
		Board b2 = new Board("Player 2");
		b1.print();
		b2.print();
	}
}
