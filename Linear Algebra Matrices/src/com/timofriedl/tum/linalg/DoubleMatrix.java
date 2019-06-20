package com.timofriedl.tum.linalg;

/**
 * Represents a table of double values with a given width and height.
 */
public class DoubleMatrix {

	/**
	 * the numbers in this matrix
	 * 
	 * format: [y][x]
	 */
	private double[][] numbers;

	/**
	 * Creates the zero matrix with a given size.
	 * 
	 * @param width  the width of this matrix
	 * @param height the height of this matrix
	 */
	public DoubleMatrix(int width, int height) {
		if (width < 0 || height < 0)
			throw new IllegalArgumentException("Matrix size must not be negative.");

		numbers = new double[height][width];
	}

	/**
	 * Creates a matrix with the given numbers.
	 * 
	 * Array is called by reference!
	 * 
	 * @param numbers the numbers contained by the matrix
	 */
	public DoubleMatrix(double[][] numbers) {
		if (numbers == null || numbers.length == 0 || numbers[0] == null)
			this.numbers = new double[0][0];
		else
			this.numbers = numbers;
	}

	/**
	 * Creates the identity matrix with the given size.
	 * 
	 * @param size the width and height of this identity matrix
	 * @return the created identity matrix
	 */
	public static DoubleMatrix identity(int size) {
		if (size < 0)
			throw new IllegalArgumentException("The size of the identity matrix must not be negative.");

		final double[][] numbers = new double[size][size];

		for (int x = 0; x < size; x++)
			numbers[x][x] = 1.0;

		return new DoubleMatrix(numbers);
	}

	/**
	 * Scales all numbers of a given row of this matrix with a given factor.
	 * 
	 * @param rowNr  the y position
	 * @param factor the scaling factor
	 */
	public void scaleRow(int rowNr, double factor) {
		if (rowNr < 0 || rowNr >= getHeight())
			throw new IllegalArgumentException("The row " + rowNr + " cannot be scaled, it's outside of this matrix.");

		for (int x = 0; x < getWidth(); x++)
			numbers[rowNr][x] *= factor;
	}

	/**
	 * Scales all numbers of a given column of this matrix with a given factor.
	 * 
	 * @param columnNr the x position
	 * @param factor   the scaling factor
	 */
	public void scaleColumn(int columnNr, double factor) {
		if (columnNr < 0 || columnNr >= getWidth())
			throw new IllegalArgumentException(
					"The column " + columnNr + " cannot be scaled, it's outside of this matrix.");

		for (int y = 0; y < getHeight(); y++)
			numbers[y][columnNr] *= factor;
	}

	/**
	 * Scales all numbers of this matrix with the given factor.
	 * 
	 * @param factor the scaling factor
	 */
	public void scale(double factor) {
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++)
				numbers[y][x] *= factor;
	}

	/**
	 * Resizes this matrix to a new given size, adding zeros if size increases and
	 * deleting numbers if size decreases.
	 * 
	 * @param width  the new width of this matrix
	 * @param height the new height of this matrix
	 */
	public void resize(int width, int height) {
		double[][] numbers = new double[height][width];

		for (int y = 0; y < numbers.length && y < getHeight(); y++)
			for (int x = 0; x < numbers[0].length && x < getWidth(); x++)
				numbers[y][x] = get(x, y);

		this.numbers = numbers;
	}

	/**
	 * Copies an area of this matrix to another {@link DoubleMatrix} instance,
	 * failing if the given area is not completely contained by this matrix.
	 * 
	 * @param x      the x value of the start position for the copy
	 * @param y      the y value of the start position for the copy
	 * @param width  the width of the area to copy
	 * @param height the height of the area to copy
	 * @return the copied area of this matrix
	 */
	public DoubleMatrix copy(int x, int y, int width, int height) {
		if (x < 0 || y < 0 || x + width > getWidth() || y + height > getHeight())
			throw new IllegalArgumentException("The given area does not match the bounds of the matrix to copy.");

		final DoubleMatrix copy = new DoubleMatrix(width, height);

		for (int py = 0; py < height; py++)
			for (int px = 0; px < width; px++)
				copy.set(px, py, get(x + px, y + py));

		return copy;
	}

	/**
	 * Returns a copy of this matrix with the same bounds and content.
	 */
	@Override
	public DoubleMatrix clone() {
		return copy(0, 0, getWidth(), getHeight());
	}

	/**
	 * Filles this matrix at a given offset with the numbers of a second matrix,
	 * failing if this matrix is too small.
	 * 
	 * @param toPaste the matrix to paste in this matrix
	 * @param x       the x offset
	 * @param y       the y offset
	 */
	private void paste(DoubleMatrix toPaste, int x, int y) {
		if (x < 0 || y < 0 || x + toPaste.getWidth() > getWidth() || y + toPaste.getHeight() > getHeight())
			throw new IllegalArgumentException("The bounds of the matrix to paste are outside of this matrix.");

		for (int py = 0; py < toPaste.getHeight(); py++)
			for (int px = 0; px < toPaste.getWidth(); px++)
				set(x + px, y + py, toPaste.get(px, py));
	}

	/**
	 * Appends a second matrix on the right of this matrix, failing if the height is
	 * different.
	 * 
	 * @param secondMatrix the matrix to concatenate with this
	 */
	public void concatenate(DoubleMatrix secondMatrix) {
		if (secondMatrix.getHeight() != getHeight())
			throw new IllegalArgumentException("Matrices must have the same height when concatenating them.");

		final int oldWidth = getWidth();

		resize(oldWidth + secondMatrix.getWidth(), getHeight());
		paste(secondMatrix, oldWidth, 0);
	}

	/**
	 * Prints a row of this matrix to the console.
	 * 
	 * @param rowNr the number of the row to print
	 */
	private void printRow(int rowNr) {
		for (int columnNr = 0; columnNr < getWidth(); columnNr++)
			System.out.print(get(columnNr, rowNr) + "\t");
		System.out.println();
	}

	/**
	 * Prints this matrix to the console.
	 */
	public void print() {
		for (int rowNr = 0; rowNr < getHeight(); rowNr++)
			printRow(rowNr);
		System.out.println();
	}

	/**
	 * Sets a value of this matrix at the given position.
	 * 
	 * @param x     the x coordinate of the position
	 * @param y     the y coordinate of the position
	 * @param value the value to set
	 */
	public void set(int x, int y, double value) {
		numbers[y][x] = value;
	}

	/**
	 * Returns the value of this matrix at the given position.
	 * 
	 * @param x the x coordinate of the position
	 * @param y the y coordinate of the position
	 * @return the value at this position
	 */
	public double get(int x, int y) {
		return numbers[y][x];
	}

	/**
	 * Checks if this matrix is equally wide and tall.
	 * 
	 * @return true if squared, false if rectangled
	 */
	public boolean isSquare() {
		return getWidth() == getHeight();
	}

	/**
	 * @return the width of this matrix
	 */
	public int getWidth() {
		return numbers[0].length;
	}

	/**
	 * @return the height of this matrix
	 */
	public int getHeight() {
		return numbers.length;
	}

	public static void main(String[] args) {
		DoubleMatrix A = new DoubleMatrix(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		A.print();

		System.out.println("Square: " + A.isSquare());

		A.concatenate(identity(3));
		A.print();

		DoubleMatrix B = A.copy(1, 1, 4, 2);
		B.print();

		B = B.clone();
		B.paste(new DoubleMatrix(2, 2), 2, 0);
		B.print();

		B.resize(3, 3);
		B.print();

		B.scale(100);
		B.print();

		B.scaleRow(0, 0.0001);
		B.print();
	}

}
