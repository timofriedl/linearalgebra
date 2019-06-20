package com.timofriedl.tum.linalg;

/**
 * Solves linear equotations as {@link DoubleMatrix}es with gaussian
 * elimination.
 */
public class GaussianSolver {

	/**
	 * the matrix to solve, concatenated with its solutions vector
	 */
	private DoubleMatrix A;

	/**
	 * Creates a new solver instance with a given matrix to solve.
	 * 
	 * Example: ...............................</br>
	 * 
	 * A := ...................................</br>
	 * (2 3 | 0) ..............................</br>
	 * (4 5 | 2) ..............................</br>
	 * 
	 * @param A the matrix to solve, concatenated with its solutions vector
	 */
	public GaussianSolver(DoubleMatrix A) {
		this.A = A;
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
