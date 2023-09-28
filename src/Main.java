import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DynamicCentering dynamicCentering = new DynamicCentering();
        Scanner scanner = new Scanner(System.in);
        dynamicCentering.setCenteringPage("Welcome to Mine Sweeper!");
        System.out.print("Enter the number of rows: ");
        int rowNumber = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columnNumber = scanner.nextInt();
        MineSweeper mineSweeper = new MineSweeper(rowNumber,columnNumber);
        mineSweeper.run();
    }
}
