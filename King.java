package chess;

import java.lang.Math;
import java.util.ArrayList;

public class King extends Piece{
	boolean isCastle = false;
	
	public King(ReturnPiece currPiece, String move, ArrayList<ReturnPiece> list) {
		super(currPiece, move, list); 
	}
	
	public boolean isValidMove() {

		if (currPiece.pieceType != ReturnPiece.PieceType.WK || currPiece.pieceType != ReturnPiece.PieceType.BK) { // if king
			return false;
		}
		
		if (!this.isBlocked()) {
			int fileDiff = Math.abs(tarFile - currFile); int rankDiff = Math.abs(tarRank - currRank);
			if (rankDiff == 0 && fileDiff == 2) {
				isCastle = true;
				return true;
			}
			
			if (fileDiff == 1 || rankDiff == 1) {
				if (fileDiff*rankDiff == 1 || fileDiff*rankDiff == 0)
					isCastle = false;
					return true;
			}
		}
		return false;		
	}
	
	public boolean isInCheck() {
		//Horizontally: R/Q
		//Diagonally
		ReturnPiece checkingPiece;
		return true;
	}
}

