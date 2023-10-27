import java.io.*;
import java.util.Scanner;

public class AddDelete {
    private static final String FILENAME = "student_info.txt";

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();
        addStudentToFile(id, name, email);
        System.out.println("Student added successfully.");
    }

    public void viewAllStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID to update: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new student name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new student email: ");
        String newEmail = scanner.nextLine();
        updateStudentInFile(idToUpdate, newName, newEmail);
        System.out.println("Student information updated successfully.");
    }

    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID to delete: ");
        int idToDelete = scanner.nextInt();
        deleteStudentFromFile(idToDelete);
        System.out.println("Student deleted successfully.");
    }

    private void addStudentToFile(int id, String name, String email) {
        try (FileWriter fileWriter = new FileWriter(FILENAME, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String studentInfo = "ID: " + id + ", Name: " + name + ", Email: " + email;
            bufferedWriter.write(studentInfo);
            bufferedWriter.newLine(); // Add a newline for the next entry
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStudentInFile(int idToUpdate, String newName, String newEmail) {
        try {
            File file = new File(FILENAME);
            File tempFile = new File("temp_student_info.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("ID: " + idToUpdate)) {
                    line = "ID: " + idToUpdate + ", Name: " + newName + ", Email: " + newEmail;
                }
                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if (file.delete()) {
                tempFile.renameTo(file);
            } else {
                System.err.println("Failed to update student information.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudentFromFile(int idToDelete) {
        try {
            File file = new File(FILENAME);
            File tempFile = new File("temp_student_info.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("ID: " + idToDelete)) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            if (file.delete()) {
                tempFile.renameTo(file);
            } else {
                System.err.println("Failed to delete student information.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
