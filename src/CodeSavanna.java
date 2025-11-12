import java.util.Scanner;

public class CodeSavanna {

    /**
     * Displays a login menu with options for the user to select their login type.
     * The method repeatedly prompts the user until a valid option is entered.
     *
     * @return An integer corresponding to the selected login option:
     * 1 for "Administrador", 2 for "Cliente", and 0 for "Sair".
     */
    static int printLoginMenu() {
        int option;

        do {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                          CodeSavanna - Login                         |");
            System.out.println("+----------------------------------------------------------------------+");

            System.out.println("\nSelecione uma das opções de login abaixo:");
            System.out.println("1 - Administrador");
            System.out.println("2 - Cliente");
            System.out.println("0 - Sair");
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
        } while (option < 0 || option > 2);

        return option;
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
    private static int printAdminMenu() {
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
            System.out.println("0 - Sair");
            System.out.println("+----------------------------------------------------------------------+");

            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
        } while (option < 0 || option > 9);

        return option;
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
    private static int printClientMenu() {
        int option;

        do {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                      Menu CLIENTE - CodeSavanna                      |");
            System.out.println("+----------------------------------------------------------------------+");

            System.out.println("1 - Ver catálogo de animais por habitat");
            System.out.println("2 - Ver atividades de um animal (espetáculos e alimentações)");
            System.out.println("3 - Simular apadrinhamento de um animal");
            System.out.println("4 - Encontrar \"amigos de zoo\"");
            System.out.println("5 - Jogo: adivinha a espécie");
            System.out.println("0 - Sair");
            System.out.println("+----------------------------------------------------------------------+");

            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
        } while (option < 0 || option > 5);

        return option;
    }

    private static void executeAdminMenuOption(int option) {
        switch (option) {
            case 1:
                System.out.println("1 - Listar conteúdo dos ficheiros");
                break;
            case 2:
                System.out.println("2 - Estatísticas gerais de interações");
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
                System.out.println("0 - Sair");
                break;
        }
    }

    private static void executeClientMenuOption(int option) {
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
                System.out.println("4 - Encontrar \"amigos de zoo\"");
                break;
            case 5:
                System.out.println("5 - Jogo: adivinha a espécie");
                break;
            default:
                System.out.println("0 - Sair");
                break;
        }

    }

    /**
     * Executes an action based on the selected login option.
     * Prints a corresponding message to the console depending on the provided option.
     *
     * @param opcaoLogin The login option selected by the user.
     *                   1 represents "Administrador",
     *                   2 represents "Cliente",
     *                   any other value will result in exiting ("Saindo...").
     */
    private static void executeLoginOption(int opcaoLogin) {

        int menuOption;

        // TODO: Adicionar camada de login antes de apresentar o menu
        if (opcaoLogin == 1) {
            do {
                menuOption = printAdminMenu();
                executeAdminMenuOption(menuOption);
            } while (menuOption != 0);
        } else if (opcaoLogin == 2) {
            do {
                menuOption = printClientMenu();
                executeClientMenuOption(menuOption);
            } while (menuOption != 0);
        } else {
            System.out.println("Saindo...");
        }

    }

    static void main() {
        int loginOption;
        // TODO: Adicionar camada de login antes de apresentar o menu
        // TODO: No logoff, voltar ao menu de login
        System.out.println("Bem vindo ao CodeSavanna!");
        do {
            loginOption = printLoginMenu();
            executeLoginOption(loginOption);

        } while (loginOption != 0);
    }
}
