package chesspieces;

public class Utils
{
	private static int rowBuffer;
	private static int columnBuffer;
	
	public static boolean checkQueenFields(int row, int column, Boolean[][] board, char chesspiece)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillBishopStraightDiagonal(row, column, chessboard) ||
			!Utils.fillBishopBackDiagonal(row, column, chessboard, chesspiece) ||
			!Utils.fillRookColumn(row, column, chessboard) ||
			!Utils.fillRookRow(row, column, chessboard))
		{
			return false;
		}

		return true;
	}

	public static boolean checkKingFields(int row, int column, Boolean[][] board)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillKingNeighbours(row, column, chessboard))
		{
			return false;
		}

		return true;
	}

	public static boolean checkBishopFields(int row, int column, Boolean[][] board, char chesspiece)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillBishopStraightDiagonal(row, column, chessboard) ||
			!Utils.fillBishopBackDiagonal(row, column, chessboard, chesspiece))
		{
			return false;
		}

		return true;
	}

	public static boolean checkRookFields(int row, int column, Boolean[][] board)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillRookColumn(row, column, chessboard) ||
			!Utils.fillRookRow(row, column, chessboard))
		{
			return false;
		}

		return true;
	}

	public static boolean checkKnightFields(int row, int column, Boolean[][] board)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillKnightMoveUp(row, column, chessboard) ||
			!Utils.fillKnightMoveRight(row, column, chessboard) ||
			!Utils.fillKnightMoveDown(row, column, chessboard) ||
			!Utils.fillKnightMoveLeft(row, column, chessboard))
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean markQueenFields(int row, int column, Boolean[][] board, char chesspiece)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillBishopStraightDiagonal(row, column, chessboard) ||
			!Utils.fillBishopBackDiagonal(row, column, chessboard, chesspiece) ||
			!Utils.fillRookColumn(row, column, chessboard) ||
			!Utils.fillRookRow(row, column, chessboard))
		{
			return false;
		}
		copyChessboard(chessboard, board);
		return true;
	}

	public static boolean markKingFields(int row, int column, Boolean[][] board)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillKingNeighbours(row, column, chessboard))
		{
			return false;
		}
		copyChessboard(chessboard, board);
		return true;
	}

	public static boolean markBishopFields(int row, int column, Boolean[][] board, char chesspiece)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillBishopStraightDiagonal(row, column, chessboard) ||
			!Utils.fillBishopBackDiagonal(row, column, chessboard, chesspiece))
		{
			return false;
		}
		copyChessboard(chessboard, board);
		return true;
	}

	public static boolean markRookFields(int row, int column, Boolean[][] board)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillRookColumn(row, column, chessboard) ||
			!Utils.fillRookRow(row, column, chessboard))
		{
			return false;
		}
		copyChessboard(chessboard, board);
		return true;
	}

	public static boolean markKnightFields(int row, int column, Boolean[][] board)
	{
		Boolean[][] chessboard = new Boolean[board.length][];
		copyChessboard(board, chessboard);
		if (!Utils.fillKnightMoveUp(row, column, chessboard) ||
			!Utils.fillKnightMoveRight(row, column, chessboard) ||
			!Utils.fillKnightMoveDown(row, column, chessboard) ||
			!Utils.fillKnightMoveLeft(row, column, chessboard))
		{
			return false;
		}
		copyChessboard(chessboard, board);
		return true;
	}

	public static void copyChessboard(Boolean[][] source, Boolean[][] destination)
	{
		for (int arrayIndex = 0; arrayIndex < source.length; arrayIndex++)
		{
			Boolean[] array = source[arrayIndex];
			destination[arrayIndex] = new Boolean[source.length];
			System.arraycopy(array, 0, destination[arrayIndex], 0, source.length);
		}
	}

	private static boolean fillBishopStraightDiagonal(int row, int column, Boolean[][] chessboard)
	{
		if (row > column)
		{
			row -= column;
			column = 0;
		}
		else
		{
			column -= row;
			row = 0;
		}
		while (row < chessboard.length && column < chessboard[0].length)
		{
			if (chessboard[row][column] == null ||
				chessboard[row][column] != true)
			{
				chessboard[row][column] = false;
			}
			else
			{
				return false;
			}
			row++;
			column++;
		}
		return true;
	}

	private static boolean fillBishopBackDiagonal(int row, int column, Boolean[][] chessboard, char chesspiece)
	{
		rowBuffer = row;
		columnBuffer = column;
		if (row + column > chessboard.length - 1)
		{
			row = (row - (chessboard[0].length - 1 - column));
			column = chessboard[0].length - 1;
		} 
		else
		{
			column = row + column;
			row = 0;
		}
		while (row < chessboard.length && column >= 0)
		{
			if (chessboard[row][column] == null ||
				chessboard[row][column] != true)
			{
				chessboard[row][column] = false;
			}
			else
			{
				return false;
			}
			row++;
			column--;
		}
		if (chesspiece == 'B')
		{
			chessboard[rowBuffer][columnBuffer] = true;
		}
		return true;
	}

	public static boolean fillRookColumn(int row, int column, Boolean[][] chessboard)
	{
		row = 0;
		while (row < chessboard.length)
		{
			if (chessboard[row][column] == null ||
				chessboard[row][column] != true)
			{
				chessboard[row][column] = false;
			}
			else
			{
				return false;
			}
			row++;
		}
		return true;
	}

	public static boolean fillRookRow(int row, int column, Boolean[][] chessboard)
	{
		rowBuffer = row;
		columnBuffer = column;
		column = 0;
		while (column < chessboard[0].length)
		{
			if (chessboard[row][column] == null ||
				chessboard[row][column] != true)
			{
				chessboard[row][column] = false;
			}
			else
			{
				return false;
			}
			column++;
		}
		chessboard[rowBuffer][columnBuffer] = true;
		return true;
	}

	public static boolean fillKingNeighbours(int row, int column, Boolean[][] chessboard)
	{
		rowBuffer = row;
		columnBuffer = column;
		if (row == 0)
		{
			if (column == 0)
			{
				if ((chessboard[row][column + 1] == null || chessboard[row][column + 1] != true) &&
					(chessboard[row + 1][column] == null || chessboard[row + 1][column] != true) &&
					(chessboard[row + 1][column + 1] == null || chessboard[row + 1][column + 1] != true))
				{
					chessboard[row][column + 1] = false;
					chessboard[row + 1][column] = false;
					chessboard[row + 1][column + 1] = false;
				}
				else
				{
					return false;
				}
			}
			else if (column == chessboard[0].length - 1)
			{
				if ((chessboard[row][column - 1] == null || chessboard[row][column - 1] != true) &&
					(chessboard[row + 1][column] == null || chessboard[row + 1][column] != true) &&
					(chessboard[row + 1][column - 1] == null || chessboard[row + 1][column - 1] != true))
				{
					chessboard[row][column - 1] = false;
					chessboard[row + 1][column] = false;
					chessboard[row + 1][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if ((chessboard[row][column + 1] == null || chessboard[row][column + 1] != true) &&
					(chessboard[row][column - 1] == null || chessboard[row][column - 1] != true) &&
					(chessboard[row + 1][column] == null || chessboard[row + 1][column] != true) &&
					(chessboard[row + 1][column + 1] == null || chessboard[row + 1][column + 1] != true) &&
					(chessboard[row + 1][column - 1] == null || chessboard[row + 1][column - 1] != true))
				{
					chessboard[row][column + 1] = false;
					chessboard[row][column - 1] = false;
					chessboard[row + 1][column] = false;
					chessboard[row + 1][column + 1] = false;
					chessboard[row + 1][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
		}
		else if (row == chessboard.length - 1)
		{
			if (column == 0)
			{
				if ((chessboard[row][column + 1] == null || chessboard[row][column + 1] != true) &&
					(chessboard[row - 1][column] == null || chessboard[row - 1][column] != true) &&
					(chessboard[row - 1][column + 1] == null || chessboard[row - 1][column + 1] != true))
				{
					chessboard[row][column + 1] = false;
					chessboard[row - 1][column] = false;
					chessboard[row - 1][column + 1] = false;
				}
				else
				{
					return false;
				}
			}
			else if (column == chessboard[0].length - 1)
			{
				if ((chessboard[row][column - 1] == null || chessboard[row][column - 1] != true) &&
					(chessboard[row - 1][column] == null || chessboard[row - 1][column] != true) &&
					(chessboard[row - 1][column - 1] == null || chessboard[row - 1][column - 1] != true))
				{
					chessboard[row][column - 1] = false;
					chessboard[row - 1][column] = false;
					chessboard[row - 1][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if ((chessboard[row][column + 1] == null || chessboard[row][column + 1] != true) &&
					(chessboard[row][column - 1] == null || chessboard[row][column - 1] != true) &&
					(chessboard[row - 1][column] == null || chessboard[row - 1][column] != true) &&
					(chessboard[row - 1][column + 1] == null || chessboard[row - 1][column + 1] != true) &&
					(chessboard[row - 1][column - 1] == null || chessboard[row - 1][column - 1] != true))
				{
					chessboard[row][column + 1] = false;
					chessboard[row][column - 1] = false;
					chessboard[row - 1][column] = false;
					chessboard[row - 1][column + 1] = false;
					chessboard[row - 1][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			if (column == 0)
			{
				if ((chessboard[row - 1][column] == null || chessboard[row - 1][column] != true) &&
					(chessboard[row][column + 1] == null || chessboard[row][column + 1] != true) &&
					(chessboard[row + 1][column] == null || chessboard[row + 1][column] != true) &&
					(chessboard[row - 1][column + 1] == null || chessboard[row - 1][column + 1] != true) &&
					(chessboard[row + 1][column + 1] == null || chessboard[row + 1][column + 1] != true))
				{
					chessboard[row - 1][column] = false;
					chessboard[row][column + 1] = false;
					chessboard[row + 1][column] = false;
					chessboard[row - 1][column + 1] = false;
					chessboard[row + 1][column + 1] = false;
				}
				else
				{
					return false;
				}
			}
			else if (column == chessboard[0].length - 1)
			{
				if ((chessboard[row - 1][column] == null || chessboard[row - 1][column] != true) &&
					(chessboard[row][column - 1] == null || chessboard[row][column - 1] != true) &&
					(chessboard[row + 1][column] == null || chessboard[row + 1][column] != true) &&
					(chessboard[row - 1][column - 1] == null || chessboard[row - 1][column - 1] != true) &&
					(chessboard[row + 1][column - 1] == null || chessboard[row + 1][column - 1] != true))
				{
					chessboard[row - 1][column] = false;
					chessboard[row][column - 1] = false;
					chessboard[row + 1][column] = false;
					chessboard[row - 1][column - 1] = false;
					chessboard[row + 1][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if ((chessboard[row - 1][column] == null || chessboard[row - 1][column] != true) &&
					(chessboard[row][column - 1] == null || chessboard[row][column - 1] != true) &&
					(chessboard[row][column + 1] == null || chessboard[row][column + 1] != true) &&
					(chessboard[row + 1][column] == null || chessboard[row + 1][column] != true) &&
					(chessboard[row - 1][column - 1] == null || chessboard[row - 1][column - 1] != true) &&
					(chessboard[row - 1][column + 1] == null || chessboard[row - 1][column + 1] != true) &&
					(chessboard[row + 1][column - 1] == null || chessboard[row + 1][column - 1] != true) &&
					(chessboard[row + 1][column + 1] == null || chessboard[row + 1][column + 1] != true))
				{
					chessboard[row - 1][column] = false;
					chessboard[row][column - 1] = false;
					chessboard[row][column + 1] = false;
					chessboard[row + 1][column] = false;
					chessboard[row - 1][column - 1] = false;
					chessboard[row - 1][column + 1] = false;
					chessboard[row + 1][column - 1] = false;
					chessboard[row + 1][column + 1] = false;
				}
				else
				{
					return false;
				}
			}
		}
		chessboard[rowBuffer][columnBuffer] = true;
		return true;
	}

	private static boolean fillKnightMoveUp(int row, int column, Boolean[][] chessboard)
	{
		rowBuffer = row;
		columnBuffer = column;
		if (row >= 2)
		{
			if (column == 0)
			{
				if (chessboard[row - 2][column + 1] == null ||
					chessboard[row - 2][column + 1] != true)
				{
					chessboard[row - 2][column + 1] = false;
				}
				else
				{
					return false;
				}
			}
			else if (column == chessboard[0].length - 1)
			{
				if (chessboard[row - 2][column - 1] == null ||
					chessboard[row - 2][column - 1] != true)
				{
					chessboard[row - 2][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if ((chessboard[row - 2][column + 1] == null || chessboard[row - 2][column + 1] != true) &&
					(chessboard[row - 2][column - 1] == null || chessboard[row - 2][column - 1] != true))
				{
					chessboard[row - 2][column + 1] = false;
					chessboard[row - 2][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}

	private static boolean fillKnightMoveRight(int row, int column, Boolean[][] chessboard)
	{
		rowBuffer = row;
		columnBuffer = column;
		if (column < chessboard[0].length - 2)
		{
			if (row == 0)
			{
				if (chessboard[row + 1][column + 2] == null ||
					chessboard[row + 1][column + 2] != true)
				{
					chessboard[row + 1][column + 2] = false;
				}
				else
				{
					return false;
				}
			}
			else if (row == chessboard.length - 1)
			{
				if (chessboard[row - 1][column + 2] == null ||
					chessboard[row - 1][column + 2] != true)
				{
					chessboard[row - 1][column + 2] = false;
				} else {
					return false;
				}
			}
			else
			{
				if ((chessboard[row + 1][column + 2] == null || chessboard[row + 1][column + 2] != true) &&
					(chessboard[row - 1][column + 2] == null || chessboard[row - 1][column + 2] != true))
				{
					chessboard[row + 1][column + 2] = false;
					chessboard[row - 1][column + 2] = false;
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}

	private static boolean fillKnightMoveDown(int row, int column, Boolean[][] chessboard)
	{
		rowBuffer = row;
		columnBuffer = column;
		if (row < chessboard.length - 2)
		{
			if (column == 0) {
				if (chessboard[row + 2][column + 1] == null ||
					chessboard[row + 2][column + 1] != true)
				{
					chessboard[row + 2][column + 1] = false;
				}
				else
				{
					return false;
				}
			}
			else if (column == chessboard[0].length - 1)
			{
				if (chessboard[row + 2][column - 1] == null ||
					chessboard[row + 2][column - 1] != true)
				{
					chessboard[row + 2][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if ((chessboard[row + 2][column + 1] == null || chessboard[row + 2][column + 1] != true) &&
					(chessboard[row + 2][column - 1] == null || chessboard[row + 2][column - 1] != true))
				{
					chessboard[row + 2][column + 1] = false;
					chessboard[row + 2][column - 1] = false;
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}

	private static boolean fillKnightMoveLeft(int row, int column, Boolean[][] chessboard)
	{
		rowBuffer = row;
		columnBuffer = column;
		if (column >= 2)
		{
			if (row == 0)
			{
				if (chessboard[row + 1][column - 2] == null ||
					chessboard[row + 1][column - 2] != true)
				{
					chessboard[row + 1][column - 2] = false;
				}
				else
				{
					return false;
				}
			}
			else if (row == chessboard.length - 1)
			{
				if (chessboard[row - 1][column - 2] == null ||
					chessboard[row - 1][column - 2] != true)
				{
					chessboard[row - 1][column - 2] = false;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if ((chessboard[row + 1][column - 2] == null || chessboard[row + 1][column - 2] != true) &&
					(chessboard[row - 1][column - 2] == null || chessboard[row - 1][column - 2] != true))
				{
					chessboard[row + 1][column - 2] = false;
					chessboard[row - 1][column - 2] = false;
				} 
				else
				{
					return false;
				}
			}
		}
		chessboard[rowBuffer][columnBuffer] = true;
		return true;
	}

	public static void printChessboard(Boolean[][] chessboard, String piecesSet)
	{
		int index = 0;
		for (int row = 0, column; row < chessboard.length; row++)
		{
			for (column = 0; column < chessboard.length; column++)
			{
				if (chessboard[row][column] == null)
					System.out.print('\u006F' + " ");
				else if (!chessboard[row][column])
					System.out.print('\u0078' + " ");
				else
				{
					System.out.print(piecesSet.charAt(index) + " ");
					index++;
				}
			}
			System.out.println();
		}
	}
	
	public static String getUniqueSet(Boolean[][] chessboard, String piecesSet)
	{
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (int row = 0, column; row < chessboard.length; row++)
		{
			for (column = 0; column < chessboard.length; column++)
			{
				if (chessboard[row][column] == null)
					sb.append('\u006F' + " ");
				else if (!chessboard[row][column])
					sb.append('\u0078' + " ");
				else
				{
					sb.append(piecesSet.charAt(index) + " ");
					index++;
				}
			}
			sb.append('\n');
		}
		
		return sb.toString();
	}
}