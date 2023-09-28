import java.util.Scanner;

public class MineSweeper {

    private static int rowNumber;
    private static int columnNumber;
    public MineSweeper(int rowNumber, int columnNumber) {
        setRowNumber(rowNumber);
        setColumnNumber(columnNumber);
    }

    public void run() {
        DynamicCentering dynamicCentering = new DynamicCentering();
        dynamicCentering.setCenteringPage("Welcome to Mine Sweeper!");
        Boolean isPlay = true, gameStatus = true;
        Scanner scanner = new Scanner(System.in);
        do {
            dynamicCentering.setCenteringPage("Enter the number of rows: ");
            dynamicCentering.setCenteringPage("Enter the number of columns: ");
            dynamicCentering.setCenteringPage("====================================");
            int a= scanner.nextInt();
            if (a == 1) {
                gameStatus = true;
            } else {
                gameStatus = false;
            }
            if (gameStatus) {
                dynamicCentering.setCenteringPage("You win!");
                dynamicCentering.setCenteringPage("Do you want to play? (1: Yes, 2: No)");
                int b = scanner.nextInt();
                if (b == 1) {
                    isPlay = true;
                } else {
                    isPlay = false;
                }

            } else {
                dynamicCentering.setCenteringPage("You lose!");
                dynamicCentering.setCenteringPage("Do you want to play? (1: Yes, 2: No)");
                int b = scanner.nextInt();
                if (b == 1) {
                    isPlay = true;
                } else {
                    isPlay = false;
                }
            }
        }while (isPlay);

    }

    public static void setColumnNumber(int columnNumber) {
        MineSweeper.columnNumber = columnNumber;
    }

    public static void setRowNumber(int rowNumber) {
        MineSweeper.rowNumber = rowNumber;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    public static int getRowNumber() {
        return rowNumber;
    }
}
