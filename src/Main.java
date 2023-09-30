import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        DynamicCentering dynamicCentering = new DynamicCentering();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n\n");
        dynamicCentering.setCenteringPage("Welcome to Mine Sweeper!");
        System.out.println();

        System.out.println("\nPlease enter the number of rows and columns you want to play.\n");
        System.out.print("Enter the number of rows: ");
        int rowNumber = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int columnNumber = scanner.nextInt();

        while (rowNumber < 0 || columnNumber < 0) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter the number of rows: ");
            rowNumber = scanner.nextInt();

            System.out.print("Enter the number of columns: ");
            columnNumber = scanner.nextInt();

        }

        System.out.println("\n Starting game...\n");
        MineSweeper mineSweeper = new MineSweeper(rowNumber, columnNumber);
        mineSweeper.run();

    }
}
