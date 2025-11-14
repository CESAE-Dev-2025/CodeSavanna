package CodeSavannaUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileUtils {

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
