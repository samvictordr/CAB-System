package services;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import services.AwardService;
import services.AchievementService;

public class MainApplication {

    public static void main(String[] args) {
        // Start the application
        System.out.println("Welcome to the Certification and Achievement Badges System!");

        // Attempt to connect to the database
        Connection dbConnection = null;
        Scanner scanner = null;
        try {
            dbConnection = DatabaseConnection.getConnection();
            System.out.println("Connected to the database successfully.");

            // Initialize Scanner
            scanner = new Scanner(System.in);
            boolean running = true;
            while (running) {
                displayMainMenu();
                System.out.print("Please select an option: ");

                // Directly read the input without checking hasNextLine()
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("No input detected. Please enter a number between 1 and 4.");
                    continue;
                }

                try {
                    int option = Integer.parseInt(input);

                    switch (option) {
                        case 1:
                            awardCertification(scanner, dbConnection);
                            break;
                        case 2:
                            grantBadge(scanner, dbConnection);
                            break;
                        case 3:
                            viewAchievements(scanner, dbConnection);
                            break;
                        case 4:
                            running = false;
                            System.out.println("Exiting the system...");
                            break;
                        default:
                            System.out.println("Invalid option. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        } finally {
            // Close resources
            if (scanner != null) {
                scanner.close();
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.err.println("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Award Certification");
        System.out.println("2. Grant Badge");
        System.out.println("3. View Student Achievements");
        System.out.println("4. Exit");
    }

    /**
     * Handles the logic for awarding a certification to a student.
     *
     * @param scanner      The Scanner object for user input.
     * @param dbConnection The database connection.
     */
    private static void awardCertification(Scanner scanner, Connection dbConnection) {
        try {
            System.out.print("Enter student ID to award certification: ");
            String studentInput = scanner.nextLine().trim();
            int studentId = Integer.parseInt(studentInput);

            System.out.print("Enter the course ID for certification: ");
            String courseInput = scanner.nextLine().trim();
            int courseId = Integer.parseInt(courseInput);

            AwardService awardService = new AwardService(dbConnection);
            awardService.awardCertificationIfEligible(studentId, courseId);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Student ID and Course ID must be numbers.");
        } catch (Exception e) {
            System.err.println("Error awarding certification: " + e.getMessage());
        }
    }

    /**
     * Handles the logic for granting a badge to a student.
     *
     * @param scanner      The Scanner object for user input.
     * @param dbConnection The database connection.
     */
    private static void grantBadge(Scanner scanner, Connection dbConnection) {
        try {
            System.out.print("Enter student ID to grant badge: ");
            String studentInput = scanner.nextLine().trim();
            int studentId = Integer.parseInt(studentInput);

            System.out.print("Enter the achievement ID for badge: ");
            String achievementInput = scanner.nextLine().trim();
            int achievementId = Integer.parseInt(achievementInput);

            AwardService awardService = new AwardService(dbConnection);
            awardService.grantBadgeIfEligible(studentId, achievementId);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Student ID and Achievement ID must be numbers.");
        } catch (Exception e) {
            System.err.println("Error granting badge: " + e.getMessage());
        }
    }

    /**
     * Handles the logic for viewing a student's achievements.
     *
     * @param scanner      The Scanner object for user input.
     * @param dbConnection The database connection.
     */
    private static void viewAchievements(Scanner scanner, Connection dbConnection) {
        try {
            System.out.print("Enter student ID to view achievements: ");
            String studentInput = scanner.nextLine().trim();
            int studentId = Integer.parseInt(studentInput);

            AchievementService achievementService = new AchievementService(dbConnection);
            achievementService.viewStudentAchievements(studentId);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Student ID must be a number.");
        } catch (Exception e) {
            System.err.println("Error retrieving achievements: " + e.getMessage());
        }
    }
}