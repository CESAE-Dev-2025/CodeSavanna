package CodeSavannaUtils;

import java.util.Random;
import java.util.Scanner;

public class arrayUtils {

    public static String[] getRamdomItem(String[][] matrix, int minIndex, int maxIndex) {
        Random rand = new Random();
        int randomItem = rand.nextInt(maxIndex - minIndex + 1) + minIndex;

        return matrix[randomItem];
    }

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

    public static void printMatrix(String[][] matrix) {

        int[] columnLengths = getColumnsLengths(matrix);

        System.out.println();
        printMatrixSeparator(matrix, columnLengths);
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%-" + columnLengths[j] + "s", matrix[i][j].trim());
                System.out.print(" | ");
            }
            System.out.println();

            if (i == 0) {
                printMatrixSeparator(matrix, columnLengths);
            }
        }
        printMatrixSeparator(matrix, columnLengths);
        System.out.println();
    }

    static void printMatrixSeparator(String[][] matrix, int[] columnLengths) {
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

    public static int countValueInColumn(String[][] matrix, int column, String searchValue) {

        int count = 0;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][column].equals(searchValue)) {
                count++;
            }
        }
        return count;
    }

    public static double sumValueByCriteria(String[][] matrix, int column, String searchValue) {
        double sum = 0;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][column].equals(searchValue)) {
                sum += Double.parseDouble(matrix[i][5]);
            }
        }
        return sum;
    }

    public static boolean existsInMatrix(String[][] matrix, int column, String value) {

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][column].equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static String findValueAtColumn(String[][] matrix, int searchColumn, String searchValue, int responseColumn) {

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][searchColumn].equals(searchValue)) {
                return matrix[i][responseColumn];
            }
        }

        return "";
    }

    static int[] cloneArrayInt(int[] arrayToClone) {
        int[] arrayClone = new int[arrayToClone.length];
        for (int i = 0; i < arrayClone.length; i++) {
            arrayClone[i] = arrayToClone[i];
        }
        return arrayClone;
    }

    static double[] cloneArrayDouble(double[] arrayToClone) {
        double[] arrayClone = new double[arrayToClone.length];
        for (int i = 0; i < arrayClone.length; i++) {
            arrayClone[i] = arrayToClone[i];
        }
        return arrayClone;
    }

    static String[] cloneArrayString(String[] arrayToClone) {
        String[] arrayClone = new String[arrayToClone.length];
        for (int i = 0; i < arrayClone.length; i++) {
            arrayClone[i] = arrayToClone[i];
        }
        return arrayClone;
    }

    static String[][] cloneMatrixString(String[][] matrixToClone) {
        String[][] matrixClone = new String[matrixToClone.length][matrixToClone[0].length];
        for (int i = 0; i < matrixClone.length; i++) {
            for (int j = 0; j < matrixToClone[0].length; j++) {
                matrixClone[i][j] = matrixToClone[i][j];
            }
        }
        return matrixClone;
    }

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
