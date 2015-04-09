package chesspieces;

import java.util.HashSet;
import java.util.Set;

public class ChessPiecesUniqueCombinations
{
	private static int M;
	private static int N;
	private static String chessPiecesInitialSet;

	private static int chessPieceIndex = 0;
	private static int uniqueCombinationsCount = 0;
	
	private static ChessPiece chessPiece;
	private static ChessPiece[] chessPieces;
	private static Set<String> uniqueChessPiecesPermutations = new HashSet<String>();

	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		readInput(args);
		
		chessPieces = new ChessPiece[chessPiecesInitialSet.length()];
		for (int i = 0; i < chessPiecesInitialSet.length(); i++)
		{
			chessPieces[i] = new ChessPiece();
		}

		generateChessPiecesPermutations("", chessPiecesInitialSet);
		
		for (String chessPiecesPermutation : uniqueChessPiecesPermutations)
		{
			calculateUniqueCombinations(chessPiecesPermutation);
		}
		
		long endTime = System.currentTimeMillis();
		
		printResult(startTime, endTime);
	}
	
	private static void readInput(String[] args)
	{
		M = Integer.parseInt(args[0]);
		N = Integer.parseInt(args[1]);
		chessPiecesInitialSet = args[2];
	}

	private static void generateChessPiecesPermutations(String prefix, String str)
	{
		int n = str.length();
		if (n == 0)
		{
			uniqueChessPiecesPermutations.add(prefix);
		}
		else
		{
			for (int i = 0; i < n; i++)
			{
				generateChessPiecesPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
			}
		}
	}

	private static void calculateUniqueCombinations(String chessPiecesSet)
	{
		for (int i = 0; i < chessPieces.length; i++)
		{
			chessPieces[i].setChesspieceType(chessPiecesSet.charAt(i));
		}

		Boolean[][] board = new Boolean[M][N];

		for (int row = 0, column; row < M; row++)
		{
			column = (chessPieces[chessPieceIndex].getColumn() != -1) ? (chessPieces[chessPieceIndex].getColumn() + 1) : 0;
			if (chessPieces[chessPieceIndex].getColumn() == N - 1)
			{
				chessPieces[chessPieceIndex].setColumn(-1);
			}
			for (; column < N; column++)
			{
				if (board[row][column] == null)
				{
					chessPiece = chessPieces[chessPieceIndex];

					switch(chessPiece.getChesspieceType())
					{
						case 'B':
							if (chessPieceIndex == chessPieces.length - 1)
							{
								if (ValidationUtils.checkBishopFields(row, column, board, chessPiece.getChesspieceType()))
								{
									uniqueCombinationsCount++;
								}
							}
							else if (ValidationUtils.markBishopFields(row, column, board, chessPiece.getChesspieceType()))
							{
								saveChesspieceCoordinates(row, column);
								chessPieceIndex++;
							}
							else
							{
								chessPiece.setColumn(-1);
							}
							break;
						case 'K':
							if (chessPieceIndex == chessPieces.length - 1)
							{
								if (ValidationUtils.checkKingFields(row, column, board))
								{
									uniqueCombinationsCount++;
								}
							}
							else if (ValidationUtils.markKingFields(row, column, board))
							{
								saveChesspieceCoordinates(row, column);
								chessPieceIndex++;
							}
							else
							{
								chessPiece.setColumn(-1);
							}
							break;
						case 'N':
							if (chessPieceIndex == chessPieces.length - 1)
							{
								if (ValidationUtils.checkKnightFields(row, column, board))
								{
									uniqueCombinationsCount++;
								}
							}
							else if (ValidationUtils.markKnightFields(row, column, board))
							{
								saveChesspieceCoordinates(row, column);
								chessPieceIndex++;
							}
							else
							{
								chessPiece.setColumn(-1);
							}
							break;
						case 'Q':
							if (chessPieceIndex == chessPieces.length - 1)
							{
								if (ValidationUtils.checkQueenFields(row, column, board, chessPiece.getChesspieceType()))
								{
									uniqueCombinationsCount++;
								}
							}
							else if (ValidationUtils.markQueenFields(row, column, board, chessPiece.getChesspieceType()))
							{
								saveChesspieceCoordinates(row, column);
								chessPieceIndex++;
							}
							else
							{
								chessPiece.setColumn(-1);
							}
							break;
						case 'R':
							if (chessPieceIndex == chessPieces.length - 1)
							{
								if (ValidationUtils.checkRookFields(row, column, board))
								{
									uniqueCombinationsCount++;
								}
							}
							else if (ValidationUtils.markRookFields(row, column, board))
							{
								saveChesspieceCoordinates(row, column);
								chessPieceIndex++;
							}
							else
							{
								chessPiece.setColumn(-1);
							}
							break;
						default:
							System.out.printf("Unsupported chess piece:%n[%s]%n", chessPiece.getChesspieceType());
							System.out.printf("Supported chess pieces:%n[B] - bishop;%n[K] - king;%n[N] - knight;%n[Q] - queen;%n[R] - rook.%n");
							System.exit(0);
							break;
					}
				}
				else
				{
					chessPieces[chessPieceIndex].setColumn(-1);
				}
			}
			if (row == M - 1)
			{
				--chessPieceIndex;
				if (chessPieceIndex < 0) 
				{
					chessPieceIndex = 0;
					break;
				}
				row = chessPieces[chessPieceIndex].getRow() - 1;
				board = new Boolean[M][N];
				removeLastChessPieceFromBoard(board);
				
			}
		}
	}
	private static void saveChesspieceCoordinates(int row, int column)
	{
		chessPiece.setRow(row);
		chessPiece.setColumn(column);
	}
	
	private static void removeLastChessPieceFromBoard(Boolean[][] board)
	{
		for (int piece = 0; piece < chessPieceIndex; piece++)
		{
			chessPiece = chessPieces[piece];
			switch(chessPiece.getChesspieceType())
			{
				case 'B': 
					ValidationUtils.fillBishop(chessPiece.getRow(), chessPiece.getColumn(), board, chessPiece.getChesspieceType());
					break;
				case 'K': 
					ValidationUtils.fillKing(chessPiece.getRow(), chessPiece.getColumn(), board);
					break;
				case 'N': 
					ValidationUtils.fillKnight(chessPiece.getRow(), chessPiece.getColumn(), board);
					break;
				case 'R': 
					ValidationUtils.fillRook(chessPiece.getRow(), chessPiece.getColumn(), board);
					break;
				case 'Q': 
					ValidationUtils.fillQueen(chessPiece.getRow(), chessPiece.getColumn(), board, chessPiece.getChesspieceType());
					break;
			}
		}
	}
	
	private static void printResult(long startTime, long endTime)
	{
		System.out.printf("[solution count] = %s; [time] = %.3f sec%n", uniqueCombinationsCount, (endTime - startTime) / 1000D);
	}
}
