package CodeSavannaUtils;

import java.util.Scanner;

import static CodeSavannaUtils.arrayUtils.*;

public class coreUtils {

    /**
     * Extracts unique species names from a 2D array of animal data.
     * Each row in the input array represents an animal, and it is assumed that the
     * species name is stored in the third column (index 2) of each row.
     *
     * @param animals a 2D array where each row represents an animal,
     *                and the third column (index 2) contains the species name
     * @return an array of unique species names found in the input array
     */
    public static String[] getSpecies(String[][] animals) {
        int uniqueSpeciesCount = 0;
        boolean uniqueSpeciesFound;
        for (int i = 1; i < animals.length; i++) {
            uniqueSpeciesFound = true;
            for (int j = 1; j < i; j++) {
                if (animals[i][2].equals(animals[j][2])) {
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
                if (animals[i][2].equals(animals[j][2])) {
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
     * Prompts the user to input an animal ID and validates if it exists within the provided matrix.
     * Repeats the prompt until a valid animal ID is entered.
     *
     * @param animals a 2D array of strings where the first column contains animal IDs to validate against
     * @return a valid animal ID entered by the user, converted to uppercase
     */
    public static String getValidAnimal(String[][] animals) {
        Scanner input = new Scanner(System.in);
        String selectedAnimal;
        boolean animalExists;

        do {
            System.out.print("Digite o ID do animal: ");
            selectedAnimal = input.next().trim().toUpperCase();
            animalExists = existsInMatrix(animals, 0, selectedAnimal);
        } while (!animalExists);

        return selectedAnimal;
    }

    /**
     * Prompts the user with a message and ensures the input string meets the criteria of valid length.
     * The input string must be between 3 and 50 characters inclusive.
     *
     * @param message the prompt message displayed to the user.
     * @return a valid string input by the user, trimmed and meeting the length requirements.
     */
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

    /**
     * Validates whether the given email string is in a valid format.
     * A valid email should have a prefix of 3 to 50 characters, a domain,
     * and a suffix with at least 2 characters (e.g., xxx@yyy.zz).
     *
     * @param email the email address to be validated
     * @return true if the email is in a valid format, false otherwise
     */
    public static boolean isValidEmail(String email) {
        // Email válido deve ter o seguinte formato mínimo: xxx@yyy.zz

        String[] emailParts = email.split("@");
        if (emailParts.length != 2) {
            return false;
        }

        if (emailParts[0].length() < 3 || emailParts[0].length() > 50) {
            return false;
        }

        String[] emailDot = emailParts[1].split("\\.");
        
        return emailDot[0].length() >= 3 && emailDot[1].length() >= 2;
    }

    /**
     * Prompts the user to input an email address and validates its format.
     *
     * @param message The message to display as a prompt for the user's input.
     * @return A valid email address entered by the user in the format 'xxx@yyy.zz'.
     */
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

    /**
     * Prompts the user to input a sponsor amount until a valid input is provided.
     * Ensures the input is a double value equal to or greater than 10.00.
     *
     * @param message the message displayed to the user prompting input
     * @return the valid sponsor amount entered by the user as a double
     */
    public static double getValidSponsorAmount(String message) {
        Scanner input = new Scanner(System.in);
        double userInput;
        boolean validDouble = true;

        do {
            System.out.print(message);
            userInput = input.nextDouble();

            if (userInput < 10) {
                validDouble = false;
                System.out.println("O valor mínimo de apadrinhamento é de 10.00 €");
            }

        } while (!validDouble);

        return userInput;
    }

    /**
     * Filters a 2D array of interactions based on specific animal and interaction type values.
     *
     * @param interactions         A 2D array where each row represents an interaction and each column contains details about the interaction.
     * @param animalColumn         The column index representing the animal in each row.
     * @param animalValue          The specific animal value to filter for.
     * @param iteractionTypeColumn The column index representing the interaction type in each row.
     * @param interactionTypeValue The specific interaction type value to filter for.
     * @return A 2D array containing only the rows that match the specified animal and interaction type values.
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
     * Retrieves an array of unique show names from a given interactions dataset based on a specified search column and value.
     * The method filters and returns only those shows that match the search value in the specified column
     * and ensures no duplicate show names are included in the result.
     *
     * @param interactions A 2D array representing the dataset where each row is a record and columns hold attributes of the record.
     * @param searchColumn An integer representing the column index to search for the specified value.
     * @param searchValue  A string value to be matched within the specified column in the dataset.
     * @return An array of unique show names that match the search criteria. If no matches are found, an empty array is returned.
     */
    public static String[] getUniqueShows(String[][] interactions, int searchColumn, String searchValue) {
        int uniqueShowsCount = 0;
        boolean uniqueShowsFound;
        for (int i = 1; i < interactions.length; i++) {

            if (interactions[i][searchColumn].equals(searchValue)) {

                uniqueShowsFound = true;
                for (int j = 1; j < i; j++) {
                    if (interactions[i][4].equals(interactions[j][4])) {
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
                    if (interactions[i][4].equals(interactions[j][4])) {
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

    /**
     * Calculates the income for each show based on interactions data.
     *
     * @param interactions A 2D array containing interaction records where each entry includes details such as
     *                     show name and income (e.g., interactions[row][4] represents the show name, and
     *                     interactions[row][5] represents the income).
     * @param shows        An array of show names for which the income needs to be calculated.
     * @return An array of doubles where each element corresponds to the total income of the show at the same index
     * in the shows array.
     */
    public static double[] getShowsIncome(String[][] interactions, String[] shows) {
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

    /**
     * Determines the index of the most profitable show based on the provided income data.
     *
     * @param showsIncome an array of doubles representing the income generated by each show
     * @return the index of the show with the highest income
     */
    public static int getMostProfitableShowIndex(double[] showsIncome) {

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

    /**
     * Retrieves the details of the most valuable animal based on a given animal ID.
     *
     * @param animals  a 2D array of strings where each row represents an animal's details
     *                 and each column contains specific attributes of the animal.
     * @param animalId the unique identifier corresponding to the desired animal.
     * @return a 2D array containing one row that represents the details of the
     * most valuable animal matching the given animal ID, or an empty 2D
     * array if no match is found.
     */
    public static String[][] getMostValuableAnimal(String[][] animals, String animalId) {
        String[][] valuableAnimal = new String[1][animals[0].length];

        for (int i = 1; i < animals.length; i++) {
            if (animals[i][0].equals(animalId)) {
                valuableAnimal[0] = animals[i];
            }
        }

        return valuableAnimal;
    }

    /**
     * Retrieves the animal ID associated with a specific show name from the given interactions data.
     *
     * @param interactions a 2D array of strings representing interaction data. Each row contains information
     *                     about an interaction, and the 5th column (index 4) represents the show name, while
     *                     the 4th column (index 3) represents the corresponding animal ID.
     * @param show         the name of the show for which the associated animal ID is to be retrieved.
     * @return a string representing the animal ID associated with the specified show name, or an empty string
     * if no match is found.
     */
    public static String getAnimalIdFromShowName(String[][] interactions, String show) {
        String animalId = "";

        for (int i = 1; i < interactions.length; i++) {
            if (interactions[i][4].equals(show)) {
                animalId = interactions[i][3];
                i = interactions.length;
            }
        }

        return animalId;
    }

    /**
     * Calculates the number of interactions associated with animals at risk of extinction.
     *
     * @param interactions     a 2D array of strings representing interaction data. Each row
     *                         contains information about an interaction, and the 4th column
     *                         (index 3) specifies the animal identifier associated with the interaction.
     * @param extintionAnimals a 2D array of strings where each row represents an animal at risk
     *                         of extinction. The first column (index 0) contains the unique
     *                         animal identifiers.
     * @return an array of integers where each value represents the number of interactions
     * associated with the corresponding animal in the extintionAnimals array.
     */
    public static int[] getExtintionAnimalsInteractions(String[][] interactions, String[][] extintionAnimals) {

        int[] animalInteractions = new int[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalInteractions[i] = countValueInColumn(interactions, 3, extintionAnimals[i][0]);
        }
        return animalInteractions;
    }

    /**
     * Calculates the total income generated by interactions associated with animals
     * at risk of extinction.
     *
     * @param interactions     a 2D array of strings representing interaction data. Each row
     *                         contains information about an interaction, and the 4th column
     *                         (index 3) should specify the animal identifier associated
     *                         with the interaction.
     * @param extintionAnimals a 2D array of strings where each row represents an animal
     *                         at risk of extinction. The first column (index 0) should
     *                         contain the unique animal identifiers.
     * @return an array of doubles where each value represents the total income generated
     * by the interactions of the corresponding animal in the extinctionAnimals array.
     */
    public static double[] getExtintionAnimalsIncomes(String[][] interactions, String[][] extintionAnimals) {

        double[] animalIncome = new double[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalIncome[i] = sumValueByCriteria(interactions, 3, extintionAnimals[i][0]);
        }
        return animalIncome;
    }

    /**
     * Identifies and retrieves a list of unique habitats from the provided data.
     *
     * @param animals a 2D array of strings where each row represents an animal's data,
     *                and the 4th column (index 3) contains the habitat identifier.
     * @return an array of unique habitat identifiers found in the animal data.
     */
    public static String[] getHabitats(String[][] animals) {
        int uniqueHabitatsCount = 0;
        boolean uniquehabitatsFound;
        for (int i = 1; i < animals.length; i++) {
            uniquehabitatsFound = true;
            for (int j = 1; j < i; j++) {
                if (animals[i][3].equals(animals[j][3])) {
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
                if (animals[i][3].equals(animals[j][3])) {
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

    /**
     * Calculates the number of interactions associated with specific habitats.
     *
     * @param interactions   a 2D array of strings representing interaction data. The 4th column (index 3)
     *                       should specify the habitat identifier associated with each interaction.
     * @param habitatAnimals a 2D array of strings where each row represents a habitat, and the first
     *                       column (index 0) contains the unique identifier for each habitat.
     * @return the total count of interactions linked to the specified habitats.
     */
    public static int getHabitatInteractions(String[][] interactions, String[][] habitatAnimals) {
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

    /**
     * Calculates the total income derived from interactions associated with specific habitats.
     *
     * @param interactions   a 2D array of strings representing interaction data. The 4th column (index 3)
     *                       should indicate the habitat associated with each interaction, and the 6th
     *                       column (index 5) should represent the income generated by the interaction.
     * @param habitatAnimals a 2D array of strings where each row corresponds to a habitat, and the first
     *                       column (index 0) specifies the unique identifier for the habitat.
     * @return the total income derived from all interactions associated with the given habitats.
     */
    public static double getHabitatIncome(String[][] interactions, String[][] habitatAnimals) {
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

    /**
     * Determines the extinction risk message based on the provided animal's data.
     *
     * @param animal an array of strings containing details about the animal. The 6th element
     *               (index 5) of the array should indicate whether the animal is at extinction
     *               risk ("SIM" for at risk, anything else otherwise).
     * @return a string message indicating if the animal is at risk of extinction or not.
     */
    public static String getExtintionRiskMessage(String[] animal) {
        boolean isAtExtintionRisk = animal[5].equals("SIM");
        String extintionRiskMessage;
        if (isAtExtintionRisk) {
            extintionRiskMessage = "Está em perigo de extinção";
        } else {
            extintionRiskMessage = "Não está em perigo de extinção";
        }
        return extintionRiskMessage;
    }

    /**
     * Checks if the guessed specie matches the actual specie and provides appropriate feedback.
     *
     * @param specieGuess the guessed specie provided by the user
     * @param specie      the actual specie to match against
     * @param guessCount  the number of attempts made by the user
     * @return true if the guessed specie matches the actual specie (case insensitive), false otherwise
     */
    public static boolean isGuessed(String specieGuess, String specie, int guessCount) {
        boolean guessed;
        guessed = specieGuess.equalsIgnoreCase(specie);

        if (guessed) {
            System.out.println("\nParabéns! Descobriste a espécie!");
            System.out.println("Precisaste de " + guessCount + " tentativas.");
        } else {
            System.out.println("\nResposta incorreta.");
            System.out.print("Deseja tentar outra vez? (S/N) ");
        }
        return guessed;
    }

    /**
     * Determines the sponsorship tier based on the sponsor's contribution amount.
     *
     * @param sponsorAmount the amount contributed by the sponsor
     * @return a string representing the sponsorship tier:
     * "Apadrinhamento Simples" for contributions of 25.00 or less,
     * "Apadrinhamento Gold" for contributions between 25.01 and 50.00,
     * or "Apadrinhamento Diamond" for contributions above 50.00
     */
    public static String getSponsorTier(double sponsorAmount) {
        if (sponsorAmount <= 25.00) {
            return "Apadrinhamento Simples";
        }

        if (sponsorAmount <= 50.00) {
            return "Apadrinhamento Gold";
        }

        return "Apadrinhamento Diamond";

    }

}
