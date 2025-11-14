package CodeSavannaUtils;

import java.util.Random;
import java.util.Scanner;

public class arrayUtils {

    /**
     * Retrieves a random row from the specified matrix based on the provided index range.
     *
     * @param matrix   a two-dimensional array of strings from which a random row will be selected
     * @param minIndex the minimum index (inclusive) of the row range to consider
     * @param maxIndex the maximum index (inclusive) of the row range to consider
     * @return a randomly selected row from the specified range within the matrix
     */
    public static String[] getRamdomItem(String[][] matrix, int minIndex, int maxIndex) {
        Random rand = new Random();
        int randomItem = rand.nextInt(maxIndex - minIndex + 1) + minIndex;

        return matrix[randomItem];
    }

    /**
     * Verifies if the login credentials provided by the user match the expected credentials
     * for the given role.
     *
     * @param role the role of the user trying to log in (e.g., "admin", "client")
     * @return true if the username and password match the stored credentials for the specified role,
     * false otherwise
     */
    public static boolean validLogin(String role) {
        Scanner input = new Scanner(System.in);
        String[][] users = {
                {"admin", "admin", "code"},
                {"admin", "chefe", "!password?456"},
                {"client", "leandro", "pass"}
        };

        String username, password;
        boolean isValid = false;

        System.out.print("\nUsername: ");
        username = input.next().trim();

        System.out.print("Password: ");
        password = input.next().trim();

        for (int i = 0; i < users.length; i++) {
            if (users[i][0].equals(role) && users[i][1].equals(username) && users[i][2].equals(password)) {
                isValid = true;
                i = users.length;
            }
        }

        if (!isValid) {
            System.out.println("\nLogin inválido!");
        }

        return isValid;
    }

    /**
     * Calculates the maximum lengths of strings in each column of a 2D string matrix.
     *
     * @param matrix a 2D array of strings where each row represents a set of values,
     *               and each column is analyzed for the longest string length.
     * @return an array of integers where each element represents the maximum length
     * of the strings in the corresponding column of the input matrix.
     */
    static int[] getColumnsLengths(String[][] matrix) {
        int[] columnLengths = new int[matrix[0].length];

        for (int item = 0; item < matrix.length; item++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[item][column].length() > columnLengths[column]) {
                    columnLengths[column] = matrix[item][column].length();
                }
            }
        }
        return columnLengths;
    }

    /**
     * Prints the given two-dimensional matrix in a tabular format with properly
     * formatted columns.
     *
     * @param matrix the two-dimensional array of strings to be printed, where each
     *               inner array represents a row of the matrix
     */
    public static void printMatrix(String[][] matrix) {

        int[] columnLengths = getColumnsLengths(matrix);

        System.out.println();
        printMatrixHeaderSeparator(matrix, columnLengths);
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%-" + columnLengths[j] + "s", matrix[i][j].trim());
                System.out.print(" | ");
            }
            System.out.println();

            if (i == 0) {
                printMatrixHeaderSeparator(matrix, columnLengths);
            }
        }
        printMatrixHeaderSeparator(matrix, columnLengths);
        System.out.println();
    }

    /**
     * Prints a separator line for a matrix header based on the column lengths.
     *
     * @param matrix        the 2D string array representing the matrix
     * @param columnLengths an array of integers where each element specifies the width of the corresponding column
     */
    static void printMatrixHeaderSeparator(String[][] matrix, int[] columnLengths) {
        System.out.print("+");
        for (int j = 0; j < matrix[0].length; j++) {
            System.out.print("-");
            for (int k = 0; k < columnLengths[j]; k++) {
                System.out.print("-");
            }
            System.out.print("-+");
        }
        System.out.println();
    }

    /**
     * Counts the occurrences of a specified value in a given column of a 2D string array (matrix).
     * This method iterates through the rows of the matrix and increments the count each time the
     * value in the specified column matches the search value.
     *
     * @param matrix      the 2D string array representing the data structure to search within
     * @param column      the column index to search within the matrix
     * @param searchValue the value to search for in the specified column
     * @return the number of times the specified value appears in the given column of the matrix
     */
    public static int countValueInColumn(String[][] matrix, int column, String searchValue) {

        int count = 0;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][column].equals(searchValue)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the sum of numeric values in a specified column of a 2D string array (matrix) based on a search criterion.
     * The method iterates through the rows of the matrix, and for each row where the value in the specified column matches
     * the search value, it adds up the numeric value present in another specified column.
     *
     * @param matrix      the 2D string array that represents the data
     * @param column      the column index to search the matching value
     * @param searchValue the value to match in the specified column
     * @return the sum of the numeric values in the relevant column for rows where the search value matches in the specified column
     */
    public static double sumValueByCriteria(String[][] matrix, int column, String searchValue) {
        double sum = 0;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][column].equals(searchValue)) {
                sum += Double.parseDouble(matrix[i][5]);
            }
        }
        return sum;
    }

    /**
     * Determines whether a given value exists in a specified column of a 2D string array (matrix).
     * The method iterates through the rows of the matrix, checking if the value in the specified column matches
     * the given search value.
     *
     * @param matrix the 2D string array representing the data to search within
     * @param column the column index to search within the matrix
     * @param value  the value to search for in the specified column
     * @return true if the value is found in the specified column; false otherwise
     */
    public static boolean existsInMatrix(String[][] matrix, int column, String value) {

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][column].equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Finds and retrieves a value from a specific column in a 2D string array (matrix) based on a search value in another column.
     * The method iterates through the rows of the matrix checking for a match in the specified search column.
     * If a match is found, it returns the value from the specified response column in that row.
     * If no match is found, an empty string is returned.
     *
     * @param matrix         the 2D string array representing the data structure to search in
     * @param searchColumn   the column index to search for the provided value
     * @param searchValue    the value to search for in the specified search column
     * @param responseColumn the column index from which to retrieve the value when a match is found
     * @return the value from the response column in the row where the search value is found in the search column;
     * if no match is found, an empty string is returned
     */
    public static String findValueAtColumn(String[][] matrix, int searchColumn, String searchValue, int responseColumn) {

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][searchColumn].equals(searchValue)) {
                return matrix[i][responseColumn];
            }
        }

        return "";
    }

    /**
     * Creates a copy of the provided integer array.
     * This method duplicates the elements of the input array.
     *
     * @param arrayToClone the integer array to be cloned
     * @return a new integer array containing the same elements as the input array
     */
    static int[] cloneArrayInt(int[] arrayToClone) {
        int[] arrayClone = new int[arrayToClone.length];
        for (int i = 0; i < arrayClone.length; i++) {
            arrayClone[i] = arrayToClone[i];
        }
        return arrayClone;
    }

    /**
     * Creates a copy of the provided double array.
     * This method duplicates the elements of the input array.
     *
     * @param arrayToClone the double array to be cloned
     * @return a new double array containing the same elements as the input array
     */
    static double[] cloneArrayDouble(double[] arrayToClone) {
        double[] arrayClone = new double[arrayToClone.length];
        for (int i = 0; i < arrayClone.length; i++) {
            arrayClone[i] = arrayToClone[i];
        }
        return arrayClone;
    }

    /**
     * Creates a copy of the provided string array.
     * This method duplicates the structure and elements of the input array.
     *
     * @param arrayToClone the string array to be cloned
     * @return a new string array containing the same elements as the input array
     */
    static String[] cloneArrayString(String[] arrayToClone) {
        String[] arrayClone = new String[arrayToClone.length];
        for (int i = 0; i < arrayClone.length; i++) {
            arrayClone[i] = arrayToClone[i];
        }
        return arrayClone;
    }

    /**
     * Creates a deep copy of a 2D string array (matrix).
     * This method replicates the structure and values of the provided matrix.
     *
     * @param matrixToClone the 2D string array to be cloned
     * @return a new 2D string array that is a deep copy of the input matrix
     */
    static String[][] cloneMatrixString(String[][] matrixToClone) {
        String[][] matrixClone = new String[matrixToClone.length][matrixToClone[0].length];
        for (int i = 0; i < matrixClone.length; i++) {
            for (int j = 0; j < matrixToClone[0].length; j++) {
                matrixClone[i][j] = matrixToClone[i][j];
            }
        }
        return matrixClone;
    }

    /**
     * Sorts an integer array in descending order.
     * This method creates a copy of the provided array and sorts the copy in
     * descending order by rearranging its elements.
     *
     * @param arrayToSort the integer array to be sorted
     * @return a new integer array sorted in descending order
     */
    public static int[] sortIntArrayDescending(int[] arrayToSort) {

        int[] sortedArray = cloneArrayInt(arrayToSort);

        int temp;
        for (int i = sortedArray.length - 1; i >= 0; i--) {

            for (int j = sortedArray.length - 1; j >= 0; j--) {
                if (i != j && sortedArray[i] < sortedArray[j]) {
                    temp = sortedArray[j];
                    sortedArray[j] = sortedArray[i];
                    sortedArray[i] = temp;
                }
            }

        }

        return sortedArray;
    }

    /**
     * Sorts a double array in descending order.
     * This method creates a copy of the provided array and sorts the copy in
     * descending order by rearranging its elements.
     *
     * @param arrayToSort the double array to be sorted
     * @return a new double array sorted in descending order
     */
    public static double[] sortDoubleArrayDescending(double[] arrayToSort) {
        double[] sortedArray = cloneArrayDouble(arrayToSort);

        double temp;
        for (int i = sortedArray.length - 1; i >= 0; i--) {

            for (int j = sortedArray.length - 1; j >= 0; j--) {
                if (i != j && sortedArray[i] < sortedArray[j]) {
                    temp = sortedArray[j];
                    sortedArray[j] = sortedArray[i];
                    sortedArray[i] = temp;
                }
            }

        }

        return sortedArray;
    }

    /**
     * Sorts a string array in descending order based on the values provided in a reference integer array.
     * Both arrays must have the same length. The method rearranges the elements of the string array
     * such that their order is determined by sorting the reference array in descending order.
     *
     * @param arrayToSort    the string array to be sorted
     * @param referenceArray the integer array that serves as the reference for the sorting order
     * @return a new string array sorted according to the descending order of the reference array values
     */
    public static String[] sortStringArrayDescendingByReference(String[] arrayToSort, int[] referenceArray) {
        String[] sortedArray = cloneArrayString(arrayToSort);
        int[] referenceArrayClone = cloneArrayInt(referenceArray);

        String temp;
        int refTemp;

        for (int i = referenceArrayClone.length - 1; i >= 0; i--) {

            for (int j = referenceArrayClone.length - 1; j >= 0; j--) {
                if (i != j && referenceArrayClone[i] < referenceArrayClone[j]) {
                    refTemp = referenceArrayClone[j];
                    referenceArrayClone[j] = referenceArrayClone[i];
                    referenceArrayClone[i] = refTemp;

                    temp = sortedArray[j];
                    sortedArray[j] = sortedArray[i];
                    sortedArray[i] = temp;
                }
            }

        }

        return sortedArray;
    }

    /**
     * Sorts a 2D string array (matrix) in descending order based on the values in a reference array.
     * The rows of the matrix are rearranged to match the order of the reference array values after sorting them in descending order.
     *
     * @param matrixToSort   the 2D string array (matrix) to be sorted
     * @param referenceArray the array of integers that serves as the reference for the sorting order
     * @return a new 2D string array with rows sorted according to the descending order of the reference array values
     */
    public static String[][] sortStringMatrixAtColumnDescendingByReference(String[][] matrixToSort, int[] referenceArray) {
        String[][] sortedMatrix = cloneMatrixString(matrixToSort);
        int[] referenceArrayClone = cloneArrayInt(referenceArray);

        String[] temp;
        int refTemp;

        for (int i = referenceArrayClone.length - 1; i >= 0; i--) {

            for (int j = referenceArrayClone.length - 1; j >= 0; j--) {
                if (i != j && referenceArrayClone[i] < referenceArrayClone[j]) {
                    refTemp = referenceArrayClone[j];
                    referenceArrayClone[j] = referenceArrayClone[i];
                    referenceArrayClone[i] = refTemp;

                    temp = sortedMatrix[j];
                    sortedMatrix[j] = sortedMatrix[i];
                    sortedMatrix[i] = temp;
                }
            }

        }

        return sortedMatrix;
    }

    /**
     * Filters a 2D array (matrix) based on a specified column and value.
     * Returns a new matrix containing only the rows where the value in the specified column matches the given filter value.
     *
     * @param originalMatrix the original 2D array of strings to be filtered
     * @param filterColumn   the column index in the matrix to be used for filtering
     * @param filterValue    the value to filter the rows by
     * @return a new filtered 2D array containing only the rows that match the specified filter criteria
     */
    public static String[][] filterMatrix(String[][] originalMatrix, int filterColumn, String filterValue) {
        int count = 0;
        for (int i = 1; i < originalMatrix.length; i++) {
            if (originalMatrix[i][filterColumn].equals(filterValue)) {
                count++;
            }
        }
        String[][] filteredMatrix = new String[count][originalMatrix[0].length];

        int filteredMatrixIndex = 0;
        for (int i = 0; i < originalMatrix.length; i++) {
            if (originalMatrix[i][filterColumn].equals(filterValue)) {
                filteredMatrix[filteredMatrixIndex] = originalMatrix[i];
                filteredMatrixIndex++;
            }
        }

        return filteredMatrix;
    }

}
