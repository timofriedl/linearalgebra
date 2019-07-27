package com.timofriedl.linalg.doublematrix.algorithm.gaussian;

import com.timofriedl.linalg.doublematrix.DoubleMatrix;
import com.timofriedl.linalg.doublematrix.algorithm.DoubleMatrixAlgorithm;

/**
 * Solves linear equotations as {@link DoubleMatrix}es with gaussian
 * elimination.
 * 
 * @author Timo Friedl
 */
public abstract class GaussianSolver extends DoubleMatrixAlgorithm {

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
	public abstract DoubleMatrix solve();

}
