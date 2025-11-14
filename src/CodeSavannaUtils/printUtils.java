package CodeSavannaUtils;

public class printUtils {

    /**
     * Prints an artistic "Welcome" message in ASCII art to the console.
     * <p>
     * This method is intended to visually enhance the user experience
     * by displaying an aesthetically pleasing welcome banner when the
     * application starts. It serves as a decorative and welcoming element.
     * <p>
     * The banner includes the word "Welcome" styled in ASCII art.
     * It can be utilized to add a professional and visually engaging
     * intro screen for console-based applications.
     * <p>
     * Note: This method directly prints to the console and does not return
     * any values or perform any functional processing.
     */
    public static void printWelcomeArt() {
        System.out.println("\n\n\n");
        System.out.println("        ___          _      __                                    ");
        System.out.println("       / __\\___   __| | ___/ _\\ __ ___   ____ _ _ __  _ __   __ _ ");
        System.out.println("      / /  / _ \\ / _` |/ _ \\ \\ / _` \\ \\ / / _` | '_ \\| '_ \\ / _` |");
        System.out.println("     / /__| (_) | (_| |  __/\\ \\ (_| |\\ V / (_| | | | | | | | (_| |");
        System.out.println("     \\____/\\___/ \\__,_|\\___\\__/\\__,_| \\_/ \\__,_|_| |_|_| |_|\\__,_|");
    }

    /**
     * Prints a result section header with the provided title centered within a predefined
     * space and enclosed by a decorative border.
     * <p>
     * This method visually formats the title text to enhance readability and structure
     * in console outputs. The text is centered within a 70-character-wide boundary,
     * surrounded by a decorative frame.
     *
     * @param resultTitle The title text to display in the header. It is centered
     *                    within the available space of the decorative border.
     */
    public static void printResultHeader(String resultTitle) {
        int availableSpace = 70;
        int titleLenght = resultTitle.length();
        int spaceBefore = (availableSpace - titleLenght) / 2;
        int spaceAfter = availableSpace - spaceBefore - titleLenght;

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.print("|");
        for (int i = 0; i < spaceBefore; i++) {
            System.out.print(" ");
        }
        System.out.print(resultTitle);
        for (int i = 0; i < spaceAfter; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        System.out.println("+----------------------------------------------------------------------+");
    }

    /**
     * Prints a styled sub-header line with centered text.
     * <p>
     * This method formats a given sub-header text with leading and trailing dashes
     * to visually center the text within a predefined space. It enhances the
     * readability and structure of console-based outputs.
     *
     * @param subHeader The text to be displayed as the sub-header. It is centered
     *                  within the available space defined by the method.
     */
    public static void printResultSubHeader(String subHeader) {
        int availableSpace = 70;
        int subHeaderLenght = subHeader.length();
        int spaceBefore = (availableSpace - subHeaderLenght) / 2;
        int spaceAfter = availableSpace - spaceBefore - subHeaderLenght;

        for (int i = 0; i < spaceBefore; i++) {
            System.out.print("-");
        }
        System.out.print(" " + subHeader + " ");
        for (int i = 0; i < spaceAfter; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints interaction statistics for a given animal interaction list.
     * <p>
     * This method displays the title of the list and the interaction summary.
     * It provides information on whether there were no interactions, a single interaction,
     * or multiple interactions, along with the interaction's description.
     *
     * @param listTitle          The title of the interaction list to be displayed.
     * @param animalInteractions A two-dimensional array containing animal interaction data.
     *                           Each row represents an interaction, and the fifth column
     *                           contains the description of the interaction.
     */
    public static void printIteractionStats(String listTitle, String[][] animalInteractions) {
        System.out.println(listTitle);
        if (animalInteractions.length == 0) {
            System.out.println("- Não houve interações deste tipo para este animal.");
        } else if (animalInteractions.length == 1) {
            System.out.println("- " + animalInteractions[0][4] + " (1 vez)");
        } else {
            System.out.println("- " + animalInteractions[0][4] + " (" + animalInteractions.length + " vezes)");
        }
    }

    /**
     * Prints a decorative footer line to conclude a result section.
     * <p>
     * This method outputs a horizontal line consisting of dashes and plus symbols
     * to visually signify the end of a result display. It is intended as a
     * stylistic element for console-based output to enhance readability
     * and presentation.
     */
    public static void printResultFooter() {
        System.out.println("+----------------------------------------------------------------------+");
    }

    /**
     * Prints an artistic exit board to the console, including ASCII art and credits.
     * The method showcases a decorative presentation for the application's exit message,
     * providing acknowledgment to contributors and project references.
     * <p>
     * It includes:
     * - Decorative ASCII artwork.
     * - Credits for the development team and the supporting organization.
     * - References to relevant resources such as GitHub repositories.
     * - Display of copyright information.
     * <p>
     * This method is purely for aesthetic and informational display and serves no functional computational logic.
     */
    public static void printExitBoard() {
        char copyrightSymbol = '©';
        System.out.println();
        System.out.println("                      ,---.           ,---.");
        System.out.println("                     / /\"`.\\.--\"\"\"--./,'\"\\ \\");
        System.out.println("                     \\ \\    _       _    / /");
        System.out.println("                      `./  / __   __ \\  \\,'");
        System.out.println("                       /    /_O)_(_O\\    \\");
        System.out.println("                       |  .-'  ___  `-.  |");
        System.out.println("                    .--|       \\_/       |--.");
        System.out.println("                  ,'    \\   \\   |   /   /    `.");
        System.out.println("                 /       `.  `--^--'  ,'       \\");
        System.out.println("              .-\"\"\"\"\"-.    `--.___.--'     .-\"\"\"\"\"-.");
        System.out.println(".------------/         \\------------------/         \\-------------.");
        System.out.println("| .----------\\         /----------------- \\         /-----------. |");
        System.out.println("| |           `-`--`--'                    `--'--'-'            | |");
        System.out.println("| |                                                             | |");
        System.out.println("| |             CESAE Digital - Software Developer              | |");
        System.out.println("| |                                                             | |");
        System.out.println("| |        Leandro de Assis Gabriel                             | |");
        System.out.println("| |        Github: https://github.com/lassisg                   | |");
        System.out.println("| |                                                             | |");
        System.out.println("| |        Formador: Vitor Santos                               | |");
        System.out.println("| |        https://github.com/CESAE-Dev-2025/CodeSavanna        | |");
        System.out.println("| |                                                             | |");
        System.out.println("| |        Copyright" + copyrightSymbol + " 2025                                      | |");
        System.out.println("| |                                                             | |");
        System.out.println("| |_____________________________________________________________| |");
        System.out.println("|_________________________________________________________________|");
        System.out.println("                   )__________|__|__________(");
        System.out.println("                  |            ||            |");
        System.out.println("                  |____________||____________|");
        System.out.println("                    ),-----.(      ),-----.(");
        System.out.println("                  ,'   ==.   \\    /  .==    `.");
        System.out.println("                 /            )  (            \\");
        System.out.println("                 `==========='    `==========='  hjw");
        System.out.println("\n\n");
    }

}
