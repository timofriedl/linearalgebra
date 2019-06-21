package com.timofriedl.tum.linalg.doublematrix.algorithm;

import com.timofriedl.tum.linalg.doublematrix.DoubleMatrix;

/**
 * Contains methods for calculating the determinant recursively of a {@link DoubleMatrix}.
 * 
 * @author Valentin Bertle
 */
public class RecursiveDeterminantCalculator extends DoubleMatrixAlgorithm {

  public RecursiveDeterminantCalculator(DoubleMatrix inputMatrix) {
    super(inputMatrix);

    if (!inputMatrix.isSquare())
      throw new IllegalArgumentException("The matrix should be a n x n matrix.");
  }

  /**
   * Calculates determinant recursively
   * 
   * @param matrix
   * @return determinant
   */
  public double recursiveDeterminant(DoubleMatrix matrix) {
    if (matrix.getHeight() == 1)
      return matrix.get(0, 0);

    double determinant = 0;

    for (int x = 0; x < matrix.getWidth(); x++) {

      DoubleMatrix tmp = matrix.clone();

      tmp.removeRow(0);
      tmp.removeColumn(x);

      if (x % 2 == 0)
        determinant += matrix.get(x, 0) * recursiveDeterminant(tmp);
      else
        determinant -= matrix.get(x, 0) * recursiveDeterminant(tmp);
    }
    return determinant;
  }
}
