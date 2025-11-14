package CodeSavannaUtils;

import java.util.Scanner;

import static CodeSavannaUtils.utils.*;

public class coreUtils {

    public static String[] getSpecies(String[][] animals) {
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

    public  static String[] getUniqueShows(String[][] interactions, int searchColumn, String searchValue) {
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

    public static String[][] getMostValuableAnimal(String[][] animals, String animalId) {
        String[][] valuableAnimal = new String[1][animals[0].length];

        for (int i = 1; i < animals.length; i++) {
            if (animals[i][0].equals(animalId)) {
                valuableAnimal[0] = animals[i];
            }
        }

        return valuableAnimal;
    }

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

    public static int[] getExtintionAnimalsInteractions(String[][] interactions, String[][] extintionAnimals) {

        int[] animalInteractions = new int[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalInteractions[i] = countValueInColumn(interactions, 3, extintionAnimals[i][0]);
        }
        return animalInteractions;
    }

    public static double[] getExtintionAnimalsIncomes(String[][] interactions, String[][] extintionAnimals) {

        double[] animalIncome = new double[extintionAnimals.length];
        for (int i = 0; i < extintionAnimals.length; i++) {
            animalIncome[i] = sumValueByCriteria(interactions, 3, extintionAnimals[i][0]);
        }
        return animalIncome;
    }

    public static String[] getHabitats(String[][] animals) {
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
