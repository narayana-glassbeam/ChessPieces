package chesspieces;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChessPieces
{
	private static int chesspieceIndex = 0;
	private static ChessPiece chesspiece;
	private static int unique = 0;
	private static int M;
	private static int N;
	private static ChessPiece[] chesspieces;
	private static Set<String> uniqueChessPermutations = new HashSet<String>();

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		M = scanner.nextInt();
		N = scanner.nextInt();
		String chesspiecesSet = scanner.next();
		
		chesspieces = new ChessPiece[chesspiecesSet.length()];
		for (int i = 0; i < chesspiecesSet.length(); i++)
		{
			chesspieces[i] = new ChessPiece();
		}
		
		long startTime = System.currentTimeMillis();
		permutation("", chesspiecesSet);
		for (String permutation : uniqueChessPermutations)
		{
			calculate(permutation);
		}
		long endTime = System.currentTimeMillis();
		System.out.printf("[solution count] = %s; ", unique);
		System.out.printf("[time] = %.3f sec%n", (endTime - startTime) / 1000D);
	}

	private static void calculate(String set)
	{
		for (int i = 0; i < chesspieces.length; i++)
		{
			chesspieces[i].setChesspieceType(set.charAt(i));
		}

		Boolean[][] board = new Boolean[M][N];

		for (int row = 0, column; row < M; row++)
		{
			column = (chesspieces[chesspieceIndex].getColumn() != -1) ? (chesspieces[chesspieceIndex].getColumn() + 1) : 0;
			if (chesspieces[chesspieceIndex].getColumn() == N - 1)
			{
				chesspieces[chesspieceIndex].setColumn(-1);
			}
			for (; column < N; column++)
			{
				if (board[row][column] == null)
				{
					chesspiece = chesspieces[chesspieceIndex];
					switch(chesspiece.getChesspieceType())
					{
						case 'B':
							if (chesspieceIndex == chesspieces.length - 1)
							{
								if (TestUtils.checkBishopFields(row, column, board, chesspiece.getChesspieceType()))
								{
									unique++;
								}
							}
							else if (TestUtils.markBishopFields(row, column, board, chesspiece.getChesspieceType()))
							{
								saveChesspieceCoordiantes(row, column);
								chesspieceIndex++;
							}
							else
							{
								chesspiece.setColumn(-1);
							}
							break;
						case 'K':
							if (chesspieceIndex == chesspieces.length - 1)
							{
								if (TestUtils.checkKingFields(row, column, board))
								{
									unique++;
								}
							}
							else if (TestUtils.markKingFields(row, column, board))
							{
								saveChesspieceCoordiantes(row, column);
								chesspieceIndex++;
							}
							else
							{
								chesspiece.setColumn(-1);
							}
							break;
						case 'N':
							if (chesspieceIndex == chesspieces.length - 1)
							{
								if (TestUtils.checkKnightFields(row, column, board))
								{
									unique++;
								}
							}
							else if (TestUtils.markKnightFields(row, column, board))
							{
								saveChesspieceCoordiantes(row, column);
								chesspieceIndex++;
							}
							else
							{
								chesspiece.setColumn(-1);
							}
							break;
						case 'Q':
							if (chesspieceIndex == chesspieces.length - 1)
							{
								if (TestUtils.checkQueenFields(row, column, board, chesspiece.getChesspieceType()))
								{
									unique++;
								}
							}
							else if (TestUtils.markQueenFields(row, column, board, chesspiece.getChesspieceType()))
							{
								saveChesspieceCoordiantes(row, column);
								chesspieceIndex++;
							}
							else
							{
								chesspiece.setColumn(-1);
							}
							break;
						case 'R':
							if (chesspieceIndex == chesspieces.length - 1)
							{
								if (TestUtils.checkRookFields(row, column, board))
								{
									unique++;
								}
							}
							else if (TestUtils.markRookFields(row, column, board))
							{
								saveChesspieceCoordiantes(row, column);
								chesspieceIndex++;
							}
							else
							{
								chesspiece.setColumn(-1);
							}
							break;
					}
				}
				else
				{
					chesspieces[chesspieceIndex].setColumn(-1);
				}
			}
			if (row == M - 1)
			{
				--chesspieceIndex;
				if (chesspieceIndex < 0) 
				{
					chesspieceIndex = 0;
					break;
				}
				row = chesspieces[chesspieceIndex].getRow() - 1;
				board = new Boolean[M][N];
				for (int piece = 0; piece < chesspieceIndex; piece++)
				{
					chesspiece = chesspieces[piece];
					switch(chesspiece.getChesspieceType())
					{
						case 'B': TestUtils.fillBishop(chesspiece.getRow(), chesspiece.getColumn(), board, chesspiece.getChesspieceType()); break;
						case 'K': TestUtils.fillKing(chesspiece.getRow(), chesspiece.getColumn(), board); break;
						case 'N': TestUtils.fillKnight(chesspiece.getRow(), chesspiece.getColumn(), board); break;
						case 'R': TestUtils.fillRook(chesspiece.getRow(), chesspiece.getColumn(), board); break;
						case 'Q': TestUtils.fillQueen(chesspiece.getRow(), chesspiece.getColumn(), board, chesspiece.getChesspieceType()); break;
					}
				}
			}
		}
	}
	private static void saveChesspieceCoordiantes(int row, int column)
	{
		chesspiece.setRow(row);
		chesspiece.setColumn(column);
	}

	private static void permutation(String prefix, String str)
	{
		int n = str.length();
		if (n == 0)
		{
			uniqueChessPermutations.add(prefix);
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

class ChessPiece
{
	private char chesspieceType;
	private int row;
	private int column;

	void setChesspieceType(char chesspieceType)
	{
		this.chesspieceType = chesspieceType;
		setRow(-1);
		setColumn(-1);
	}
	char getChesspieceType()
	{
		return this.chesspieceType;
	}
	void setRow(int row)
	{
		this.row = row;
	}
	int getRow()
	{
		return this.row;
	}
	void setColumn(int column)
	{
		this.column = column;
	}
	int getColumn()
	{
		return this.column;
	}
}
