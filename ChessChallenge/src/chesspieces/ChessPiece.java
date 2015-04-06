package chesspieces;

public class ChessPiece
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
