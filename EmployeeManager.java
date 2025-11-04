import java.io.*;
import java.util.Scanner;

/**
 * EmployeeManager is a basic, menu-driven application for managing employee
 * records using flat text file handling. 
 * WARNING: This approach is not thread-safe or scalable.
 */
public class EmployeeManager {

    private static final String FILE_NAME = "employee_records.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // The application loop is basic, but functional for demonstration.
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        addEmployeeRecord(scanner);
                        break;
                    case 2:
                        displayAllRecords();
                        break;
                    case 3:
                        System.out.println("Exiting the system. Don't let your data get corrupted.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again, but use your brain this time.");
                }
            } else {
                System.out.println("Invalid input. Enter a number (1-3).");
                scanner.nextLine(); // Clear the invalid input
                choice = 0;
            }
            System.out.println("\n----------------------------------\n");
        } while (choice != 3);

        scanner.close();
    }

    /**
     * Prints the menu options to the console.
     */
    private static void displayMenu() {
        System.out.println("--- Employee Management System (Text File) ---");
        System.out.println("1. Add New Employee Record");
        System.out.println("2. Display All Records");
        System.out.println("3. Exit");
    }

    /**
     * Prompts the user for employee details and appends the record to the file.
     * Uses FileWriter in append mode and PrintWriter for formatted output.
     */
    private static void addEmployeeRecord(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Employee Department: ");
        String dept = scanner.nextLine().trim();

        // Data record format: ID|NAME|DEPARTMENT
        String record = String.format("%s|%s|%s", id, name, dept);
        
        try (FileWriter fw = new FileWriter(FILE_NAME, true); // 'true' means append mode
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println(record);
            System.out.println("SUCCESS: Record added for " + name);
            
        } catch (IOException e) {
            System.err.println("FATAL: Could not write to file " + FILE_NAME + ". I/O failed.");
            e.printStackTrace();
        }
    }

    /**
     * Reads and displays all employee records from the file.
     * Uses FileReader and BufferedReader for efficient line-by-line reading.
     */
    private static void displayAllRecords() {
        System.out.println("\n--- ALL EMPLOYEE RECORDS ---");
        
        try (FileReader fr = new FileReader(FILE_NAME);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            boolean foundRecords = false;
            
            while ((line = br.readLine()) != null) {
                // Parse the line based on the '|' delimiter
                String[] parts = line.split("\\|");
                
                if (parts.length == 3) {
                    System.out.printf("ID: %s | Name: %s | Dept: %s\n", parts[0], parts[1], parts[2]);
                    foundRecords = true;
                } else {
                    System.err.println("CORRUPTED RECORD: Skipped line due to malformed data: " + line);
                }
            }
            
            if (!foundRecords) {
                System.out.println("No records found in the file. Start doing some work.");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("The data file does not exist yet. Add some records first.");
        } catch (IOException e) {
            System.err.println("FATAL: Error reading the file. Check the contents.");
            e.printStackTrace();
        }
    }
}
