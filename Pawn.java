package chess;

public class Pawn extends Piece {
	//isCapturable Vars
	char tarColor = tarPiece.pieceType.toString().charAt(0);
	
	//enPassant
	boolean hasMovedTwo = false; int moveCount;
	
	public Pawn(ReturnPiece currPiece, String move, int moveCount) {
		super(currPiece,move);
		this.moveCount = moveCount;
	}
	
	public boolean isValidMove() {
		//!! CONSIDER OUT OF BOUNDS
		if (currPiece.pieceType != ReturnPiece.PieceType.WP || currPiece.pieceType != ReturnPiece.PieceType.BP) {
			return false;
		}
		
		if (!this.isBlocked() && tarFile == currFile) { //target cell is empty & in same column(file)
			//white
			if (isWhite == 1) {
				if (currRank == 2 && tarRank == currRank + 2) { //first move: can move 2
					hasMovedTwo = true;
					return true;
				}
				if (tarRank == currRank + 1) { //regular rule
					hasMovedTwo = false;
					return true;
				}
			}
			//black
			if (isWhite == 0) {
				if (currRank == 7 && tarRank == currRank - 2) {
					hasMovedTwo = true;
					return true;
				}
				if (tarRank == currRank - 1) {
					hasMovedTwo = false;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCapture() {
		//!! CONSIDER OUT OF BOUNDS
		if (this.isBlocked() && currColor != tarColor) { //target is occupied & isn't friendly
			//white
			if (isWhite == 1) {
				if (tarRank == currRank + 1) {
					if (tarFile == currFile + 1 || tarFile == currFile - 1)
						return true;
				}
			}
			//black
			if (isWhite == 0) {
				if (tarRank == currRank - 1) {
					if (tarFile == currFile + 1 || tarFile == currFile - 1)
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean isEnPassant() {
		//capturing pawn moved 3 ranks (5/4); enemy pawn moved two squares
		//en passant is possible if: 
		//1) tarPiece is an enemy pawn; OK
		//2) currPiece is on rank 5 or 4; OK
		//3) tarPiece has just moved 2 ranks last turn to rank 5 or 4
		//In that case: move front left/right
		//target tile (diagnoal 1) should be empty
		//captured tile is also occupied by a pawn, and that pawn is not friendly
		//white
		ReturnPiece adjPiece1, adjPiece2;
		if (!this.isBlocked()) {
			//white
			if (isWhite == 1) {
				if (currRank == 5 && tarPiece.pieceType.toString() == "BP") { //implement moves check
					if (tarFile == currFile + 1 || tarFile == currFile - 1)
						return true;
				}
			}
			//black
			if (isWhite == 0) {
				if (currRank == 4 && tarPiece.pieceType.toString() == "WP") {
					if (tarFile == currFile + 1 || tarFile == currFile - 1)
						return true;
				}
			}
		}
		return false;
	}
}
