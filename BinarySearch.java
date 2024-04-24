/*
* This program generates 250 random numbers in an array
* and allows the user to search the array for a number.
*
* @author  Mr Coxall
* @version 0.5
* @since   2020-09-01
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the class.
 */
final class BinarySearch {
    /**
     * The number of elements in the array.
    */
    public static final int ARRAY_SIZE = 250;
    /**
     * The max number for array.
    */
    public static final int MAX = 999;
    /**
     * The min number for array.
    */
    public static final int MIN = 0;

    /**
    * Prevent instantiation.
    * Throw an exception IllegalStateException.
    * if this is ever called
    *
    * @throws IllegalStateException if this is ever called
    *
    */
    private BinarySearch() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * Function finds the index of a number, using Binary Search recursively.
    *
    * @param userArray the array to search
    * @param userNumber the number to find
    * @param lowIndex the starting index
    * @param highIndex the ending index
    * @return the index where the number was found
    */
    static int binarySearch(final int[] userArray, final int userNumber,
                            final int lowIndex, final int highIndex) {
        final int middleIndex = (int) Math.floor((lowIndex + highIndex) / 2);
        int foundIndex = 0;

        if (lowIndex > highIndex) {
            foundIndex = -1;
        } else if (userArray[middleIndex] == userNumber) {
            foundIndex = middleIndex;
        } else if (userArray[middleIndex] > userNumber) {
            foundIndex = binarySearch(
                userArray, userNumber, lowIndex, middleIndex - 1
            );
        } else if (userArray[middleIndex] < userNumber) {
            foundIndex = binarySearch(
                userArray, userNumber, middleIndex + 1, highIndex
            );
        }

        return foundIndex;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        System.out.println("Binary Search Program");
        try {
            // Initializing the random class
            final Random randNumber = new Random();

            // Initializing array of numbers
            final int[] randomNumberArray = new int[ARRAY_SIZE];

            // Adding numbers to the array
            for (
                int counter = 0; counter < randomNumberArray.length; counter++
            ) {
                randomNumberArray[counter] = randNumber.nextInt(MAX) + 1;
            }

            // Sorting the array
            final int[] numberArray = randomNumberArray;
            Arrays.sort(numberArray);

            System.out.print("Sorted list of numbers:\n");
            for (int element: numberArray) {
                final String padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }
            System.out.print("\n\n");

            // Getting user input as to what number they wish to search for
            final Scanner userInput = new Scanner(System.in);
            System.out.print("Enter a number to search for");
            System.out.print(" (" + MIN + " to " + MAX + "): ");
            final int searchNumber = userInput.nextInt();
            userInput.close();
            System.out.println();

            // Ensuring the user inputs an appropriate integer
            if (searchNumber > MAX || searchNumber < MIN) {
                throw new NumberFormatException();
            } else {
                // Using binary search to find the user's chosen number
                final int searchResult = binarySearch(numberArray, searchNumber,
                                                0, numberArray.length - 1);

                // Outputing the results of the search
                System.out.println(
                    searchNumber + " is in index: " + searchResult
                );
            }

            // Catches and tells the user that an error occured
        } catch (NumberFormatException nfe) {
            System.out.println("This is not a valid number.");
        }
    }
}
