import java.io.FileNotFoundException;
import java.util.Scanner;

public class CodeSavanna {

    /**
     * Displays the login menu for CodeSavanna.
     * Allows users to select between Administrator and Client options for login.
     * Continues to prompt the user for input until '0' to exit is selected.
     */
    static void loginMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int loginOption;

        do {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                          CodeSavanna - Login                         |");
            System.out.println("+----------------------------------------------------------------------+");

            System.out.println("\nSelecione uma das opções de login abaixo:");
            System.out.println("1 - Administrador");
            System.out.println("2 - Cliente");
            System.out.println("0 - Sair");

            Scanner sc = new Scanner(System.in);
            loginOption = sc.nextInt();

            switch (loginOption) {
                case 1:
                    System.out.println("1 - Administrador");
                    if (utils.validLogin("admin")) {
                        adminMenu(animals, clients, interactions);
                    }
                    break;
                case 2:
                    System.out.println("2 - Cliente");
                    if (utils.validLogin("client")) {
                        clientMenu(animals, clients, interactions);
                    }
                    break;
                default:
                    System.out.println("0 - Sair");
                    break;
            }
            // TODO: Adicionar camada de login antes de apresentar o menu

        } while (loginOption != 0);

    }

    /**
     * Displays a menu for listing the contents of different files, such as 'animais', 'clientes', and 'interacoes'.
     *
     * @param animals      A 2D String array representing the data in the 'animais' file.
     * @param clients      A 2D String array representing the data in the 'clientes' file.
     * @param interactions A 2D String array representing the data in the 'interacoes' file.
     */
    static void printFileMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                     Menu Ficheiros - CodeSavanna                     |");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("1 - Listar conteúdo do ficheiro 'animais'");
            System.out.println("2 - Listar conteúdo do ficheiro 'clientes'");
            System.out.println("3 - Listar conteúdo do ficheiro 'interacoes'");
            System.out.println("0 - Voltar");
            System.out.println("+----------------------------------------------------------------------+");

            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Listar conteúdo do ficheiro 'animais'");
                    utils.printMatrix(animals);
                    break;
                case 2:
                    System.out.println("Listar conteúdo do ficheiro 'clientes'");
                    utils.printMatrix(clients);
                    break;
                case 3:
                    System.out.println("Listar conteúdo do ficheiro 'interacoes'");
                    utils.printMatrix(interactions);
                    break;
                default:
                    System.out.println("0 - Voltar");
                    break;
            }
        } while (option != 0);
    }


    private static void printInteractionsStats(String[][] interactions) {
        int visitCount = countByColumnValue(interactions, 2, "VISITA");
        int showCount = countByColumnValue(interactions, 2, "ESPETACULO");
        int feedCount = countByColumnValue(interactions, 2, "ALIMENTACAO");
        int sponsorCount = countByColumnValue(interactions, 2, "APADRINHAMENTO");

        // TODO: Melhorar layout da impressão
        System.out.println("Total de interações: " + (interactions.length - 1)); // Excluimos a linha com os cabeçalhos
        System.out.println("VISITA : " + visitCount);
        System.out.println("ESPETACULO : " + showCount);
        System.out.println("ALIMENTACAO : " + feedCount);
        System.out.println("APADRINHAMENTO: " + sponsorCount);
    }

    private static int countByColumnValue(String[][] matrix, int column, String groupCriteria) {

        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][column].equals(groupCriteria)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Displays the administrator menu with various options for managing system functionalities.
     * The method repeatedly prompts the user until a valid option is entered,
     * ranging from 0 to 9.
     * <p>
     * Menu options include:
     * 1 - List content of files
     * 2 - General interaction statistics
     * 3 - Total revenue by interaction type
     * 4 - Most popular animal
     * 5 - Top 3 species with the most sponsorships
     * 6 - List sponsors of an animal
     * 7 - Most profitable show
     * 8 - Endangered species ranking
     * 9 - Statistics by habitat
     * 0 - Exit
     *
     * @return An integer between 0 and 9 representing the user's menu selection.
     */
    private static void adminMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                       Menu ADMIN - CodeSavanna                       |");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("1 - Listar conteúdo dos ficheiros");
            System.out.println("2 - Estatísticas gerais de interações");
            System.out.println("3 - Receita total por tipo de interação");
            System.out.println("4 - Animal mais popular");
            System.out.println("5 - Top 3 espécies com mais apadrinhamentos");
            System.out.println("6 - Listar padrinhos de um animal");
            System.out.println("7 - Espetáculo mais rentável");
            System.out.println("8 - Ranking de animais em perigo de extinção");
            System.out.println("9 - Estatísticas por habitat");
            System.out.println("0 - Voltar");
            System.out.println("+----------------------------------------------------------------------+");

            Scanner sc = new Scanner(System.in);
            System.out.print("\nOpção: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printFileMenu(animals, clients, interactions);
                    break;
                case 2:
                    printInteractionsStats(interactions);
                    break;
                case 3:
                    System.out.println("3 - Receita total por tipo de interação");
                    break;
                case 4:
                    System.out.println("4 - Animal mais popular");
                    break;
                case 5:
                    System.out.println("5 - Top 3 espécies com mais apadrinhamentos");
                    break;
                case 6:
                    System.out.println("6 - Listar padrinhos de um animal");
                    break;
                case 7:
                    System.out.println("7 - Espetáculo mais rentável");
                    break;
                case 8:
                    System.out.println("8 - Ranking de animais em perigo de extinção");
                    break;
                case 9:
                    System.out.println("9 - Estatísticas por habitat");
                    break;
                default:
                    System.out.println("0 - Voltar");
                    break;
            }
        } while (option != 0);

    }

    /**
     * Displays the client menu with various options for the user to interact with.
     * The method repeatedly prompts the user until a valid option is entered
     * ranging from 0 to 5.
     * <p>
     * Options include:
     * 1 - Viewing animal catalog by habitat.
     * 2 - Viewing activities of an animal (shows and feedings).
     * 3 - Simulating an animal sponsorship.
     * 4 - Finding "zoo friends."
     * 5 - Playing a guessing game about the species.
     * 0 - Exit.
     *
     * @return An integer between 0 and 5 representing the user's menu selection.
     */
    private static void clientMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                      Menu CLIENTE - CodeSavanna                      |");
            System.out.println("+----------------------------------------------------------------------+");

            System.out.println("1 - Ver catálogo de animais por habitat");
            System.out.println("2 - Ver atividades de um animal (espetáculos e alimentações)");
            System.out.println("3 - Simular apadrinhamento de um animal");
            System.out.println("4 - Jogo: adivinha a espécie");
            System.out.println("0 - Voltar");
            System.out.println("+----------------------------------------------------------------------+");

            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("1 - Ver catálogo de animais por habitat");
                    break;
                case 2:
                    System.out.println("2 - Ver atividades de um animal (espetáculos e alimentações)");
                    break;
                case 3:
                    System.out.println("3 - Simular apadrinhamento de um animal");
                    break;
                case 4:
                    System.out.println("4 - Jogo: adivinha a espécie");
                    break;
                default:
                    System.out.println("0 - Voltar");
                    break;
            }

        } while (option != 0);

    }

    public static void main(String[] args) throws FileNotFoundException {
        String animalsFilePath = "files/animais.csv";
        String clientsFilePath = "files/clientes.csv";
        String interactionsFilePath = "files/interacoes.csv";

        String[][] animals = utils.readCsv(animalsFilePath, ";");
        String[][] clients = utils.readCsv(clientsFilePath, ";");
        String[][] interactions = utils.readCsv(interactionsFilePath, ";");

        System.out.println("Bem vindo ao CodeSavanna!");

        loginMenu(animals, clients, interactions);
    }
}
