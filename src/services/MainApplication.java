package services;

import db.DatabaseConnection; // Added import
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import services.AwardService;
import services.AchievementService;

public class MainApplication {

    public static void main(String[] args) {
        // Start the application
        System.out.println("Welcome to the Certification and Achievement Badges System!");

        // Attempt to connect to the database
        Connection dbConnection = null;
        try {
            dbConnection = DatabaseConnection.getConnection();
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            return;  // Exit the application if database connection fails
        }

        // Main Menu Loop
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Award Certification");
            System.out.println("2. Grant Badge");
            System.out.println("3. View Student Achievements");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after the number input

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
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void awardCertification(Scanner scanner, Connection dbConnection) {
        // Updated to use AwardService and adjusted inputs
        System.out.print("Enter student ID to award certification: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the course ID for certification: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        try {
            AwardService awardService = new AwardService(dbConnection);
            awardService.awardCertificationIfEligible(studentId, courseId);
        } catch (Exception e) {
            System.err.println("Error awarding certification: " + e.getMessage());
        }
    }

    private static void grantBadge(Scanner scanner, Connection dbConnection) {
        // Updated to use AwardService with dbConnection
        System.out.print("Enter student ID to grant badge: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the achievement ID for badge: ");
        int achievementId = scanner.nextInt();
        scanner.nextLine();

        try {
            AwardService awardService = new AwardService(dbConnection);
            awardService.grantBadgeIfEligible(studentId, achievementId);
        } catch (Exception e) {
            System.err.println("Error granting badge: " + e.getMessage());
        }
    }

    private static void viewAchievements(Scanner scanner, Connection dbConnection) {
        // Updated to use AchievementService with dbConnection
        System.out.print("Enter student ID to view achievements: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        try {
            AchievementService achievementService = new AchievementService(dbConnection);
            achievementService.viewStudentAchievements(studentId);
        } catch (Exception e) {
            System.err.println("Error retrieving achievements: " + e.getMessage());
        }
    }
}