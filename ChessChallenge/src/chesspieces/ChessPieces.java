package chesspieces;

import java.util.HashSet;

public class ChessPieces
{
	private static int size;
	private static char chesspiece;
	private static int uniqueSetCount = 0;
	private static String chesspieces;
	private static HashSet<String> uniquePermutations = new HashSet<String>();
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		chesspieces = "QQKKBBN";
		size = 7;
		
		permutation(chesspieces);
		
		for (String permutation : uniquePermutations)
		{
			chesspieces = permutation;
			for (int i = 0; i < size * size; i++)
			{
				CalculatePositions((i / size), (i % size), 0, new Boolean[size][size]);
			}
		}
		long endTime = System.currentTimeMillis();
		
		System.out.printf("[solution count] = %s %n", uniqueSetCount);
		System.out.printf("[time] = %.3f sec", (endTime - startTime) / 1000D);
	}
	
	static void CalculatePositions (int row, int column, int chessPieceIndex, Boolean[][] chessboard) {
		if ((row < size || column < size) && chessPieceIndex == size)
		{
			chessPieceIndex--;
		}
		if (column == chessboard.length)
		{
			row++;
		}
		for (; row < chessboard.length; row++)
		{
			if (column == chessboard.length)
			{
				column = 0;
			}
			for (; column < chessboard.length; column++)
			{
				if (chessboard[row][column] == null && chessPieceIndex < chesspieces.length())
				{
					chesspiece = chesspieces.charAt(chessPieceIndex);
					
					switch (chesspiece)
					{
						case 'Q':
							if (Utils.markQueenFields(row, column, chessboard, chesspiece)) break;
							chessPieceIndex++;
							break;
						case 'K':
							if (Utils.markKingFields(row, column, chessboard)) break;
							chessPieceIndex++;
							break;
						case 'B':
							if (Utils.markBishopFields(row, column, chessboard, chesspiece)) break;
							chessPieceIndex++;
							break;
						case 'R':
							if (Utils.markRookFields(row, column, chessboard)) break;
							chessPieceIndex++;
							break;
						case 'N':
							if (Utils.markKnightFields(row, column, chessboard)) break;
							chessPieceIndex++;
							break;
					}

					check4UniqueSet(chessPieceIndex, chessboard);
				}
			}
		}
	}
	
	private static void check4UniqueSet(int chessPieceIndex, Boolean[][] chessboard)
	{
		if (chessPieceIndex == chesspieces.length())
		{
			uniqueSetCount++;
		}
	}
	
	public static void permutation(String str) { 
	    permutation("", str); 
	}

	private static void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0) uniquePermutations.add(prefix);
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
}