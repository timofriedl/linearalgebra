package com.timofriedl.tum.linalg.doublematrix.algorithm;

import com.timofriedl.tum.linalg.doublematrix.DoubleMatrix;

/**
 * Solves linear equotations as {@link DoubleMatrix}es with gaussian
 * elimination.
 * 
 * @author Valentin Bertle
 * @author Timo Friedl
 */
public class GaussianSolver extends DoubleMatrixAlgorithm {

	/**
	 * Creates a new solver instance with a given matrix to solve.
	 * 
	 * Example: ...............................</br>
	 * 
	 * A := ...................................</br>
	 * (2 3 | 0) ..............................</br>
	 * (4 5 | 2) ..............................</br>
	 * 
	 * @param inputMatrix the matrix to solve, concatenated with its solutions
	 *                    vector
	 */
	public GaussianSolver(DoubleMatrix inputMatrix) {
		super(inputMatrix);
	}

	/**
	 * Solves a linear equotation system with gaussian elimination.
	 * 
	 * @return the solved matrix
	 */
	public DoubleMatrix solve() {
		// TODO solve

		return null;
	}

}
