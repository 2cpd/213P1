package chess;

import java.lang.Math;
import java.util.ArrayList;

public class King extends Piece{
	
	public King(ReturnPiece currPiece, String move, ArrayList<ReturnPiece> list) {
		super(currPiece, move, list);
	}
	
	public boolean isValidMove() {

		if (currPiece.pieceType != ReturnPiece.PieceType.WK || currPiece.pieceType != ReturnPiece.PieceType.BK) { // if king
			return false;
		}
		
		if (!this.isBlocked()) {
			int fileDiff = Math.abs(tarFile - currFile);
			int rankDiff = Math.abs(tarRank - currRank);
			
			if (fileDiff == 1 || rankDiff == 1) {
				if (fileDiff*rankDiff == 1 || fileDiff*rankDiff == 0) return true;
			}
		}
		return false;		
	}
}

