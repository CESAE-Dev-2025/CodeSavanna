import java.io.FileNotFoundException;
import java.util.Scanner;

import static CodeSavannaUtils.utils.*;
import static CodeSavannaUtils.utils.printResultHeader;

public class CodeSavanna {

    static void printFileMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            printResultHeader("CodeSavanna - Menu Ficheiros");

            System.out.println("1 - Listar conteúdo do ficheiro 'animais'");
            System.out.println("2 - Listar conteúdo do ficheiro 'clientes'");
            System.out.println("3 - Listar conteúdo do ficheiro 'interacoes'");
            System.out.println("0 - Voltar");

            Scanner sc = new Scanner(System.in);
            System.out.print("\nOpção: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printResultHeader("Listar conteúdo do ficheiro 'animais'");
                    printMatrix(animals);
                    break;
                case 2:
                    printResultHeader("Listar conteúdo do ficheiro 'clientes'");
                    printMatrix(clients);
                    break;
                case 3:
                    printResultHeader("Listar conteúdo do ficheiro 'interacoes'");
                    printMatrix(interactions);
                    break;
                case 0:
                    //System.out.println("0 - Voltar");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
    }

    static void printInteractionsIncomeStats(String[][] interactions) {
        double visitIncome = sumValueByCriteria(interactions, 2, "VISITA");
        double showIncome = sumValueByCriteria(interactions, 2, "ESPETACULO");
        double feedIncome = sumValueByCriteria(interactions, 2, "ALIMENTACAO");
        double sponsorIncome = sumValueByCriteria(interactions, 2, "APADRINHAMENTO");
        double totalIncome = visitIncome + showIncome + feedIncome + sponsorIncome;

        printResultHeader("Receita total por tipo de interação");

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

        printResultFooter();
    }

    static void printInteractionsStats(String[][] interactions) {
        int visitCount = countValueInColumn(interactions, 2, "VISITA");
        int showCount = countValueInColumn(interactions, 2, "ESPETACULO");
        int feedCount = countValueInColumn(interactions, 2, "ALIMENTACAO");
        int sponsorCount = countValueInColumn(interactions, 2, "APADRINHAMENTO");

        printResultHeader("Estatísticas gerais de interações");

        System.out.printf("%-21s", "Total de interações:");
        System.out.println(interactions.length - 1); // Excluimos a linha com os cabeçalhos

        System.out.printf("%-21s", "APADRINHAMENTO:");
        System.out.println(visitCount);

        System.out.printf("%-21s", "ESPETACULO:");
        System.out.println(showCount);

        System.out.printf("%-21s", "ALIMENTACAO:");
        System.out.println(feedCount);

        System.out.printf("%-21s", "APADRINHAMENTO:");
        System.out.println(sponsorCount);

        printResultFooter();
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

        printResultHeader("Animal mais popular");

        System.out.printf("%-12s", "Nome:");
        System.out.println(animals[mostPopularIndex][1]);

        System.out.printf("%-12s", "Espécie:");
        System.out.println(animals[mostPopularIndex][2]);

        System.out.printf("%-12s", "Habitat:");
        System.out.println(animals[mostPopularIndex][3]);

        System.out.printf("%-12s", "Interações:");
        System.out.println(maxInteractions);

        printResultFooter();
    }

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

        int[] sortedSponsorCount = sortIntArrayDescending(sponsorCount);
        String[] sortedSpecies = sortStringArrayDescendingByReference(species, sponsorCount);
        double[] sortedSpecieIncome = sortDoubleArrayDescending(specieIncome);

        printResultHeader("Top 3 espécies mais apadrinhadas");

        // Imprimir o Top 3
        int topSponsoredToShow = 3;
        if (sortedSpecies.length < 3) {
            topSponsoredToShow = sortedSpecies.length;
        }

        for (int i = 0; i < topSponsoredToShow; i++) {
            System.out.println();
            System.out.println((i + 1) + ") " + sortedSpecies[i]);

            System.out.printf("%-23s", "No de apadrinhamentos:");
            System.out.println(sortedSponsorCount[i]);

            System.out.printf("%-23s", "Valor mensal total:");
            System.out.println(sortedSpecieIncome[i] + " €");

            printResultFooter();
        }

    }

    static String getValidAnimal(String[][] animals) {
        Scanner input = new Scanner(System.in);
        String selectedAnimal;
        boolean animalExists;

        do {
            System.out.print("Digite o ID do animal: ");
            selectedAnimal = input.next().trim().toUpperCase();     // CHECK: Pode usar toUpperCase() ou toLowerCase()?
            animalExists = existsInMatrix(animals, 0, selectedAnimal);
        } while (!animalExists);

        return selectedAnimal;
    }

    public static String getValidString(String message) {
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean validString = true;

        do {
            System.out.print(message);
            userInput = input.nextLine().trim();

            if (userInput.length() < 3 || userInput.length() > 50) {
                validString = false;
            }

        } while (!validString);

        return userInput;
    }

    static boolean isValidEmail(String email) {
        // Email válido deve ter o seguinte formato mínimo: xxx@yyy.zz

        String[] emailParts = email.split("@");
        if (emailParts.length != 2) {
            return false;
        }

        if (emailParts[0].length() < 3 || emailParts[0].length() > 50) {
            return false;
        }

        String[] emailDot = emailParts[1].split("\\.");
        if (emailDot[0].length() < 3 || emailDot[1].length() < 2) {
            return false;
        }

        return true;
    }

    public static String getValidEmail(String message) {
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean validString = true;

        do {
            System.out.print(message);
            userInput = input.next().trim();

            if (!isValidEmail(userInput)) {
                validString = false;
                System.out.println("Por favor digite um email no formato 'xxx@yyy.zz'.");
            }

        } while (!validString);

        return userInput;
    }

    public static double getValidDouble(String message) {
        Scanner input = new Scanner(System.in);
        double userInput;
        boolean validString = true;

        do {
            System.out.print(message);
            userInput = input.nextDouble();

            if (userInput < 10) {
                validString = false;
                System.out.println("O valor mínimo de apadrinhamento é de 10.00 €");
            }

        } while (!validString);

        return userInput;
    }

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

    static void printAnimalSponsors(String[][] animals, String[][] interactions, String[][] clients) {

        String selectedAnimal = getValidAnimal(animals);
        String[][] selectedInteractions = filterByAnimalAndInteractionType(interactions, 3, selectedAnimal, 2, "APADRINHAMENTO");

        printResultHeader("Listar padrinhos de um animal");

        if (selectedInteractions.length == 0) {
            System.out.println("Não há padrinhos para este animal.");
            printResultFooter();
            return;
        }

        String animalName = findValueAtColumn(animals, 0, selectedAnimal, 1);

        System.out.println("Lista de padrinhos para o animal " + selectedAnimal + " (" + animalName + "):");

        for (int i = 0; i < selectedInteractions.length; i++) {
            String clientName = findValueAtColumn(clients, 0, selectedInteractions[i][1], 1);
            String clientEmail = findValueAtColumn(clients, 0, selectedInteractions[i][1], 3);

            System.out.println();
            System.out.printf("%-25s", "Nome do cliente:");
            System.out.println(clientName);

            System.out.printf("%-25s", "Email do cliente:");
            System.out.println(clientEmail);

            System.out.printf("%-25s", "Valor mensal pago:");
            System.out.println(selectedInteractions[i][5] + " €");

            System.out.printf("%-25s", "Plano de apadrinhamento:");
            System.out.println(selectedInteractions[i][4]);

            printResultFooter();
        }
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

        printResultHeader("Espetáculo mais rentável");

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

        printResultFooter();
    }

    private static int[] getExtintionAnimalsInteractions(String[][] interactions, String[][] extintionAnimals) {

        int[] animalInteractions = new int[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalInteractions[i] = countValueInColumn(interactions, 3, extintionAnimals[i][0]);
        }
        return animalInteractions;
    }

    private static double[] getExtintionAnimalsIncomes(String[][] interactions, String[][] extintionAnimals) {

        double[] animalIncome = new double[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalIncome[i] = sumValueByCriteria(interactions, 3, extintionAnimals[i][0]);
        }
        return animalIncome;
    }

    private static void printExtintionRank(String[][] animals, String[][] interactions) {
        String[][] extintionAnimals = filterMatrix(animals, 5, "SIM");
        int[] animalInteractions = getExtintionAnimalsInteractions(interactions, extintionAnimals);
        double[] animalIncome = getExtintionAnimalsIncomes(interactions, extintionAnimals);

        int[] sortedAnimalInteractions = sortIntArrayDescending(animalInteractions);
        String[][] sortedExtintionAnimals = sortStringMatrixAtColumnDescendingByReference(extintionAnimals, animalInteractions);
        double[] sortedAnimalIncome = sortDoubleArrayDescending(animalIncome);

        printResultHeader("Ranking de animais em perigo de extinção");

        for (int i = 0; i < sortedExtintionAnimals.length; i++) {

            System.out.println();
            System.out.println((i + 1) + ") " + sortedExtintionAnimals[i][1]);

            System.out.printf("%-20s", "Espécie:");
            System.out.println(sortedExtintionAnimals[i][2]);

            System.out.printf("%-20s", "Habitat:");
            System.out.println(sortedExtintionAnimals[i][3]);

            System.out.printf("%-20s", "Dieta:");
            System.out.println(sortedExtintionAnimals[i][4]);

            System.out.printf("%-20s", "Total de interação:");
            System.out.println(sortedAnimalInteractions[i]);

            System.out.printf("%-20s", "Total de receita:");
            System.out.println(sortedAnimalIncome[i] + " €");

            printResultFooter();
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
        String[] habitats = getHabitats(animals);

        printResultHeader("Estatísticas por habitat");

        for (int i = 0; i < habitats.length; i++) {

            String[][] habitatAnimals = filterMatrix(animals, 3, habitats[i]);
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

            if (i < habitats.length - 1) {
                System.out.println("------------------");
            }
        }

        printResultFooter();
    }

    static void adminMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            printResultHeader("CodeSavanna - Menu ADMIN");

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
                case 0:
                    // System.out.println("0 - Voltar");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);

    }

    static void clientMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int option;

        do {
            printResultHeader("CodeSavanna - Menu CLIENTE");

            System.out.println("1 - Ver catálogo de animais por habitat");
            System.out.println("2 - Ver atividades de um animal (espetáculos e alimentações)");
            System.out.println("3 - Simular apadrinhamento de um animal");
            System.out.println("4 - Jogo: adivinha a espécie");
            System.out.println("0 - Voltar");

            Scanner sc = new Scanner(System.in);
            System.out.print("\nOpção: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printAnimalsByHabitat(animals);
                    break;
                case 2:
                    printAnimalsActivities(animals, interactions);
                    break;
                case 3:
                    sponsorSimulation(animals, clients, interactions);
                    break;
                case 4:
                    playSpecieGuess(animals);
                    break;
                case 0:
//                    System.out.println("0 - Voltar");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (option != 0);

    }

    private static void playSpecieGuess(String[][] animals) {
        Scanner input = new Scanner(System.in);
        String[] animal = getRamdomItem(animals);

        printResultHeader("Jogo: adivinha a espécie");
        printResultSubHeader("Pistas");
        System.out.println("PISTA 1 (habitat): " + animal[3]);
        System.out.println("PISTA 2 (dieta): " + animal[4]);
        System.out.println("PISTA 3 (habitat): " + animal[5]);

        int guessCount = 0;
        boolean guessed;
        String keepTrying = "";
        do {
            guessCount++;
            String specieGuess = getValidString("\nQual a espécie? ");
            guessed = specieGuess.equalsIgnoreCase(animal[2]);

            if (guessed) {
                System.out.println("\nParabéns! Descobriste a espécie!");
                System.out.println("Precisaste de " + guessCount + " tentativas.");
                System.out.print("Deseja jogar outra vez? (S/N) ");
            } else {
                System.out.println("\nResposta incorreta.");
                System.out.print("Deseja tentar outra vez? (S/N) ");
            }
            keepTrying = input.next().trim().toUpperCase();

        } while (!guessed || keepTrying.equals("N"));

        printResultFooter();
    }

    static void sponsorSimulation(String[][] animals, String[][] clients, String[][] interactions) {
        String clientName = getValidString("Digite seu nome: ");
        String clientEmail = getValidEmail("Digite seu email: ");

        String selectedAnimalId = getValidAnimal(animals);
        String[][] seledtedAnimalInfo = filterMatrix(animals, 0, selectedAnimalId);

        double sponsorAmount = getValidDouble("Digite o valor do patrocínio desejado: ");
        String sponsorTier = getSponsorTier(sponsorAmount);

        printResultHeader("Apadrinhamento de um animal");
        printResultSubHeader("Resumo do apadrinhamento");

        System.out.println();
        System.out.printf("%-10s", "Padrinho:");
        System.out.println(clientName + " (" + clientEmail + ")");

        System.out.printf("%-10s", "Animal:");
        System.out.println(seledtedAnimalInfo[0][1] + " (" + seledtedAnimalInfo[0][2] + ") - " + seledtedAnimalInfo[0][3]);

        System.out.printf("%-10s", "Plano:");
        System.out.println(sponsorTier);

        System.out.printf("%-10s", "Valor:");
        System.out.println(sponsorAmount + " €/mês");

        printResultFooter();
    }

    static String getSponsorTier(double sponsorAmount) {
        if (sponsorAmount <= 25.00) {
            return "Apadrinhamento Simples";
        }

        if (sponsorAmount <= 50.00) {
            return "Apadrinhamento Gold";
        }

        return "Apadrinhamento Diamond";

    }

    static void printAnimalsActivities(String[][] animals, String[][] interactions) {
        String selectedAnimal = getValidAnimal(animals);
        String[][] seledtedAnimalInfo = filterMatrix(animals, 0, selectedAnimal);
        String[][] selectedAnimalShows = filterByAnimalAndInteractionType(interactions, 3, selectedAnimal, 2, "ESPETACULO");
        String[][] selectedAnimalFeed = filterByAnimalAndInteractionType(interactions, 3, selectedAnimal, 2, "ALIMENTACAO");

        printResultHeader("Atividades do animal " + seledtedAnimalInfo[0][1] + " (" + seledtedAnimalInfo[0][2] + ")");

        // TODO: Evitar texto nos println abaixo (ESPETÁCULOS e ALIMENTAÇÃO)
        // TODO: Buscar prpoblemas semelhantes pelo programa
        // TODO: Criar função para imprimir resultados
        System.out.println("ESPETÁCULOS:");
        if (selectedAnimalShows.length == 0) {
            System.out.println("- Não houve interações deste tipo para este animal.");
        } else if (selectedAnimalShows.length == 1) {
            System.out.println("- " + selectedAnimalShows[0][4] + " (1 vez)");
        } else {
            System.out.println("- " + selectedAnimalShows[0][4] + " (" + selectedAnimalShows.length + " vezes)");
        }

        System.out.println("\nALIMENTAÇÃO:");
        if (selectedAnimalFeed.length == 0) {
            System.out.println("- Não houve interações deste tipo para este animal.");
        } else if (selectedAnimalFeed.length == 1) {
            System.out.println("- " + selectedAnimalFeed[0][4] + " (1 vez)");
        } else {
            System.out.println("- " + selectedAnimalFeed[0][4] + " (" + selectedAnimalFeed.length + " vezes)");
        }

        printResultFooter();
    }

    static void printAnimalsByHabitat(String[][] animals) {
        String[] habitats = getHabitats(animals);

        printResultHeader("Catálogo de animais por habitat");

        System.out.println();
        for (int i = 0; i < habitats.length; i++) {

            String[][] habitatAnimals = filterMatrix(animals, 3, habitats[i]);

            printResultSubHeader(habitats[i]);

            for (int j = 0; j < habitatAnimals.length; j++) {
                System.out.println("- " + habitatAnimals[j][1] + " (" + habitatAnimals[j][2] + ")");
            }
            System.out.println();
        }

        printResultFooter();
    }

    static void loginMenu(String[][] animals, String[][] clients, String[][] interactions) {
        int loginOption;

        do {
            printResultHeader("CodeSavanna - Login");

            System.out.println("\nSelecione uma das opções de login abaixo:");
            System.out.println("1 - Administrador");
            System.out.println("2 - Cliente");
            System.out.println("0 - Sair");

            Scanner sc = new Scanner(System.in);
            System.out.print("\nOpção: ");
            loginOption = sc.nextInt();

            switch (loginOption) {
                case 1:
                    if (validLogin("admin")) {
                        adminMenu(animals, clients, interactions);
                    }
                    break;
                case 2:
                    if (validLogin("client")) {
                        clientMenu(animals, clients, interactions);
                    }
                    break;
                case 0:
                    System.out.println("Obrigado. Tenha um ótimo dia.");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (loginOption != 0);

    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO: Refazer comentários de funções
        // TODO: Procurar funções que possam ser unidas em 1 mais genérica
        String animalsFilePath = "files/animais.csv";
        String clientsFilePath = "files/clientes.csv";
        String interactionsFilePath = "files/interacoes.csv";

        String[][] animals = readCsv(animalsFilePath, ";");
        String[][] clients = readCsv(clientsFilePath, ";");
        String[][] interactions = readCsv(interactionsFilePath, ";");

        // TODO: Imprimir banner de início
        System.out.println("\n\n\n");
        System.out.println("Bem vindo ao CodeSavanna!");

        loginMenu(animals, clients, interactions);

        printExitBoard();
    }
}
