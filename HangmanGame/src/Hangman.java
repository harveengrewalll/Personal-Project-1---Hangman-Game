import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();

        String chosenWord;

        if (players.equals("1")) {
            Scanner scanner = new Scanner(new File("/Users/harveen_x/Desktop/school/se 2205/HangmanGame/words_alpha.txt"));
            List<String> words = new ArrayList<>();

            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }

            Random rand = new Random();
            chosenWord = words.get(rand.nextInt(words.size()));
        } else if (players.equals("2")) {
            System.out.println("Player 1, please enter your word: ");
            chosenWord = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good luck!");
        } else {
            System.out.println("Invalid input! Please try again.");
        }


        // System.out.println(chosenWord);

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;

        while (true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + chosenWord);
                break;
            }

            printWordState(chosenWord, playerGuesses);
            if (!getPlayerGuess(keyboard, playerGuesses, chosenWord)) {
                wrongCount++;
            }

            if (printWordState(chosenWord, playerGuesses)) {
                System.out.println("*************************************************************");
                System.out.println("You win!");
                System.out.println("*************************************************************");
                break;
            }

            System.out.println("Please enter your guess for the complete word: ");
            if (keyboard.nextLine().equals(chosenWord)) {
                System.out.println("*************************************************************");
                System.out.println("You win!");
                System.out.println("*************************************************************");
                break;
            } else {
                System.out.println("Nope! Try again!");
            }
        }
    }

    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");

        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, List<Character> playerGuesses, String chosenWord) {
        System.out.println("Please enter a letter: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0)); // Takes the very first character the user enters (in case they try to cheat and guess multiple letters.
        return (chosenWord.contains(letterGuess));
    }

    private static boolean printWordState(String chosenWord, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < chosenWord.length(); i++) {
            if (playerGuesses.contains(chosenWord.charAt(i))) {
                System.out.print(chosenWord.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
        return (chosenWord.length() == correctCount);
    }
}
