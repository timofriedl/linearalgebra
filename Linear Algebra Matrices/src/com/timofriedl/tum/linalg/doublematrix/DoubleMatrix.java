package com.timofriedl.tum.linalg.doublematrix;

import java.util.Arrays;

import com.timofriedl.tum.linalg.doublevector.DoubleVector;

/**
 * Represents a table of double values with a given width and height.
 * 
 * @author Timo Friedl
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
	 * Adds the values of a second {@link DoubleMatrix} with the same bounds to
	 * this.
	 * 
	 * @param toAdd the matrix to add
	 */
	public void add(DoubleMatrix toAdd) {
		if (toAdd.getWidth() != getWidth() || toAdd.getHeight() != getHeight())
			throw new IllegalArgumentException("Matrices must have same size when adding them.");

		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++)
				set(x, y, get(x, y) + toAdd.get(x, y));
	}

	/**
	 * Adds the values of a {@link DoubleVector} to a given row of this
	 * {@link DoubleMatrix}.
	 * 
	 * @param rowNr    the number of the row in this matrix to access
	 * @param rowToAdd the vector to add to this row
	 */
	public void addToRow(int rowNr, DoubleVector rowToAdd) {
		if (rowNr < 0 || rowNr >= getHeight())
			throw new IllegalArgumentException("The row " + rowNr + " does not match the matrix bounds.");
		if (rowToAdd.size() != getWidth())
			throw new IllegalArgumentException("The row to add does not match the matrix bounds.");

		for (int x = 0; x < rowToAdd.size(); x++)
			numbers[rowNr][x] += rowToAdd.get(x);
	}

	/**
	 * Adds the values of a {@link DoubleVector} to a given column of this
	 * {@link DoubleMatrix}.
	 * 
	 * @param columnNr    the number of the column in this matrix to access
	 * @param columnToAdd the vector to add to this column
	 */
	public void addToColumn(int columnNr, DoubleVector columnToAdd) {
		if (columnNr < 0 || columnNr >= getWidth())
			throw new IllegalArgumentException("The column " + columnNr + " does not match the matrix bounds.");
		if (columnToAdd.size() != getHeight())
			throw new IllegalArgumentException("The column to add does not match the matrix bounds.");

		for (int y = 0; y < columnToAdd.size(); y++)
			numbers[y][columnNr] += columnToAdd.get(y);
	}

	/**
	 * Calculates the matrix multiplication with a second {@link DoubleMatrix}.
	 * 
	 * <code>B</code> must be as tall as <code>this</code> is wide.
	 * 
	 * @param B the second matrix to multiply
	 */
	public DoubleMatrix multiply(DoubleMatrix B) {
		if (B.getHeight() != getWidth())
			throw new IllegalArgumentException("Matrix B must be as tall as A is wide when multiplicating them.");

		final DoubleMatrix result = new DoubleMatrix(B.getWidth(), getHeight());

		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < result.getWidth(); x++)
				result.set(x, y, getRow(y).scalarProduct(B.getColumn(x)));

		return result;
	}

	/**
	 * Scales all numbers of a given row of this {@link DoubleMatrix} with a given
	 * factor.
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
	 * Scales all numbers of a given column of this {@link DoubleMatrix} with a
	 * given factor.
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
	 * Scales all numbers of this {@link DoubleMatrix} with the given factor.
	 * 
	 * @param factor the scaling factor
	 */
	public void scale(double factor) {
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++)
				numbers[y][x] *= factor;
	}

	/**
	 * Resizes this {@link DoubleMatrix} to a new given size, adding zeros if size
	 * increases and deleting numbers if size decreases.
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
	 * Returns a copy of this {@link DoubleMatrix} with the same bounds and content.
	 */
	@Override
	public DoubleMatrix clone() {
		return copy(0, 0, getWidth(), getHeight());
	}

	/**
	 * Filles this {@link DoubleMatrix} at a given offset with the numbers of a
	 * second matrix, failing if this matrix is too small.
	 * 
	 * @param toPaste the matrix to paste in this matrix
	 * @param x       the x offset
	 * @param y       the y offset
	 */
	public void paste(DoubleMatrix toPaste, int x, int y) {
		if (x < 0 || y < 0 || x + toPaste.getWidth() > getWidth() || y + toPaste.getHeight() > getHeight())
			throw new IllegalArgumentException("The bounds of the matrix to paste are outside of this matrix.");

		for (int py = 0; py < toPaste.getHeight(); py++)
			for (int px = 0; px < toPaste.getWidth(); px++)
				set(x + px, y + py, toPaste.get(px, py));
	}

	/**
	 * Pastes a {@link DoubleVector} as a row into this {@link DoubleMatrix}.
	 * 
	 * @param rowNr     the number of the row in this matrix to access
	 * @param rowVector the vector to paste
	 */
	public void pasteRow(int rowNr, DoubleVector rowVector) {
		if (rowNr < 0 || rowNr >= getHeight())
			throw new IllegalArgumentException("The row " + rowNr + " does not match the matrix bounds.");
		if (rowVector.size() != getWidth())
			throw new IllegalArgumentException("The row vector to paste does not match the matrix bounds.");

		for (int x = 0; x < rowVector.size(); x++)
			numbers[rowNr][x] = rowVector.get(x);
	}

	/**
	 * Pastes a {@link DoubleVector} as a column into this {@link DoubleMatrix}.
	 * 
	 * @param columnNr     the number of the column in this matrix to access
	 * @param columnVector the vector to paste
	 */
	public void pasteColumn(int columnNr, DoubleVector columnVector) {
		if (columnNr < 0 || columnNr >= getWidth())
			throw new IllegalArgumentException("The column " + columnNr + " does not match the matrix bounds.");
		if (columnVector.size() != getHeight())
			throw new IllegalArgumentException("The column vector to paste does not match the matrix height.");

		for (int y = 0; y < columnVector.size(); y++)
			numbers[y][columnNr] = columnVector.get(y);
	}

	/**
	 * Appends a second {@link DoubleMatrix} on the right of this matrix, failing if
	 * the height is different.
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
	 * Removes a certain row of this {@link DoubleMatrix}
	 * 
	 * @param rowNr
	 */
	public void removeRow(int rowNr) {
		if (rowNr < 0 || rowNr >= getHeight())
			throw new IllegalArgumentException("That row number does not exist.");

		double[][] numbers = new double[getHeight() - 1][getWidth()];
		int yNumbers = 0;

		for (int y = 0; y < getHeight(); y++)
			if (y != rowNr) {
				for (int x = 0; x < getWidth(); x++)
					numbers[yNumbers][x] = this.numbers[y][x];
				yNumbers++;
			}

		this.numbers = numbers;
	}

	/**
	 * Removes a certain column of this {@link DoubleMatrix}
	 * 
	 * @param columnNr index of the column
	 */
	public void removeColumn(int columnNr) {
		if (columnNr < 0 || columnNr >= getWidth())
			throw new IllegalArgumentException("That column number does not exist.");

		double[][] numbers = new double[getHeight()][getWidth() - 1];

		for (int y = 0; y < getHeight(); y++) {

			int xNumbers = 0;

			for (int x = 0; x < getWidth(); x++)
				if (x != columnNr)
					numbers[y][xNumbers++] = this.numbers[y][x];
		}

		this.numbers = numbers;
	}

	/**
	 * Prints a row of this {@link DoubleMatrix} to the console.
	 * 
	 * @param rowNr the number of the row to print
	 */
	private void printRow(int rowNr) {
		for (int columnNr = 0; columnNr < getWidth(); columnNr++)
			System.out.print(get(columnNr, rowNr) + "\t");
		System.out.println();
	}

	/**
	 * Prints this {@link DoubleMatrix} to the console.
	 */
	public void print() {
		for (int rowNr = 0; rowNr < getHeight(); rowNr++)
			printRow(rowNr);
		System.out.println();
	}

	/**
	 * Sets a value of this {@link DoubleMatrix} at the given position.
	 * 
	 * @param x     the x coordinate of the position
	 * @param y     the y coordinate of the position
	 * @param value the value to set
	 */
	public void set(int x, int y, double value) {
		numbers[y][x] = value;
	}

	/**
	 * Returns the value of this {@link DoubleMatrix} at the given position.
	 * 
	 * @param x the x coordinate of the position
	 * @param y the y coordinate of the position
	 * @return the value at this position
	 */
	public double get(int x, int y) {
		return numbers[y][x];
	}

	/**
	 * Returns a row of this {@link DoubleMatrix} at a given row number as a
	 * {@link DoubleVector}.
	 * 
	 * @param rowNr the number of the row to copy
	 * @return the selected row of this matrix as a vector
	 * @see DoubleVector
	 */
	public DoubleVector getRow(int rowNr) {
		return new DoubleVector(Arrays.copyOf(numbers[rowNr], numbers.length));
	}

	/**
	 * Returns a column of this {@link DoubleMatrix} at a given column number as a
	 * {@link DoubleVector}.
	 * 
	 * @param columnNr the number of the column to copy
	 * @return the selected column of this matrix as a vector
	 * @see DoubleVector
	 */
	public DoubleVector getColumn(int columnNr) {
		final DoubleVector column = new DoubleVector(getHeight());

		for (int y = 0; y < getHeight(); y++)
			column.set(y, get(columnNr, y));

		return column;
	}

	/**
	 * Checks if this {@link DoubleMatrix} is equally wide and tall.
	 * 
	 * @return true if squared, false if rectangled
	 */
	public boolean isSquare() {
		return getWidth() == getHeight();
	}

	/**
	 * @return the width of this {@link DoubleMatrix}
	 */
	public int getWidth() {
		return numbers[0].length;
	}

	/**
	 * @return the height of this {@link DoubleMatrix}
	 */
	public int getHeight() {
		return numbers.length;
	}
}
