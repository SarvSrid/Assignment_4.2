import java.util.Scanner;

public class SecDatabase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddDelete addDelete = new AddDelete();

        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Update student data");
            System.out.println("4. Delete a student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDelete.addStudent();
                    break;
                case 2:
                    addDelete.viewAllStudents();
                    break;
                case 3:
                    addDelete.updateStudent();
                    break;
                case 4:
                    addDelete.deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
