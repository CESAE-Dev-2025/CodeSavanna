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

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                  Receita total por tipo de interação                 |");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("%-19s", "Total de receitas:");
        System.out.println(totalIncome);

        System.out.printf("%-19s", "VISITA:");
        System.out.println("VISITA: " + visitIncome);

        System.out.printf("%-19s", "ESPETACULO:");
        System.out.println("ESPETACULO: " + showIncome);

        System.out.printf("%-19s", "ALIMENTACAO:");
        System.out.println("ALIMENTACAO: " + feedIncome);

        System.out.printf("%-19s", "APADRINHAMENTO:");
        System.out.println("APADRINHAMENTO: " + sponsorIncome);
        System.out.println("+----------------------------------------------------------------------+");
    }

    /**
     * Prints statistics about different types of interactions based on the given 2D array.
     *
     * @param interactions A 2D array representing interactions data.
     */
    static void printInteractionsStats(String[][] interactions) {
        int visitCount = utils.countValueInColumn(interactions, 2, "VISITA");
        int showCount = utils.countValueInColumn(interactions, 2, "ESPETACULO");
        int feedCount = utils.countValueInColumn(interactions, 2, "ALIMENTACAO");
        int sponsorCount = utils.countValueInColumn(interactions, 2, "APADRINHAMENTO");

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                   Estatísticas gerais de interações                  |");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("%-21s", "Total de interações: :");
        System.out.println(interactions.length - 1); // Excluimos a linha com os cabeçalhos

        System.out.printf("%-21s", "APADRINHAMENTO:");
        System.out.println(visitCount);

        System.out.printf("%-21s", "ESPETACULO:");
        System.out.println(showCount);

        System.out.printf("%-21s", "ALIMENTACAO:");
        System.out.println(feedCount);

        System.out.printf("%-21s", "APADRINHAMENTO:");
        System.out.println(sponsorCount);
        System.out.println("+----------------------------------------------------------------------+");
    }

    /**
     * Determines the most popular animal based on the number of interactions recorded.
     *
     * @param animals      A 2D array of strings representing the data of animals.
     *                     Each row contains information about a specific animal, where the second column (index 1) represents the name of the animal.
     * @param interactions A 2D array of strings representing data on interactions between animals and other entities.
     *                     Each row contains information about a specific interaction, where the fourth column (index 3) represents the animal involved in the interaction.
     */
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

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                          Animal mais popular                         |");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("%-12s", "Nome:");
        System.out.println(animals[mostPopularIndex][1]);

        System.out.printf("%-12s", "Espécie:");
        System.out.println(animals[mostPopularIndex][2]);

        System.out.printf("%-12s", "Habitat:");
        System.out.println(animals[mostPopularIndex][3]);

        System.out.printf("%-12s", "Interações:");
        System.out.println(maxInteractions);
        System.out.println("------------");
    }

    /**
     * Extracts an array of unique species names from a 2D array of animal information.
     * The input array contains animal records where each row represents an animal
     * with details such as name, type, and species.
     *
     * @param animals a 2D array where each row represents an animal and columns represent its details.
     *                The third column (index 2) is expected to contain the species information.
     * @return an array containing unique species names found in the input array.
     */
    static String[] getSpecies(String[][] animals) {
        int uniqueSpeciesCount = 0;
        boolean uniqueSpeciesFound;
        for (int i = 1; i < animals.length; i++) {
            uniqueSpeciesFound = true;
            for (int j = 1; j < i; j++) {
                if (i != j && animals[i][2].equals(animals[j][2])) {
                    uniqueSpeciesFound = false;
                    j = i;
                }
            }
            if (uniqueSpeciesFound) {
                uniqueSpeciesCount++;
            }

        }

        String[] species = new String[uniqueSpeciesCount];
        int speciesIndex = 0;
        for (int i = 1; i < animals.length; i++) {
            uniqueSpeciesFound = true;
            for (int j = 1; j < i; j++) {
                if (i != j && animals[i][2].equals(animals[j][2])) {
                    uniqueSpeciesFound = false;
                    j = i;
                }
            }
            if (uniqueSpeciesFound) {
                species[speciesIndex] = animals[i][2];
                speciesIndex++;
            }

        }

        return species;
    }

    /**
     * Prints the top 3 most sponsored species based on the provided animal and interaction data.
     * The method analyzes sponsorship interactions of animals, calculates the total sponsorship count
     * and income for each species, and then lists the top 3 species with the highest sponsorship counts.
     *
     * @param animals      a 2D array where each row represents an animal and its details.
     *                     The format for each row is expected to include at least:
     *                     - Column 0: Animal ID
     *                     - Column 2: Species name
     * @param interactions a 2D array where each row represents an interaction between a sponsor
     *                     and an animal. The format for each row is expected to include at least:
     *                     - Column 2: Interaction type (e.g., "APADRINHAMENTO")
     *                     - Column 3: Target animal ID
     *                     - Column 5: Amount paid
     */
    static void printTopSponsoredSpecies(String[][] animals, String[][] interactions) {
        String[] species = getSpecies(animals);
        int[] sponsorCount = new int[species.length];
        double[] specieIncome = new double[species.length];

        for (int i = 0; i < species.length; i++) {
            // Para cada espécie, buscar animais
            for (int j = 1; j < animals.length; j++) {
                if (animals[j][2].equals(species[i])) {
                    // Para cada animal da espécie, buscar interações do tipo 'APADRINHAMENTO'
                    String[][] currentAnimalInteractions = filterByAnimalAndInteractionType(interactions, 3, animals[j][0], 2, "APADRINHAMENTO");
                    for (int k = 0; k < currentAnimalInteractions.length; k++) {
                        //Para cada interação do tipo 'APADRINHAMENTO' de cada animal da espécie, acumular valorPago e contar padrinhos
                        specieIncome[i] += Double.parseDouble(currentAnimalInteractions[k][5]);
                        sponsorCount[i]++;
                    }
                }
            }
        }

        // TODO: Tentei fazer por funções, mas o array original era ordenado na primeira chamada

        // int[] sortedSponsorCount = utils.sortDescending(sponsorCount);
        // String[] sortedSpecies = utils.sortDescendingByReference(species, sponsorCount);
        // double[] sortedSpecieIncome = sortDescendingByReference(specieIncome, sponsorCount);

        String speciesTemp;
        int sponsorCountTemp;
        double specieIncomeTemp;

        for (int i = sponsorCount.length - 1; i >= 0; i--) {

            for (int j = sponsorCount.length - 1; j >= 0; j--) {
                if (i != j && sponsorCount[i] < sponsorCount[j]) {
                    sponsorCountTemp = sponsorCount[j];
                    sponsorCount[j] = sponsorCount[i];
                    sponsorCount[i] = sponsorCountTemp;

                    speciesTemp = species[j];
                    species[j] = species[i];
                    species[i] = speciesTemp;

                    specieIncomeTemp = specieIncome[j];
                    specieIncome[j] = specieIncome[i];
                    specieIncome[i] = specieIncomeTemp;
                }
            }

        }

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                   Top 3 espécies mais apadrinhadas                   |");
        System.out.println("+----------------------------------------------------------------------+");

        // Imprimir o Top 3
        int topSponsoredToShow = 3;
        if (species.length < 3) {
            topSponsoredToShow = species.length;
        }

        for (int i = 0; i < topSponsoredToShow; i++) {
            System.out.println();
            System.out.println((i + 1) + ") " + species[i]);

            System.out.printf("%-23s", "No de apadrinhamentos:");
            System.out.println(sponsorCount[i]);

            System.out.printf("%-23s", "Valor mensal total:");
            System.out.println(specieIncome[i] + " €");

            System.out.println("----------------------");
        }

    }

    /**
     * Prompts the user to input the ID of an animal and verifies its existence within the given 2D array of animals.
     * If the provided ID is not found, the user will be repeatedly prompted until a valid ID is entered.
     *
     * @param animals A 2D String array representing the data of animals. Each row contains information about a specific animal,
     *                where the first column (index 0) is assumed to store the IDs of the animals.
     * @return The valid animal ID entered by the user.
     */
    static String getValidAnimal(String[][] animals) {
        Scanner input = new Scanner(System.in);
        String selectedAnimal;
        boolean animalExists;

        do {
            System.out.print("Digite o ID do animal a buscar: ");
            selectedAnimal = input.next().trim().toUpperCase();     // TODO: Pode usar toUpperCase() ou toLowerCase()?
            animalExists = utils.existsInMatrix(animals, 0, selectedAnimal);
        } while (!animalExists);

        return selectedAnimal;
    }

    /**
     * Filters the given interactions array to include only rows where the specified
     * animal column matches the given animal value and the specified interaction
     * type column matches the given interaction type value.
     *
     * @param interactions         A 2D String array representing the data of interactions.
     *                             Each row contains information about a specific interaction.
     * @param animalColumn         The index of the column in the interactions array that
     *                             corresponds to the animal's attribute to be filtered by.
     * @param animalValue          The value to match in the specified animal column.
     * @param iteractionTypeColumn The index of the column in the interactions array
     *                             that corresponds to the interaction type to be filtered by.
     * @param interactionTypeValue The value to match in the specified interaction
     *                             type column.
     * @return A filtered 2D String array containing only the rows that match both
     * the animal value in the specified column and the interaction type
     * value in the specified column.
     */
    public static String[][] filterByAnimalAndInteractionType(String[][] interactions, int animalColumn, String animalValue, int iteractionTypeColumn, String interactionTypeValue) {
        int count = 0;

        for (int i = 1; i < interactions.length; i++) {
            if (interactions[i][animalColumn].equals(animalValue) && interactions[i][iteractionTypeColumn].equals(interactionTypeValue)) {
                count++;
            }
        }

        String[][] filteredMatrix = new String[count][interactions[0].length];
        int filteredIndex = 0;

        for (int i = 1; i < interactions.length; i++) {
            if (interactions[i][animalColumn].equals(animalValue) && interactions[i][iteractionTypeColumn].equals(interactionTypeValue)) {
                filteredMatrix[filteredIndex] = interactions[i];
                filteredIndex++;
            }
        }

        return filteredMatrix;
    }

    /**
     * Displays the list of sponsors for a specific animal. The method prompts the user to select an animal,
     * filters the interactions to find only sponsorships for that animal, and retrieves the details of each sponsor.
     * If no sponsors are found for the selected animal, a message is displayed.
     *
     * @param animals      A 2D String array representing the data of animals. Each row contains information
     *                     about a specific animal, where the first column (index 0) stores the animal ID.
     * @param interactions A 2D String array representing data on interactions between animals and clients.
     *                     Each row contains information about a specific interaction, where columns represent
     *                     attributes such as interaction type, related animal ID, and associated client ID.
     * @param clients      A 2D String array representing the data of clients. Each row contains information
     *                     about a specific client, where the first column (index 0) stores the client ID,
     *                     and other columns store additional client details such as name and email.
     */
    static void printAnimalSponsors(String[][] animals, String[][] interactions, String[][] clients) {

        String selectedAnimal = getValidAnimal(animals);
        String[][] selectedInteractions = filterByAnimalAndInteractionType(interactions, 3, selectedAnimal, 2, "APADRINHAMENTO");

        if (selectedInteractions.length == 0) {
            System.out.println("Não há padrinhos para este animal.");
            return;
        }

        String animalName = utils.findValueAtColumn(animals, 0, selectedAnimal, 1);

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                     Listar padrinhos de um animal                    |");
        System.out.println("+----------------------------------------------------------------------+");

        System.out.println("Lista de padrinhos para o animal " + selectedAnimal + " (" + animalName + "):");

        for (int i = 0; i < selectedInteractions.length; i++) {
            String clientName = utils.findValueAtColumn(clients, 0, selectedInteractions[i][1], 1);
            String clientEmail = utils.findValueAtColumn(clients, 0, selectedInteractions[i][1], 3);

            System.out.println();
            System.out.printf("%-25s", "Nome do cliente:");
            System.out.println(clientName);

            System.out.printf("%-25s", "Email do cliente:");
            System.out.println(clientEmail);

            System.out.printf("%-25s", "Valor mensal pago:");
            System.out.println(selectedInteractions[i][5] + " €");

            System.out.printf("%-25s", "Plano de apadrinhamento:");
            System.out.println(selectedInteractions[i][4]);
            System.out.println("------------------------");
        }
    }

    static String[][] filterByInteractionType(String[][] interactions, int iteractionTypeColumn, String interactionTypeValue) {
        int count = 0;

        for (int i = 1; i < interactions.length; i++) {
            if (interactions[i][iteractionTypeColumn].equals(interactionTypeValue)) {
                count++;
            }
        }

        String[][] filteredMatrix = new String[count][interactions[0].length];
        int filteredIndex = 0;

        for (int i = 1; i < interactions.length; i++) {
            if (interactions[i][iteractionTypeColumn].equals(interactionTypeValue)) {
                filteredMatrix[filteredIndex] = interactions[i];
                filteredIndex++;
            }
        }

        return filteredMatrix;
    }

    private static String[] getUniqueShows(String[][] interactions, int searchColumn, String searchValue) {
        int uniqueShowsCount = 0;
        boolean uniqueShowsFound;
        for (int i = 1; i < interactions.length; i++) {

            if (interactions[i][searchColumn].equals(searchValue)) {

                uniqueShowsFound = true;
                for (int j = 1; j < i; j++) {
                    if (i != j && interactions[i][4].equals(interactions[j][4])) {
                        uniqueShowsFound = false;
                        j = i;
                    }
                }
                if (uniqueShowsFound) {
                    uniqueShowsCount++;
                }
            }

        }

        String[] shows = new String[uniqueShowsCount];
        int showsIndex = 0;
        for (int i = 1; i < interactions.length; i++) {

            if (interactions[i][searchColumn].equals(searchValue)) {
                uniqueShowsFound = true;
                for (int j = 1; j < i; j++) {
                    if (i != j && interactions[i][4].equals(interactions[j][4])) {
                        uniqueShowsFound = false;
                        j = i;
                    }
                }
                if (uniqueShowsFound) {
                    shows[showsIndex] = interactions[i][4];
                    showsIndex++;
                }

            }
        }

        return shows;
    }

    static double[] getShowsIncome(String[][] interactions, String[] shows) {
        double[] showsIncome = new double[shows.length];

        for (int i = 0; i < shows.length; i++) {

            for (int j = 1; j < interactions.length; j++) {
                if (shows[i].equals(interactions[j][4])) {
                    showsIncome[i] += Double.parseDouble(interactions[j][5]);
                }
            }

        }

        return showsIncome;
    }

    private static int getMostValuableShowIndex(double[] showsIncome) {

        int greaterIndex = 0;
        double greater = showsIncome[greaterIndex];

        for (int i = 1; i < showsIncome.length; i++) {
            if (showsIncome[i] > greater) {
                greater = showsIncome[i];
                greaterIndex = i;
            }
        }

        return greaterIndex;
    }

    static String[][] getMostValuableAnimal(String[][] animals, String animalId) {
        String[][] valuableAnimal = new String[1][animals[0].length];

        for (int i = 1; i < animals.length; i++) {
            if (animals[i][0].equals(animalId)) {
                valuableAnimal[0] = animals[i];
            }
        }

        return valuableAnimal;
    }

    private static String getAnimalIdFromShowName(String[][] interactions, String show) {
        String animalId = "";

        for (int i = 1; i < interactions.length; i++) {
            if (interactions[i][4].equals(show)) {
                animalId = interactions[i][3];
                i = interactions.length;
            }
        }

        return animalId;
    }

    static void printMostRantableShow(String[][] interactions, String[][] animals) {
        String[] shows = getUniqueShows(interactions, 2, "ESPETACULO");
        double[] showsIncome = getShowsIncome(interactions, shows);
        int mostValuableShowIndex = getMostValuableShowIndex(showsIncome);
        String animalId = getAnimalIdFromShowName(interactions, shows[mostValuableShowIndex]);
        String[][] animal = getMostValuableAnimal(animals, animalId);

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                       Espetáculo mais rentável                       |");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("%-20s", "Nome do espetáculo:");
        System.out.println(shows[mostValuableShowIndex]);

        System.out.printf("%-20s", "Receita total:");
        System.out.println(showsIncome[mostValuableShowIndex] + " €");

        System.out.printf("%-20s", "Animal principal:");
        System.out.println();
        System.out.printf("%-11s", "- Nome:");
        System.out.println(animal[0][1]);

        System.out.printf("%-11s", "- Espécie:");
        System.out.println(animal[0][2]);
        System.out.println("--------------------");
    }

    private static int[] getExtintionAnimalsInteractions(String[][] interactions, String[][] extintionAnimals) {

        int[] animalInteractions = new int[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalInteractions[i] = utils.countValueInColumn(interactions, 3, extintionAnimals[i][0]);
        }
        return animalInteractions;
    }

    private static double[] getExtintionAnimalsIncomes(String[][] interactions, String[][] extintionAnimals) {

        double[] animalIncome = new double[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalIncome[i] = utils.sumValueByCriteria(interactions, 3, extintionAnimals[i][0]);
        }
        return animalIncome;
    }

    private static void printExtintionRank(String[][] animals, String[][] interactions) {
        String[][] extintionAnimals = utils.filterMatrix(animals, 5, "SIM");
        int[] animalInteractions = getExtintionAnimalsInteractions(interactions, extintionAnimals);
        double[] animalIncome = getExtintionAnimalsIncomes(interactions, extintionAnimals);

        // TODO: Tentei fazer por funções, mas o array original era ordenado na primeira chamada

        String[] extintionAnimalsTemp;
        int animalInteractionsTemp;
        double animalIncomeTemp;

        for (int i = animalIncome.length - 1; i >= 0; i--) {

            for (int j = animalIncome.length - 1; j >= 0; j--) {
                if (i != j && animalIncome[i] < animalIncome[j]) {
                    animalIncomeTemp = animalIncome[j];
                    animalIncome[j] = animalIncome[i];
                    animalIncome[i] = animalIncomeTemp;

                    animalInteractionsTemp = animalInteractions[j];
                    animalInteractions[j] = animalInteractions[i];
                    animalInteractions[i] = animalInteractionsTemp;

                    extintionAnimalsTemp = extintionAnimals[j];
                    extintionAnimals[j] = extintionAnimals[i];
                    extintionAnimals[i] = extintionAnimalsTemp;
                }
            }

        }

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|               Ranking de animais em perigo de extinção               |");
        System.out.println("+----------------------------------------------------------------------+");

        for (int i = 0; i < extintionAnimals.length; i++) {

            System.out.println();
            System.out.println((i + 1) + ") " + extintionAnimals[i][1]);

            System.out.printf("%-20s", "Espécie:");
            System.out.println(extintionAnimals[i][2]);

            System.out.printf("%-20s", "Habitat:");
            System.out.println(extintionAnimals[i][3]);

            System.out.printf("%-20s", "Dieta:");
            System.out.println(extintionAnimals[i][4]);

            System.out.printf("%-20s", "Total de interação:");
            System.out.println(animalInteractions[i]);

            System.out.printf("%-20s", "Total de receita:");
            System.out.println(animalIncome[i] + " €");
            System.out.println("-------------------");
        }
    }


    private static String[] getHabitats(String[][] animals) {
        int uniqueHabitatsCount = 0;
        boolean uniquehabitatsFound;
        for (int i = 1; i < animals.length; i++) {
            uniquehabitatsFound = true;
            for (int j = 1; j < i; j++) {
                if (i != j && animals[i][3].equals(animals[j][3])) {
                    uniquehabitatsFound = false;
                    j = i;
                }
            }
            if (uniquehabitatsFound) {
                uniqueHabitatsCount++;
            }

        }

        String[] habitats = new String[uniqueHabitatsCount];
        int habitatsIndex = 0;
        for (int i = 1; i < animals.length; i++) {
            uniquehabitatsFound = true;
            for (int j = 1; j < i; j++) {
                if (i != j && animals[i][3].equals(animals[j][3])) {
                    uniquehabitatsFound = false;
                    j = i;
                }
            }
            if (uniquehabitatsFound) {
                habitats[habitatsIndex] = animals[i][3];
                habitatsIndex++;
            }

        }

        return habitats;
    }

    private static int getHabitatInteractions(String[][] interactions, String[][] habitatAnimals) {
        int interactionCount = 0;

        for (int i = 0; i < habitatAnimals.length; i++) {
            for (int j = 1; j < interactions.length; j++) {
                if (interactions[j][3].equals(habitatAnimals[i][0])) {
                    interactionCount++;
                }
            }
        }
        return interactionCount;
    }

    private static double getHabitatIncome(String[][] interactions, String[][] habitatAnimals) {
        double interactionIncome = 0;

        for (int i = 0; i < habitatAnimals.length; i++) {
            for (int j = 1; j < interactions.length; j++) {
                if (interactions[j][3].equals(habitatAnimals[i][0])) {
                    interactionIncome += Double.parseDouble(interactions[j][5]);
                }
            }
        }
        return interactionIncome;
    }

    private static void printHabitatStats(String[][] animals, String[][] interactions) {
        /*
        9. Estatísticas por habitat
        Para cada habitat presente no ficheiro animais.csv:
            • Contar quantos animais nesse habitat.
            • Contar quantas interações totais existem com animais desse habitat.
            • Somar o valorPago total associado aos animais desse habitat.
        * */
        String[] habitats = getHabitats(animals);

//        String[][] extintionAnimals = utils.filterMatrix(animals, 5, "SIM");
//        int[] animalInteractions = getExtintionAnimalsInteractions(interactions, extintionAnimals);
//        double[] animalIncome = getExtintionAnimalsIncomes(interactions, extintionAnimals);

        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                       Estatísticas por habitat                       |");
        System.out.println("+----------------------------------------------------------------------+");

        for (int i = 0; i < habitats.length; i++) {

            String[][] habitatAnimals = utils.filterMatrix(animals, 3, habitats[i]);
            int habitatInteractions = getHabitatInteractions(interactions, habitatAnimals);
            double habitatIncome = getHabitatIncome(interactions, habitatAnimals);

            System.out.println();
            System.out.printf("%-19s", "Habitat:");
            System.out.println(habitats[i]);

            System.out.printf("%-19s", "Nº de animais:");
            System.out.println(habitatAnimals.length);

            System.out.printf("%-19s", "Nº de interações:");
            System.out.println(habitatInteractions);

            System.out.printf("%-19s", "Receita associada:");
            System.out.println(habitatIncome + " €");
            System.out.println("------------------");
        }
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
                    printMostPopularAnimal(animals, interactions);
                    break;
                case 5:
                    printTopSponsoredSpecies(animals, interactions);
                    break;
                case 6:
                    printAnimalSponsors(animals, interactions, clients);
                    break;
                case 7:
                    printMostRantableShow(interactions, animals);
                    break;
                case 8:
                    printExtintionRank(animals, interactions);
                    break;
                case 9:
                    printHabitatStats(animals, interactions);
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
