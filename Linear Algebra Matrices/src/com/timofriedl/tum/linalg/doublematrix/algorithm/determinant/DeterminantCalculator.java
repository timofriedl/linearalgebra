package com.timofriedl.tum.linalg.doublematrix.algorithm.determinant;

import com.timofriedl.tum.linalg.doublematrix.DoubleMatrix;
import com.timofriedl.tum.linalg.doublematrix.algorithm.DoubleMatrixAlgorithm;

/**
 * Super class for all different determinant calculators
 */
public abstract class DeterminantCalculator extends DoubleMatrixAlgorithm {

	/**
	 * Creates a new calculator instance for any n x n {@link DoubleMatrix}.
	 * 
	 * @param inputMatrix the input {@link DoubleMatrix} for this algorithm
	 */
	public DeterminantCalculator(DoubleMatrix inputMatrix) {
		super(inputMatrix);

		if (!inputMatrix.isSquare())
			throw new IllegalArgumentException("The matrix should be a n x n matrix.");
	}

	/**
	 * Abstract method for all sub calsses
	 */
	public abstract double determinant();

}
