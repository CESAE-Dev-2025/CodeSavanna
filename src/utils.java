import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class utils {
    /**
     * Validates the login credentials for a given role.
     *
     * @param role The role of the user trying to log in (e.g., "admin" or "client").
     * @return true if the provided username and password match the stored credentials for the specified role, false otherwise.
     */
    public static boolean validLogin(String role) {
        Scanner sc = new Scanner(System.in);
        String[][] users = {
                {"admin", "neo", "matrix"},
                {"admin", "admin", "code"},
                {"admin", "chefe", "!password?456"},
                {"client", "leandro", "pass"}
        };

        String username, password;
        boolean isValid = false;

        System.out.print("\nUsername: ");
        username = sc.next().trim();

        System.out.print("Password: ");
        password = sc.next().trim();

        for (int i = 0; i < users.length; i++) {
            if (users[i][0].equals(role) && users[i][1].equals(username) && users[i][2].equals(password)) {
                isValid = true;
                i = users.length;
            }
        }

        return isValid;
    }

    /**
     * Reads the header of a CSV file and extracts the column names.
     *
     * @param filePath  The file path of the CSV file to read.
     * @param columns   The number of columns in the CSV file.
     * @param separator The separator used in the CSV file (e.g., ",", ";", "\t").
     * @return A 2D String array containing the header row with column names.
     * @throws FileNotFoundException If the provided file path is invalid or the file cannot be found.
     */
    public static String[][] readCsvHeader(String filePath, int columns, String separator) throws FileNotFoundException {
        Scanner file = new Scanner(new File(filePath));

        String[][] matriz = new String[1][columns];
        String[] linha = file.nextLine().split(separator);

        for (int coluna = 0; coluna < matriz[0].length; coluna++) {
            matriz[0][coluna] = linha[coluna];
        }

        file.close();

        return matriz;
    }

    /**
     * Retrieves the number of lines present in the specified file.
     *
     * @param filePath The file path of the file to count the lines from.
     * @return The total number of lines in the file.
     * @throws FileNotFoundException If the provided file path is invalid or the file cannot be found.
     */
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

    /**
     * Retrieves the number of columns in a file based on the specified separator.
     *
     * @param filePath  The file path of the file to extract column count from.
     * @param separator The separator used to distinguish columns in the file.
     * @return The total number of columns found in the file.
     * @throws FileNotFoundException If the provided file path is invalid or the file cannot be found.
     */
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

    /**
     * Reads a CSV file and stores its contents in a 2D String array based on the specified parameters.
     *
     * @param filePath  The file path of the CSV file to read.
     * @param separator The separator used in the CSV file to split values (e.g., ",", ";", "\t").
     * @return A 2D String array containing the data read from the CSV file.
     * @throws FileNotFoundException If the provided file path is invalid or the file cannot be found.
     */
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

    /**
     * Prints the given matrix to the console in a visually organized format.
     *
     * @param matrix The 2D String array representing the matrix to be printed.
     */
    public static void printMatrix(String[][] matrix) {

        int[] columnLengths = new int[matrix[0].length];

        for (int item = 0; item < matrix.length; item++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[item][column].length() > columnLengths[column]) {
                    columnLengths[column] = matrix[item][column].length();
                }
            }
        }

        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            // TODO: Imprimir linha para separar cabeçalho do conteúdo (+-----+-----+-----+)
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%-" + columnLengths[j] + "s", matrix[i][j].trim());
                if (j < matrix[0].length) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Searches for a specified value in a particular column of a 2D matrix.
     *
     * @param matrix      The 2D String array representing the matrix to search.
     * @param column      The integer value of the column to search within the matrix.
     * @param searchValue The String value to search for within the specified column.
     * @return The count of occurrences of the search value within the specified column of the matrix.
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
     * Sums the values in a specific column of a 2D matrix based on a specified search value.
     *
     * @param matrix      The 2D String array representing the matrix where values will be summed.
     * @param column      The integer value indicating the column from which values will be summed.
     * @param searchValue The String value to search for in the specified column.
     * @return The total sum of values in the specified column that match the search value.
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

    public static String askForString(String message) {
        Scanner input = new Scanner(System.in);

        System.out.print(message);
        return input.next().trim();

    }

    public static String[][] filterMatrix(String[][] matrix, int column, String value) {
        int count = utils.countValueInColumn(matrix, 3, "A01");
        String[][] filteredMatrix = new String[count][matrix[0].length];
        int filteredIndex = 0;
        
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][3].equals(value)){
                filteredMatrix[filteredIndex] = matrix[i];
                filteredIndex++;
            }
        }
        
        return filteredMatrix;
    }
}
