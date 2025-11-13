package CodeSavannaUtils;

public class utils {
    public static void printResultHeader(String resultTitle) {
        int availableSpace = 70;
        int titleLenght = resultTitle.length();
        int spaceBefore = (availableSpace - titleLenght) / 2;
        int spaceAfter = availableSpace - spaceBefore - titleLenght;
        
        
        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
//        System.out.println("|               Ranking de animais em perigo de extinção               |");
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
}
