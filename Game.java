/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * Shaunak (James) Divine
 * jsd2672
 */

package assignment2;

import java.util.Scanner;
import java.lang.String;

public class Game {
    private static Scanner s;
    private static boolean mode;
    private static boolean winToken;
    private static String playAgain;

    public Game(boolean mode, Scanner s, String playAgain){
        this.s = s;
        this.mode = mode;
        this.playAgain = playAgain;

    }
    public static String checkGuess(String guess, String code) {
        int black = 0;
        int white = 0;
        int pegNum = GameConfiguration.pegNumber;
        int replaceIndex = 0;
        String originalGuess = guess;
        char placeholder = '-';

        for (int i = 0; i < pegNum; i++) {
            if (guess.charAt(i) == code.charAt(i)) {
                black++;
                code = code.substring(0, i) + placeholder + code.substring(i + 1, pegNum);
                guess = guess.substring(0, i) + placeholder + guess.substring(i + 1, pegNum);
            }

        }
        for (int j = 0; j < pegNum; j++){
            for (int k = 0; k < pegNum; k++){
                if(guess.charAt(j) == code.charAt(k) && guess.charAt(j) != '-'){
                    white++;
                    replaceIndex = code.indexOf(code.charAt(k));
                    code = code.substring(0, replaceIndex) + placeholder + code.substring(replaceIndex + 1, pegNum);
                    break;
                }
            }
        }
        if (black == pegNum) {
            winToken = true;
            return (originalGuess + "-> Result: " + pegNum + "B_0W – You win !!");
        }

        return (originalGuess + " -> Result: " + black + "B_" + white + "W");
    }


    public static boolean validGuess(String guess){
        String[] pegColor = GameConfiguration.colors;
        int pegNum = GameConfiguration.pegNumber;
        int valid = 0;

        if (guess.contentEquals("HISTORY")){
            return true;
        }

        if (pegNum != guess.length()){
            return false;
        }
        for (int i = 0; i < pegNum; i++){
            valid = 0;
            for (int j = 0; j < pegColor.length; j++){
                if (guess.charAt(i) == pegColor[j].charAt(0)){
                    valid = 1;
                    break;
                }
            }
            if (valid != 1){
                return false;
            }
        }
        return true;
    }

    public static void runGame(){
        while(playAgain.contains("Y")){
            winToken = false;
            int guesses = GameConfiguration.guessNumber;
            History guessList = new History();
            String code = SecretCodeGenerator.getInstance().getNewSecretCode();
            if (mode){
                System.out.println("Generating secret code ... (for this example the secret code is " + code + ")");
            }else{
                System.out.println("Generating secret code ...");
            }
            while (guesses > 0) {
                System.out.println("You have " + guesses + " guesses left.");
                System.out.println("What is your next guess?");
                System.out.print("Type in the characters for your guess and press enter.\nEnter guess: ");
                String input = s.nextLine();
                if (!validGuess(input)){
                    System.out.println();
                    System.out.println(input + " -> INVALID GUESS\n");
                    continue;
                }
                if (input.contentEquals("HISTORY")){
                    guessList.printHistory();
                    continue;
                }
                guessList.addGuess(input, checkGuess(input, code));
                System.out.println();
                System.out.println(checkGuess(input, code));
                System.out.println();
                guesses--;
                if (guesses == 0 && !winToken){
                    System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
                    System.out.println("Are you ready for another game (Y/N):");
                    input = s.nextLine();
                    playAgain = input;
                }
                if (winToken){
                    System.out.print("Are you ready for another game (Y/N):");
                    input = s.nextLine();
                    playAgain = input;
                    guesses = 0;
                }
            }
        }
        System.exit(0);




    }


}




 /*

        if (guess.charAt(0) == code.charAt(0) && guess.charAt(1) == code.charAt(1) && guess.charAt(2) == code.charAt(2) && guess.charAt(3) == code.charAt(3)){
            return (guess + " -> Result: 4B_0W – You win !!");
        }
        if (guess.charAt(0) == code.charAt(0)) {
            black++;
            code = placeholder + code.substring(1, 4);
            System.out.println(code);
        }
        if (guess.charAt(1) == code.charAt(1)) {
            black++;
            code = code.substring(0,1) + placeholder + code.substring(2, 4);
            System.out.println(code);
        }
        if (guess.charAt(2) == code.charAt(2)) {
            black++;
            code = code.substring(0,2) + placeholder + code.substring(3, 4);
            System.out.println(code);
        }
        if (guess.charAt(3) == code.charAt(3)) {
            black++;
            code = code.substring(0,3) + placeholder;
            System.out.println(code);
        }
        if (code.contains(spot1)) {
            white++;
            replaceIndex = code.indexOf(guess.charAt(0));
            if (replaceIndex == 0){
                code = placeholder + code.substring(1, 4);
            }
            if (replaceIndex == 3){
                code = code.substring(0,3) + placeholder;
            }
            else {
                code = code.substring(0,replaceIndex) + placeholder + code.substring(replaceIndex+1, 4);
            }
        }
        if (code.contains(spot2)) {
            white++;
            replaceIndex = code.indexOf(guess.charAt(1));
            if (replaceIndex == 0){
                code = placeholder + code.substring(1, 4);
            }
            if (replaceIndex == 3){
                code = code.substring(0,3) + placeholder;
            }
            else {
                code = code.substring(0, replaceIndex) + placeholder + code.substring(replaceIndex + 1, 4);
            }
        }
        if (code.contains(spot3)) {
            white++;
            replaceIndex = code.indexOf(guess.charAt(2));
            if (replaceIndex == 0){
                code = placeholder + code.substring(1, 4);
            }
            if (replaceIndex == 3){
                code = code.substring(0,3) + placeholder;
            }
            else {
                code = code.substring(0, replaceIndex) + placeholder + code.substring(replaceIndex + 1, 4);
            }
        }
        if (code.contains(spot4)) {
            white++;
            replaceIndex = code.indexOf(guess.charAt(3));
            if (replaceIndex == 0){
                code = placeholder + code.substring(1, 4);
            }
            if (replaceIndex == 3){
                code = code.substring(0,3) + placeholder;
            }
            else {
                code = code.substring(0, replaceIndex) + placeholder + code.substring(replaceIndex + 1, 4);
            }
        }

        return(guess + " -> Result: " + black + "B_" + white + "W");
    }

         */
