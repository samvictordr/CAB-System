import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        // Start the application
        System.out.println("Welcome to the Certification and Achievement Badges System!");

        // Configure logging based on properties (for simplicity, assuming logging setup is done elsewhere)
        setupLogging();

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

    private static void setupLogging() {
        // Setup logging based on the configuration file (for simplicity)
        String logLevel = ApplicationConfig.LOG_LEVEL;
        String logFilePath = ApplicationConfig.LOG_FILE_PATH;

        // Example: Configure a logger (using System.out here, but you can use a proper logging framework)
        System.out.println("Logging level: " + logLevel);
        System.out.println("Log file path: " + logFilePath);
        // Initialize your logger setup here (e.g., Log4j, SLF4J, etc.)
    }

    private static void awardCertification(Scanner scanner, Connection dbConnection) {
        // Logic to award a certification
        System.out.print("Enter student ID to award certification: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter the certification name: ");
        String certificationName = scanner.nextLine();

        try {
            // Example: Call your service that handles the certification awarding logic
            CertificationService certificationService = new CertificationService(dbConnection);
            certificationService.awardCertification(studentId, certificationName);
            System.out.println("Certification awarded successfully.");
        } catch (Exception e) {
            System.err.println("Error awarding certification: " + e.getMessage());
        }
    }

    private static void grantBadge(Scanner scanner, Connection dbConnection) {
        // Logic to grant a badge
        System.out.print("Enter student ID to grant badge: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter the badge name: ");
        String badgeName = scanner.nextLine();

        try {
            // Example: Call your service that handles the badge granting logic
            BadgeService badgeService = new BadgeService(dbConnection);
            badgeService.grantBadge(studentId, badgeName);
            System.out.println("Badge granted successfully.");
        } catch (Exception e) {
            System.err.println("Error granting badge: " + e.getMessage());
        }
    }

    private static void viewAchievements(Scanner scanner, Connection dbConnection) {
        // Logic to view student achievements
        System.out.print("Enter student ID to view achievements: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        try {
            // Example: Call your service to retrieve achievements for the student
            AchievementService achievementService = new AchievementService(dbConnection);
            achievementService.viewStudentAchievements(studentId);
        } catch (Exception e) {
            System.err.println("Error retrieving achievements: " + e.getMessage());
        }
    }
}
