import java.io.FileNotFoundException;
import java.util.Scanner;

public class CodeSavanna {

    /**
     * Displays a login menu for users to select their login option (administrator or client).
     *
     * @param animals      A 2D String array representing the data in the 'animais' file.
     * @param clients      A 2D String array representing the data in the 'clientes' file.
     * @param interactions A 2D String array representing the data in the 'interacoes' file.
     */
    static void loginMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int loginOption;

        do {
            System.out.println();
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                          CodeSavanna - Login                         |");
            System.out.println("+----------------------------------------------------------------------+");

            System.out.println("\nSelecione uma das opções de login abaixo:");
            System.out.println("1 - Administrador");
            System.out.println("2 - Cliente");
            System.out.println("0 - Sair");

            Scanner sc = new Scanner(System.in);
            System.out.print("\nOpção: ");
            loginOption = sc.nextInt();

            switch (loginOption) {
                case 1:
                    if (utils.validLogin("admin")) {
                        adminMenu(animals, clients, interactions);
                    }
                    break;
                case 2:
                    if (utils.validLogin("client")) {
                        clientMenu(animals, clients, interactions);
                    }
                    break;
                default:
                    System.out.println("0 - Sair");
                    break;
            }

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

    /**
     * Displays statistics related to the income generated from different types of interactions based on the given 2D array.
     *
     * @param interactions A 2D array representing data on interactions.
     *                     Each row contains information about a specific interaction, where the third column (index 2) represents the type of interaction.
     *                     The available types considered for income calculation are: VISITA, ESPETACULO, ALIMENTACAO, and APADRINHAMENTO.
     *                     The method calculates and prints the total income along with the income generated from each type of interaction.
     */
    static void printInteractionsIncomeStats(String[][] interactions) {
        double visitIncome = utils.sumValueByCriteria(interactions, 2, "VISITA");
        double showIncome = utils.sumValueByCriteria(interactions, 2, "ESPETACULO");
        double feedIncome = utils.sumValueByCriteria(interactions, 2, "ALIMENTACAO");
        double sponsorIncome = utils.sumValueByCriteria(interactions, 2, "APADRINHAMENTO");
        double totalIncome = visitIncome + showIncome + feedIncome + sponsorIncome;

        // TODO: Melhorar layout da impressão
        System.out.println("Total de receitas: " + totalIncome);
        System.out.println("VISITA: " + visitIncome);
        System.out.println("ESPETACULO: " + showIncome);
        System.out.println("ALIMENTACAO: " + feedIncome);
        System.out.println("APADRINHAMENTO: " + sponsorIncome);
    }

    /**
     * Prints statistics about different types of interactions based on the given 2D array.
     *
     * @param interactions A 2D array representing interactions data.
     */
    static void printInteractionsStats(String[][] interactions) {
        int visitCount = utils.searchValueInColumn(interactions, 2, "VISITA");
        int showCount = utils.searchValueInColumn(interactions, 2, "ESPETACULO");
        int feedCount = utils.searchValueInColumn(interactions, 2, "ALIMENTACAO");
        int sponsorCount = utils.searchValueInColumn(interactions, 2, "APADRINHAMENTO");

        // TODO: Melhorar layout da impressão
        System.out.println("Total de interações: " + (interactions.length - 1)); // Excluimos a linha com os cabeçalhos
        System.out.println("VISITA: " + visitCount);
        System.out.println("ESPETACULO: " + showCount);
        System.out.println("ALIMENTACAO: " + feedCount);
        System.out.println("APADRINHAMENTO: " + sponsorCount);
    }

    public static void printMostPopularAnimal(String[][] animals, String[][] interactions) {
        int[] animalInteractions = new int[animals.length - 1]; // Salta a linha do cabeçalho

        int countIndex = 0;
        for (int i = 1; i < animals.length; i++) { // Salta a linha de cabeçalho
            int interactionCount = 0;
            for (int j = 1; j < interactions.length; j++) { // Salta a linha do cabeçalho
                if (animals[i][0].equals(interactions[j][3])) {
                    interactionCount++;
                }
            }
            animalInteractions[countIndex++] = interactionCount;
        }

        int maxInteractions = animalInteractions[0];
        int mostPopularIndex = 0;
        for (int i = 1; i < animalInteractions.length; i++) {
            if (animalInteractions[i] > maxInteractions) {
                maxInteractions = animalInteractions[i];
                mostPopularIndex = i + 1; // Adiciona a linha do cabeçalho
            }
        }

        System.out.println("\nAnimal mais popular: " + animals[mostPopularIndex][1] + " (" + maxInteractions + " interações)");
        System.out.println("Espécie: " + animals[mostPopularIndex][2]);
        System.out.println("Habitat: " + animals[mostPopularIndex][3]);
    }

    /**
     * Displays the admin menu with various options for administrative tasks.
     *
     * @param animals      A 2D String array representing the data of animals.
     * @param clients      A 2D String array representing the data of clients.
     * @param interactions A 2D String array representing the data of interactions.
     */
    static void adminMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            System.out.println();
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
                    printInteractionsIncomeStats(interactions);
                    break;
                case 4:
                    System.out.println("4 - Animal mais popular");
                    printMostPopularAnimal(animals, interactions);
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
     * Displays the client menu options for CodeSavanna.
     *
     * @param animals      A 2D String array representing the animal data.
     * @param clients      A 2D String array representing the client data.
     * @param interactions A 2D String array representing the interactions data.
     */
    static void clientMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            System.out.println();
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
            System.out.print("\nOpção: ");
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
