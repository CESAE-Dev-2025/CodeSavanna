package CodeSavannaUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileUtils {

    /**
     * Counts the number of lines in a file specified by the file path.
     *
     * @param filePath the path of the file whose lines are to be counted
     * @return the number of lines in the file
     * @throws FileNotFoundException if the file at the specified path does not exist
     */
    public static int getFileLinesCount(String filePath) throws FileNotFoundException {

        Scanner input = new Scanner(new File(filePath));
        int lineCount = 0;

        while (input.hasNextLine()) {
            lineCount++;
            input.nextLine();
        }

        input.close();

        return lineCount;
    }

    /**
     * Reads the first line of a file and calculates the number of columns based on the specified separator.
     * The columns are determined by splitting the first line using the given separator.
     *
     * @param filePath  the path to the file to be read
     * @param separator the delimiter used to split the first line of the file into columns
     * @return the number of columns in the first line of the file
     * @throws FileNotFoundException if the file at the specified path does not exist
     */
    public static int getFileColumns(String filePath, String separator) throws FileNotFoundException {

        Scanner input = new Scanner(new File(filePath));
        int columnCount = 0;

        if (input.hasNextLine()) {
            String currentLine = input.nextLine();
            columnCount = currentLine.split(separator).length;
        }

        input.close();

        return columnCount;
    }

    /**
     * Reads a CSV file from the specified file path and parses its content into a 2D string array.
     * Each row in the resulting 2D array represents a line from the CSV file split by the specified separator.
     *
     * @param filePath  the path to the CSV file to be read
     * @param separator the delimiter that separates the values in the CSV file
     * @return a 2D string array containing the parsed data from the CSV file
     * @throws FileNotFoundException if the file at the specified path does not exist
     */
    public static String[][] readCsv(String filePath, String separator) throws FileNotFoundException {

        String[] currentLine;
        int lines = getFileLinesCount(filePath);
        int columns = getFileColumns(filePath, separator);

        String[][] matrix = new String[lines][columns];
        Scanner input = new Scanner(new File(filePath));

        int rowIndex = 0;
        while (input.hasNextLine()) {
            currentLine = input.nextLine().split(separator);
            for (int coluna = 0; coluna < matrix[0].length; coluna++) {
                matrix[rowIndex][coluna] = currentLine[coluna];
            }
            rowIndex++;
        }

        input.close();

        return matrix;
    }

}
