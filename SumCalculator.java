import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * SumCalculator demonstrates autoboxing (int to Integer) and unboxing
 * (Integer to int) while calculating the sum of parsed integer inputs.
 */
public class SumCalculator {

    public static void main(String[] args) {
        // Use a Scanner to take input from the user (e.g., "10 20 30")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a list of integers separated by spaces (e.g., 10 20 30):");
        String inputLine = scanner.nextLine();
        scanner.close();

        // Split the input string into individual number strings
        String[] numberStrings = inputLine.split("\\s+");

        // 1. Autoboxing Demonstration
        // We use a List<Integer>, which requires Integer objects.
        // When we add an 'int' (parsed value) to this list, Java automatically
        // converts (autoboxes) the 'int' primitive into an 'Integer' object.
        List<Integer> integerList = new ArrayList<>();
        
        System.out.println("\n--- Parsing and Autoboxing ---");
        for (String s : numberStrings) {
            try {
                // Parsing string input to a primitive 'int'
                int value = Integer.parseInt(s.trim());
                
                // Autoboxing occurs here: 'value' (int) is converted to an Integer object
                // before being added to the List<Integer>.
                integerList.add(value); 
                System.out.printf("Parsed %s (int), Autoboxed and added to List: %d\n", s, value);
                
            } catch (NumberFormatException e) {
                // This is sloppy error handling. A real application would log this or prompt for reentry.
                System.err.println("Trash input ignored: '" + s + "' is not a valid integer.");
            }
        }

        // 2. Unboxing Demonstration and Summation
        // We calculate the sum of the Integer objects in the list.
        long sum = 0;
        
        System.out.println("\n--- Summation and Unboxing ---");
        for (Integer boxedValue : integerList) {
            // Unboxing occurs here: 'boxedValue' (Integer object) is automatically
            // converted (unboxed) back to an 'int' primitive so it can be 
            // used in the arithmetic operation with 'sum' (long).
            sum += boxedValue; 
            System.out.printf("Unboxed %d (Integer) to int for summation.\n", boxedValue);
        }

        System.out.println("\n--- Result ---");
        System.out.println("Final Sum: " + sum);
    }
}
