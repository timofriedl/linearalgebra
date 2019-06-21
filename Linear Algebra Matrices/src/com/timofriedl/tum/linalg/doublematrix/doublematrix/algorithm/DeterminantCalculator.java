package com.timofriedl.tum.linalg.doublematrix.doublematrix.algorithm;

import com.timofriedl.tum.linalg.doublematrix.DoubleMatrix;

/**
 * Contains methods for calculating the determinant of a {@link DoubleMatrix}.
 * 
 * @author Valentin Bertle
 */
public class DeterminantCalculator extends DoubleMatrixAlgorithm {

  /**
   * Creates a new instance for determinant calculations
   */
  public DeterminantCalculator(DoubleMatrix inputMatrix) {
    super(inputMatrix);

    if (!inputMatrix.isSquare())
      throw new IllegalArgumentException("Matrix should be a n x n matrix");
  }


  /**
   * Calculates faculty of a certain number
   * 
   * @param n
   * @return
   */
  private int faculty(int n) {
    int sum = n;

    for (int i = n - 1; i > 0; i--)
      sum *= i;

    return sum;
  }

  /**
   * Swaps two array elements a and b
   * 
   * @param array
   * @param a first element
   * @param b second element
   */
  private void swap(int[] array, int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }

  /**
   * Creates an array for a certain n: [0,...,n-1]
   * 
   * @param n the lenght of the array
   * @return
   */
  private int[] makeArray(int n) {
    int[] array = new int[n];

    for (int i = 0; i < array.length; i++)
      array[i] = i;

    return array;
  }

  /**
   * Reverses an array at a certain index
   * 
   * @param array
   * @param index from this index on the array is reversed
   */
  private void reverse(int[] array, int index) {
    int[] a = new int[array.length];

    for (int i = 0; i < index; i++)
      a[i] = array[i];

    int count = 0;

    for (int i = index; i < array.length; i++) {
      a[i] = array[array.length - 1 - count];
      count++;
    }

    for (int i = 0; i < array.length; i++)
      array[i] = a[i];
  }

  /**
   * creates a new permutation of an array
   * 
   * @param array the array that will be permutated
   */
  private void permut(int[] array) {
    int current = 0;

    for (int i = 0; i < array.length - 1; i++)
      if (i >= current && array[i] < array[i + 1])
        current = i;

    int current2 = 0;

    for (int i = 0; i < array.length; i++)
      if (array[i] > array[current])
        current2 = i;

    swap(array, current, current2);

    reverse(array, current + 1);
  }

  /**
   * calculates all permutations of an array: [1,...,n]
   * 
   * @param n the lenght of the permutation array: [1,...,n]
   * @return
   */
  private int[][] permutations(int n) {
    int[] firstPermutation = makeArray(n);
    int permutationsCount = faculty(firstPermutation.length);
    int[][] permutations = new int[permutationsCount][firstPermutation.length];

    for (int i = 0; i < permutations.length; i++) {
      System.arraycopy(firstPermutation, 0, permutations[i], 0, firstPermutation.length);
      permut(firstPermutation);
    }

    return permutations;
  }

  /**
   * Calculates sgn of a permuations
   * 
   * @param permutation the permuatation whose sgn will be calculated
   * @return
   */
  private int sgn(int[] permutation) {
    int numerator = 1;
    int denominator = 1;

    for (int i = 0; i < permutation.length - 1; i++)
      for (int j = i + 1; j < permutation.length; j++) {
        numerator *= permutation[j] - permutation[i];
        denominator *= j - i;
      }

    return numerator / denominator;
  }

  /**
   * Calculates determinant
   * 
   * @param A the matrix whose determinant will be calculated
   * @return
   */
  public double determinant() {
    DoubleMatrix A = super.inputMatrix;
    double determinant = 0.0;
    int[][] permutations = permutations(A.getWidth());

    for (int i = 0; i < permutations.length; i++) {
      double product = 1.0;

      for (int j = 0; j < A.getWidth(); j++)
        product *= A.get(j, permutations[i][j]);

      determinant += sgn(permutations[i]) * product;
    }
    return determinant;
  }

}
