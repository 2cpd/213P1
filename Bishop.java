package chess;
import java.lang.Math;
import java.util.ArrayList;

public class Bishop extends Piece{
	
	public Bishop(ReturnPiece currPiece, String move, ArrayList<ReturnPiece> list) {
		super(currPiece, move, list);
	}
	
	public boolean isValidMove() {
		if (currPiece.pieceType != ReturnPiece.PieceType.WB || currPiece.pieceType != ReturnPiece.PieceType.BB) { // if bishop
			return false;
		}
		
		if (!this.isBlocked()) {
			int fileDiff = tarFile - currFile;
			int rankDiff = tarRank - currRank;
			
			if (Math.abs(rankDiff) == Math.abs(fileDiff) && Math.abs(fileDiff) != 0) { //x=y, i.e. diagonal
				return true;
			}
		}
		
		//int quadrant = 0;
		/*if (Math.abs((currRank) - tarRank) == //row
			Math.abs((currFile) - (tarFile))) { //column
				if (currFile < tarFile && currRank < tarRank) quadrant = 1; //upper right
				if (currFile > tarFile && currRank < tarRank) quadrant = 2; //upper left
				if (currFile > tarFile && currRank > tarRank) quadrant = 3; //lower left
				if (currFile < tarFile && currRank > tarRank) quadrant = 4; //lower right
				//route is correct
				return isBlocked(quadrant);
		}*/
		return false;
	}
	
	public boolean isBlocked(int quadrant) {
		int fileDiff = tarFile - currFile; //xDiff
		int rankDiff = tarRank - currRank; //yDiff
		for (int i = 0; i < piecesList.size(); i++) {
			ReturnPiece checkingPiece = piecesList.get(i);
			int checkingFile = checkingPiece.toString().charAt(0) - '`'; //numerical value of current file/rank checked
			int checkingRank = checkingPiece.toString().charAt(1) - '0';
			int checkingFileDiff = checkingFile - currFile;
			int checkingRankDiff = checkingRank - currRank;
			int checkingIsWhite;
			if (checkingPiece.toString().charAt(3) == 'W') checkingIsWhite = 1;
			else if (checkingPiece.toString().charAt(3) == 'B') checkingIsWhite = 0;
			
			if (checkingIsWhite == isWhite && Math.abs(checkingRankDiff) == Math.abs(checkingFileDiff)) {
				if (Math.abs(rankDiff) == Math.abs(fileDiff) && Math.abs(fileDiff) != 0) {
					if (fileDiff > 0 && rankDiff > 0 && checkingFileDiff > 0 && checkingRankDiff > 0) { //Q1, for both tar-curr and checking-curr
						if (checkingFileDiff < fileDiff && checkingRankDiff < rankDiff)
							return true;
					}
					else if (fileDiff < 0 && rankDiff > 0 && checkingFileDiff < 0 && checkingRankDiff > 0) { //Q2
						if (checkingFileDiff > fileDiff && checkingRankDiff < rankDiff)
							return true;
					}
					else if (fileDiff < 0 && rankDiff < 0 && checkingFileDiff < 0 && checkingRankDiff < 0) { //Q3
						if (checkingFileDiff > fileDiff && checkingRankDiff > rankDiff)
							return true;
					}
					else if (fileDiff > 0 && rankDiff < 0 && checkingFileDiff > 0 && checkingRankDiff < 0) { //Q4
						if (checkingFileDiff < fileDiff && checkingRankDiff > rankDiff)
							return true;
					}
				}
				
			}
		}
			
			/*if (quadrant == 1) {
				if ((tarFile - checkingFile) == (tarRank - checkingRank) && //if diagonal
					checkingFile < tarFile && checkingRank < tarRank && //target and original
					checkingFile > currFile && checkingRank > currRank) { //if between
					return false;
				}
				
			} else if (quadrant == 2) {
				if (Math.abs((tarFile - checkingFile)) == (tarRank - checkingRank) && //if diagonal
						checkingFile > tarFile && checkingRank < tarRank &&
						checkingFile < currFile && checkingRank > currRank) { //if between
						return false;
					}
				
			} else if (quadrant == 3) {
				if (Math.abs((tarFile - checkingFile)) == Math.abs((tarRank - checkingRank)) && //if diagonal
						checkingFile > tarFile && checkingRank > tarRank &&
						checkingFile < currFile && checkingRank < currRank) { //if between
						return false;
					}
			
			} else if (quadrant == 4) {
				if ((tarFile - checkingFile) == Math.abs((tarRank - checkingRank)) && //if diagonal
						checkingFile < tarFile && checkingRank > tarRank &&
						checkingFile > currFile && checkingRank < currRank) { //if between
						return false;
					}
				
			}*/
		return false;
	}
}
