package com.timofriedl.tum.linalg.doublevector;

import java.util.Arrays;

/**
 * Represents a vector of <code>double</code> values.
 * 
 * @author Timo Friedl
 */
public class DoubleVector {

	/**
	 * the numbers stored in this vector
	 */
	private final double[] numbers;

	/**
	 * Creates a new zero vector instance.
	 */
	public DoubleVector(int size) {
		if (size < 0)
			throw new IllegalArgumentException("DoubleVector size must not be negative.");

		numbers = new double[size];
	}

	/**
	 * Creates a new vector instance with the given values.
	 * 
	 * Array is called by reference!
	 * 
	 * @param numbers the <code>double</code> values for this vector
	 */
	public DoubleVector(double[] numbers) {
		this.numbers = numbers;
	}

	/**
	 * Adds the values of a second {@link DoubleVector} to the values of
	 * <code>this</code>.
	 * 
	 * @param toAdd the vector to add
	 */
	public void add(DoubleVector toAdd) {
		if (toAdd.size() != size())
			throw new IllegalArgumentException("DoubleVector sizes must be equal when adding them.");

		for (int i = 0; i < size(); i++)
			numbers[i] += toAdd.numbers[i];
	}

	/**
	 * Scales this {@link DoubleVector} with a given factor.
	 * 
	 * @param factor the scaling factor
	 */
	public void scale(double factor) {
		for (int i = 0; i < size(); i++)
			numbers[i] *= factor;
	}

	/**
	 * Sums up the scalar product with a second vector of the same dimension.
	 * 
	 * @param v2 the second vector for the scalar product
	 * @return the calculated scalar product value
	 */
	public double scalarProduct(DoubleVector v2) {
		if (size() != v2.size())
			throw new IllegalArgumentException(
					"DoubleVectors must have the same size when calculating the scalar product.");

		double sum = 0;
		for (int i = 0; i < size(); i++)
			sum += get(i) * v2.get(i);

		return sum;
	}

	/**
	 * Calculates the sum of all values in this {@link DoubleVector}.
	 * 
	 * @return the calculated sum
	 */
	public double sum() {
		double sum = 0;

		for (int i = 0; i < size(); i++)
			sum += get(i);

		return sum;
	}

	/**
	 * Returns a copy of this {@link DoubleVector}.
	 */
	@Override
	public DoubleVector clone() {
		return new DoubleVector(Arrays.copyOf(numbers, size()));
	}
	
	/**
	 * Prints this {@link DoubleVector} horizontally on the command line.
	 * 
	 * Example: ....................................</br>
	 * 1.0 2.0 3.0
	 */
	public void printHorizontally() {
		for (int i = 0; i < size(); i++)
			System.out.print(get(i) + "\t");
		System.out.println();
	}

	/**
	 * Prints this {@link DoubleVector} vertically on the command line.
	 * 
	 * Example: .....................................</br>
	 * 1.0 ..........................................</br>
	 * 2.0 ..........................................</br>
	 * 3.0
	 */
	public void printVertically() {
		for (int i = 0; i < size(); i++)
			System.out.println(get(i) + "\t");
		System.out.println();
	}

	/**
	 * Returns the number of dimensions of this {@link DoubleVector}.
	 * 
	 * @return the size of this vector
	 */
	public int size() {
		return numbers.length;
	}

	/**
	 * Returns the value of this {@link DoubleVector} at a given position.
	 * 
	 * @param position the value position
	 * @return the value at this position
	 */
	public double get(int position) {
		return numbers[position];
	}

	/**
	 * Sets a value of this {@link DoubleVector} at a given position.
	 * 
	 * @param position the position to set the value
	 * @param value    the new value
	 */
	public void set(int position, double value) {
		numbers[position] = value;
	}

}
