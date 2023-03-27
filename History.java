/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * Shaunak (James) Divine
 * jsd2672
 */

package assignment2;

public class History {
    private static String[] guessList = new String[GameConfiguration.guessNumber];
    private static int length;
    private static int index;

    public History(){
        length = GameConfiguration.guessNumber;
        index = 0;
    }

    public static void addGuess(String guess, String result){
        guessList[index] = guess + "        " + result.substring(16);
        index++;
    }

    public static void printHistory(){
        for (int i = 0; i < index; i++){
            System.out.println(guessList[i]);
        }
    }

}
