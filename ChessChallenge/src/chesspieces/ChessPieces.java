package chesspieces;

import java.util.HashSet;

public class ChessPieces
{
	private static int size;
	private static long uniqueSetCount = 0;
	private static String chesspieces;
	private static HashSet<String> solutions = new HashSet<String>();
	private static HashSet<String> uniquePermutations = new HashSet<String>();

	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		chesspieces = "QQKKBBN";
		size = 7;
		permutation(chesspieces);
		int counter = 0;
		for (String permutation : uniquePermutations)
		{
			counter++;
			chesspieces = permutation;
			calculatePositions(0, 0, 0, new Boolean[size][size]);
		}

		long endTime = System.currentTimeMillis();
		System.out.printf("[solution count] = %s %n", solutions.size());
		System.out.printf("[time] = %.3f sec", (endTime - startTime) / 1000D);
	}
	
	static void calculatePositions(int row, int column, int chessPieceIndex, Boolean[][] board)
	{
		char chesspiece;
		boolean validation = false, isMarked;
		Boolean[][] chessboard = new Boolean[board.length][];
		Utils.copyChessboard(board, chessboard);
		
		if ((row < size || column < size) && chessPieceIndex == size) chessPieceIndex--;
		if (column == chessboard.length) row++;

		for (; row < chessboard.length; row++)
		{
			if (column == chessboard.length) column = 0;
			for (; column < chessboard.length; column++)
			{
				if (chessboard[row][column] == null && 
					chessPieceIndex < chesspieces.length())
				{
					isMarked = false;
					chesspiece = chesspieces.charAt(chessPieceIndex);
					switch (chesspiece)
					{
						case 'Q':
							if (!Utils.checkQueenFields(row, column, chessboard, chesspiece)) break;
							
							calculatePositions(row, (validation ? column : column + 1), chessPieceIndex, chessboard);
							
							if (Utils.markQueenFields(row, column, chessboard, chesspiece)) 
							{
								isMarked = true;
								chessPieceIndex++;
							}
							break;
						case 'K':
							if (!Utils.checkKingFields(row, column, chessboard)) break;
							
							calculatePositions(row, (validation ? column : column + 1), chessPieceIndex, chessboard);
							
							if (Utils.markKingFields(row, column, chessboard)) 
							{
								isMarked = true;
								chessPieceIndex++;
							}
							break;
						case 'B':
							if (!Utils.checkBishopFields(row, column, chessboard, chesspiece)) break;
							
							calculatePositions(row, (validation ? column : column + 1), chessPieceIndex, chessboard);
							
							if (Utils.markBishopFields(row, column, chessboard, chesspiece)) 
							{
								isMarked = true;
								chessPieceIndex++;
							}
							break;
						case 'R':
							if (!Utils.checkRookFields(row, column, chessboard)) break;
									
							calculatePositions(row, (validation ? column : column + 1), chessPieceIndex, chessboard);
							
							if (Utils.markRookFields(row, column, chessboard)) 
							{
								isMarked = true;
								chessPieceIndex++;
							}
							break;
						case 'N':
							if (!Utils.checkKnightFields(row, column, chessboard)) break;
							
							calculatePositions(row, (validation ? column : column + 1), chessPieceIndex, chessboard);
							
							if (Utils.markKnightFields(row, column, chessboard)) 
							{
								isMarked = true;
								chessPieceIndex++;
							}
							break;
					}
					if (isMarked)
					{
						check4UniqueSet(chessPieceIndex, chessboard);
					}
				}
				else validation = true;
			}
		}
	}

	private static void check4UniqueSet(int chessPieceIndex, Boolean[][] chessboard)
	{
		if (chessPieceIndex == chesspieces.length()) 
		{
			solutions.add(Utils.getUniqueSet(chessboard, chesspieces));
			uniqueSetCount++;
		}
	}

	public static void permutation(String str)
	{
		permutation("", str);
	}

	private static void permutation(String prefix, String str)
	{
		int n = str.length();
		if (n == 0)
		{
			uniquePermutations.add(prefix);
		}
		else 
		{
			for (int i = 0; i < n; i++)
			{
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
			}
		}
	}
}
