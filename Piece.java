package chess;
import java.util.ArrayList;

public class Piece extends Chess {
	char currColor;
	int isWhite; //1 if white, 0 if black
	String move; ReturnPiece currPiece; ReturnPiece tarPiece;
	ArrayList<ReturnPiece> piecesList;
	
	int currFile = move.charAt(0) - '`'; int tarFile = move.charAt(3) - '`';
	//this will return integer value from 1 to 8 for file for a to h
	int currRank = move.charAt(1) - '0'; int tarRank = move.charAt(4) - '0';
	
	public Piece(ReturnPiece currPiece, String move) {
		this.currPiece = currPiece; this.move = move;
		currColor = currPiece.pieceType.toString().charAt(0);
		
		if (currPiece.pieceType.toString().charAt(0) == 'W') isWhite = 1;
		else isWhite = 0;
	}
	
	public Piece(ReturnPiece currPiece, String move, ArrayList<ReturnPiece> list) {
		this.currPiece = currPiece; this.move = move; this.piecesList = list;
		currColor = currPiece.pieceType.toString().charAt(0);
		
		if (currPiece.pieceType.toString().charAt(0) == 'W') isWhite = 1;
		else isWhite = 0;
	}
	
	public boolean isValidMove() {
		if (!this.isBlocked()) {
			return true;
		}
		return false; //placeholder method, to be overridden
	}
	
	public boolean isBlocked() { //default blocked check
		if (tarPiece != null && currColor == currPiece.pieceType.toString().charAt(0)) {
			return true;
		}
		return false;
	}
	
}
