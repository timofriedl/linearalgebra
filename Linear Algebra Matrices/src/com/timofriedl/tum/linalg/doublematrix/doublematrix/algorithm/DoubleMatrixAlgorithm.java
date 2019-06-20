package com.timofriedl.tum.linalg.doublematrix.doublematrix.algorithm;

import com.timofriedl.tum.linalg.doublematrix.DoubleMatrix;

/**
 * Represents any algorithm that uses a {@link DoubleMatrix} as input for some
 * calculation.
 * 
 * @author Timo Friedl
 */
public abstract class DoubleMatrixAlgorithm {

	/**
	 * the input {@link DoubleMatrix} for this calculations
	 */
	protected final DoubleMatrix inputMatrix;

	/**
	 * Creates a new algorithm instance with the given input matrix
	 * 
	 * @param inputMatrix the input {@link DoubleMatrix} for this calculations
	 */
	public DoubleMatrixAlgorithm(DoubleMatrix inputMatrix) {
		this.inputMatrix = inputMatrix;
	}

}
