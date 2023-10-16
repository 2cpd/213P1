package chess;

import java.util.ArrayList;

public class Rook extends Piece{	
	public Rook(ReturnPiece currPiece, String move, ArrayList<ReturnPiece> list) {
		super(currPiece, move, list);
	}
	
	public boolean isValidMove() {
		if (currPiece.pieceType != ReturnPiece.PieceType.WR || currPiece.pieceType != ReturnPiece.PieceType.BR) { // if rook
			return false;
		}
		
		if (!this.isBlocked()) {
			if (tarFile == currFile ^ tarRank == currRank) { //same file XOR same rank
				return true;
			}
		}
		return false;
	}
	
	public boolean isBlocked() {
		//do four probes in the 4 directions
		for (int i = 0; i < piecesList.size(); i++) {
			ReturnPiece checkingPiece = piecesList.get(i);
			int checkingFile = checkingPiece.toString().charAt(0) - '`'; //numerical value of current file/rank checked
			int checkingRank = checkingPiece.toString().charAt(1) - '0';
			int checkingIsWhite;
			if (checkingPiece.toString().charAt(3) == 'W') checkingIsWhite = 1;
			else if (checkingPiece.toString().charAt(3) == 'B') checkingIsWhite = 0;
			
			if (checkingIsWhite == isWhite) {
				if (checkingRank == currRank && checkingFile != currFile) { //horiz
					if (tarRank > currRank && checkingFile < tarFile) { //block right
						return true;
					}
					else if (tarRank < currRank && checkingFile > tarFile) { //left
						return true;
					}
				}
				else if (checkingFile == currFile && checkingRank != currRank) { //vert
					if (tarRank > currRank && checkingRank < tarRank) { //up
						return true;
					}
					else if (tarRank < currRank && checkingRank > tarRank) { //down
						return true;
					}
				}
			}
		}
		return false; //if no blockage detected
	}
}
