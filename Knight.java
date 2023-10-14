package chess;

public class Knight extends Piece {
	/*
	 * If +1/-1
	 * A-c B-d a-C-e b-D-f c-E-g d-F-h e-G f-H
	 * 
	 * If +2/-2
	 * A-b a-B-c b-C-d c-D-e d-E-f e-F-g f-G-h g-H
	 */
	//private boolean isWhite; 
	
	//Inherited from Piece superclass:
	/*String move; ReturnPiece currPiece;
	char currFile = move.charAt(0); char tarFile = move.charAt(3);
	int currRank = move.charAt(1); int tarRank = move.charAt(4);*/
	char validFile1, validFile2;
	
	public Knight(ReturnPiece currPiece, String move) {
		super(currPiece,move); 
	}
	
	public boolean isValidMove() {
		if (tarRank == currRank + 1 || tarRank == currRank - 1) {
			switch(currFile) {
			case 'a': validFile1 = 'c'; validFile2 = 'c';
			case 'b': validFile1 = 'd'; validFile2 = 'd';
			case 'c': validFile1 = 'a'; validFile2 = 'e';
			case 'd': validFile1 = 'b'; validFile2 = 'f';
			case 'e': validFile1 = 'c'; validFile2 = 'g';
			case 'f': validFile1 = 'd'; validFile2 = 'h';
			case 'g': validFile1 = 'e'; validFile2 = 'e';
			case 'h': validFile1 = 'f'; validFile2 = 'f';
			}
			if (tarFile == validFile1 || tarFile == validFile2) {
				return true;
			}
		}
		else if (tarRank == currRank + 2 || tarRank == currRank - 2) {
			switch(currFile) {
			case 'a': validFile1 = 'b'; validFile2 = 'b';
			case 'b': validFile1 = 'a'; validFile2 = 'c';
			case 'c': validFile1 = 'b'; validFile2 = 'd';
			case 'd': validFile1 = 'c'; validFile2 = 'e';
			case 'e': validFile1 = 'd'; validFile2 = 'f';
			case 'f': validFile1 = 'e'; validFile2 = 'g';
			case 'g': validFile1 = 'f'; validFile2 = 'h';
			case 'h': validFile1 = 'g'; validFile2 = 'g';
			}
			if (tarFile == validFile1 || tarFile == validFile2) {
				return true;
			}
		}
		/*else {
			validFile1 = 'z'; validFile2 = 'z'; //indicates no validFile; why z? bc i said so
		}*/
		return false;
	}
}
