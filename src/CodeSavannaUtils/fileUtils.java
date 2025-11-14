package CodeSavannaUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileUtils {

    public static int getFileLinesCount(String filePath) throws FileNotFoundException {

        Scanner in = new Scanner(new File(filePath));
        int lineCount = 0;

        while (in.hasNextLine()) {
            lineCount++;
            in.nextLine();
        }

        in.close();

        return lineCount;
    }

    public static int getFileColumns(String filePath, String separator) throws FileNotFoundException {

        Scanner in = new Scanner(new File(filePath));
        int columnCount = 0;

        if (in.hasNextLine()) {
            String currentLine = in.nextLine();
            columnCount = currentLine.split(separator).length;
        }

        in.close();

        return columnCount;
    }

    public static String[][] readCsv(String filePath, String separator) throws FileNotFoundException {

        String[] currentLine;
        int lines = getFileLinesCount(filePath);
        int columns = getFileColumns(filePath, separator);

        String[][] matrix = new String[lines][columns];
        Scanner file = new Scanner(new File(filePath));

        int rowIndex = 0;
        while (file.hasNextLine()) {
            currentLine = file.nextLine().split(separator);
            for (int coluna = 0; coluna < matrix[0].length; coluna++) {
                matrix[rowIndex][coluna] = currentLine[coluna];
            }
            rowIndex++;
        }

        file.close();

        return matrix;
    }
    
}
