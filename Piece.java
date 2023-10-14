package chess;

public class Piece extends Chess {
	String move; ReturnPiece currPiece;
	
	char currFile = move.charAt(0); char tarFile = move.charAt(3);
	int currRank = move.charAt(1); int tarRank = move.charAt(4);
	
	public Piece(ReturnPiece currPiece, String move) {
		this.currPiece = currPiece; this.move = move; //this.isWhite = isWhite;
	}
	
	public boolean isValidMove() {
		return false; //placeholder method, to be overridden
	}
}
