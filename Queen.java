package chess;

import java.util.ArrayList;

public class Queen extends Piece{
	private ReturnPiece currPiece;
	private String Move;
	private ArrayList<ReturnPiece> piecesList;
	
	public Queen(ReturnPiece currPiece, String move, ArrayList<ReturnPiece> list) {
		super(currPiece, move, list);
	}
	
	public boolean isValidMove() {
		if (currPiece.pieceType != ReturnPiece.PieceType.WQ || currPiece.pieceType != ReturnPiece.PieceType.BQ) { // if queen
			return false;
		}
		
		if (!this.isBlocked()) {
			int fileDiff = tarFile - currFile;
			int rankDiff = tarRank - currRank;
			//Rook Check (N W S E)
			if (tarFile == currFile ^ tarRank == currRank) { //same file XOR same rank
				return true;
			}
			//Bishop Check (NE NW SW SE)
			else if (Math.abs(rankDiff) == Math.abs(fileDiff) && Math.abs(fileDiff) != 0) { //x=y, i.e. diagonal
				return true;
			}
		}
		return false;
		
		/*
		//*************************** Bishop
		int quadrant = 0;
		if (Math.abs((Move.charAt(1) - '0') - Move.charAt(4) - '0') == //row
			Math.abs(Move.charAt(0) - Move.charAt(3))) { //column
				if (Move.charAt(0) < Move.charAt(3) && Move.charAt(1) < Move.charAt(4)) quadrant = 1; //upper right
				if (Move.charAt(0) > Move.charAt(3) && Move.charAt(1) < Move.charAt(4)) quadrant = 2; //upper left
				if (Move.charAt(0) > Move.charAt(3) && Move.charAt(1) > Move.charAt(4)) quadrant = 3; //lower left
				if (Move.charAt(0) < Move.charAt(3) && Move.charAt(1) > Move.charAt(4)) quadrant = 4; //lower right
				bishopcheck = 1;
		}
		
		//******************************* Rook
		boolean isHorizontal = false;
		if ((currPiece.pieceFile.toString().charAt(0) != Move.charAt(3) && (Move.charAt(4)- '0') == currPiece.pieceRank) || //horizontal move
			(currPiece.pieceFile.toString().charAt(0) == Move.charAt(3) && (Move.charAt(4)- '0') != currPiece.pieceRank)) { //vertical move
				if (currPiece.pieceFile.toString().charAt(0) != Move.charAt(3) && (Move.charAt(4)- '0') == currPiece.pieceRank) isHorizontal = true;
				rookcheck = 1;
		}
		
		
		if (rookcheck == 1) {
			return isBlockedRook(isHorizontal);
		} else if (bishopcheck == 1) {
			return isBlockedBishop(quadrant);
		} else {
			return false;
		}*/
	}
	
	public boolean isBlocked() {
		int fileDiff = tarFile - currFile; //xDiff
		int rankDiff = tarRank - currRank; //yDiff
		for (int i = 0; i < piecesList.size(); i++) {
			ReturnPiece checkingPiece = piecesList.get(i);
			int checkingFile = checkingPiece.toString().charAt(0) - '`';
			int checkingRank = checkingPiece.toString().charAt(1) - '0';
			int checkingFileDiff = checkingFile - currFile;
			int checkingRankDiff = checkingRank - currRank;
			int checkingIsWhite;
			if (checkingPiece.toString().charAt(3) == 'W') checkingIsWhite = 1;
			else if (checkingPiece.toString().charAt(3) == 'B') checkingIsWhite = 0;
			
			//Rook Block Check
			if (checkingIsWhite == isWhite && Math.abs(checkingRankDiff) != Math.abs(checkingFileDiff)) {
				//^same color BUT xDiff != yDiff
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
			//Bishop Block Check
			else if (checkingIsWhite == isWhite && Math.abs(checkingRankDiff) == Math.abs(checkingFileDiff)) {
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
		return false;
	}
	
	/*public boolean isBlockedRook(boolean isHorizontal) {
		if (isHorizontal == true) { //is the target horizontal
			for (int i = 0; i < piecesList.size(); i++) {
				char currPieceFile = piecesList.get(i).pieceFile.toString().charAt(i);
				int currPieceRank = piecesList.get(i).pieceRank;
				
				if (piecesList.get(i).equals(currPiece) || //if the same as original chess,
					(currPieceFile == Move.charAt(3) && currPieceRank == Move.charAt(4))) { // or same as target chess skip this one
					continue;
				}
				
				
				if (Move.charAt(0) < Move.charAt(3) && Move.charAt(1) == Move.charAt(4)) { //check right side
					if (currPieceFile > Move.charAt(0) && currPieceFile < Move.charAt(3) && currPieceRank == Move.charAt(4)) { //if between
						return false;
					}
				}
				
				if (Move.charAt(0) > Move.charAt(3) && Move.charAt(1) == Move.charAt(4)) { //check left side
					if (currPieceFile < Move.charAt(0) && currPieceFile > Move.charAt(3) && currPieceRank == Move.charAt(4)) { //if between
						return false;
					}
				}
				
			}
		} else { //the target is vertical
			for (int i = 0; i < piecesList.size(); i++) {
				char currPieceFile = piecesList.get(i).pieceFile.toString().charAt(i);
				int currPieceRank = piecesList.get(i).pieceRank;
				
				if (piecesList.get(i).equals(currPiece) || //if the same as original chess,
					(currPieceFile == Move.charAt(3) && currPieceRank == Move.charAt(4))) { // or same as target chess skip this one
						continue;
					}
				
				if (Move.charAt(0) == Move.charAt(3) && Move.charAt(1) < Move.charAt(4)) { //check up side
					if (currPieceFile == Move.charAt(0) && currPieceRank < Move.charAt(4) && currPieceRank > Move.charAt(1)) { //if between
						return false;
					}
				}
				
				if (Move.charAt(0) == Move.charAt(3) && Move.charAt(1) > Move.charAt(4)) { //check down side
					if (currPieceFile == Move.charAt(0) && currPieceRank > Move.charAt(4) && currPieceRank > Move.charAt(2)) { //if between
						return false;
					}
				}
			}
		}
	}
	
	public boolean isBlockedBishop(int quadrant) {
		for (int i = 0; i < piecesList.size(); i++) {
			char currPieceFile = piecesList.get(i).pieceFile.toString().charAt(i);
			int currPieceRank = piecesList.get(i).pieceRank;
			
			if (quadrant == 1) {
				if ((Move.charAt(3) - currPieceFile) == (Move.charAt(4)-'0' - currPieceRank) && //if diagonal
					currPieceFile < Move.charAt(3) && currPieceRank < Move.charAt(4) -'0' && //target and original
					currPieceFile > Move.charAt(0) && currPieceRank > Move.charAt(1)- '0') { //if between
					return false;
				}
				
			} else if (quadrant == 2) {
				if (Math.abs((Move.charAt(3) - currPieceFile)) == (Move.charAt(4)-'0' - currPieceRank) && //if diagonal
						currPieceFile > Move.charAt(3) && currPieceRank < Move.charAt(4) -'0' &&
						currPieceFile < Move.charAt(0) && currPieceRank > Move.charAt(1)- '0') { //if between
						return false;
					}
				
			} else if (quadrant == 3) {
				if (Math.abs((Move.charAt(3) - currPieceFile)) == Math.abs((Move.charAt(4)-'0' - currPieceRank)) && //if diagonal
						currPieceFile > Move.charAt(3) && currPieceRank > Move.charAt(4) -'0' &&
						currPieceFile < Move.charAt(0) && currPieceRank < Move.charAt(1)- '0') { //if between
						return false;
					}
			
			} else if (quadrant == 4) {
				if ((Move.charAt(3) - currPieceFile) == Math.abs((Move.charAt(4)-'0' - currPieceRank)) && //if diagonal
						currPieceFile < Move.charAt(3) && currPieceRank > Move.charAt(4) -'0' &&
						currPieceFile > Move.charAt(0) && currPieceRank < Move.charAt(1)- '0') { //if between
						return false;
					}
				
			}
		}
		
		return true;
	}*/
}
