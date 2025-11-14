package CodeSavannaUtils;

public class printUtils {

    public static void printWelcomeArt() {
        System.out.println("\n\n\n");
        System.out.println("        ___          _      __                                    ");
        System.out.println("       / __\\___   __| | ___/ _\\ __ ___   ____ _ _ __  _ __   __ _ ");
        System.out.println("      / /  / _ \\ / _` |/ _ \\ \\ / _` \\ \\ / / _` | '_ \\| '_ \\ / _` |");
        System.out.println("     / /__| (_) | (_| |  __/\\ \\ (_| |\\ V / (_| | | | | | | | (_| |");
        System.out.println("     \\____/\\___/ \\__,_|\\___\\__/\\__,_| \\_/ \\__,_|_| |_|_| |_|\\__,_|");
    }

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

    public static void printResultFooter() {
        System.out.println("+----------------------------------------------------------------------+");
    }

    public static void printExitBoard() {
        char copyrightSymbol = '\u00A9';
        System.out.println("\n\n");
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
        System.out.println("                 )__________|__|__________(");
        System.out.println("                |            ||            |");
        System.out.println("                |____________||____________|");
        System.out.println("                  ),-----.(      ),-----.(");
        System.out.println("                ,'   ==.   \\    /  .==    `.");
        System.out.println("               /            )  (            \\");
        System.out.println("               `==========='    `==========='  hjw");
        System.out.println("\n\n");
    }
    
}
