package com.timofriedl.tum.linalg.doublematrix.algorithm.gaussian;

import com.timofriedl.tum.linalg.doublematrix.DoubleMatrix;
import com.timofriedl.tum.linalg.doublevector.DoubleVector;

/**
 * A primitive implementation of an {@link GaussianSolver}. No speed
 * improvements or other stuff. Just a simple algorithm.
 * 
 * @author Timo Friedl
 */
public class SimpleGaussianSolver extends GaussianSolver {

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
	public SimpleGaussianSolver(DoubleMatrix inputMatrix) {
		super(inputMatrix);
	}

	@Override
	public DoubleMatrix solve() {
		// TODO implement gaussian elimination algorithm with the three transformation
		// methods

		return null;
	}

	/**
	 * Performs a type one transformation. Swaps two rows of the
	 * <code>inputMatrix</code>.
	 * 
	 * @param firstRowNr  the number of the first row to swap with the second one
	 * @param secondRowNr the number of the second row to swap with the first one
	 */
	private void type1Transformation(int firstRowNr, int secondRowNr) {
		final DoubleVector firstRow = inputMatrix.getRow(firstRowNr);
		final DoubleVector secondRow = inputMatrix.getRow(secondRowNr);

		inputMatrix.pasteRow(firstRowNr, secondRow);
		inputMatrix.pasteRow(secondRowNr, firstRow);
	}

	/**
	 * Performs a type two transformation. Scales a given row with a scaling factor.
	 * 
	 * @param rowNr         the number of the row to scale
	 * @param scalingFactor the factor to scale this row
	 */
	private void type2Transformation(int rowNr, double scalingFactor) {
		inputMatrix.scaleRow(rowNr, scalingFactor);
	}

	/**
	 * Performs a type three transformation. Adds a multiple of a source row to
	 * another row.
	 * 
	 * @param sourceRowNr   the number of the row which will be multiplied by the
	 *                      <code>scalingFactor</code>
	 * @param toChangeRowNr the number of the row where the multiple of the
	 *                      <code>sourceRow</code> will be added
	 * @param scalingFactor the factor to scale the </code>sourceRow</code> with
	 */
	private void type3Transformation(int sourceRowNr, int toChangeRowNr, double scalingFactor) {
		final DoubleVector sourceRow = inputMatrix.getRow(sourceRowNr);
		sourceRow.scale(scalingFactor);

		inputMatrix.addToRow(toChangeRowNr, sourceRow);
	}

}
