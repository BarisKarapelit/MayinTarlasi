import java.util.Random;
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
        Boolean isPlay = true, gameStatus = true;
        String mineSweeperPage = "", userRow, userColumn;
        Scanner scanner = new Scanner(System.in);
        do {
            dynamicCentering.setCenteringPage("==================================================");
            mineSweeperPage = print(mineSweeperPage, dynamicCentering);
            System.out.print("Enter the number of Row: ");
            userRow = scanner.nextLine();
            System.out.print("Enter the number of Column: ");
            userColumn = scanner.nextLine();

            isPlay = getaBoolean(scanner, dynamicCentering);
        } while (isPlay);

    }

    private static Boolean getaBoolean(Scanner scanner, DynamicCentering dynamicCentering) {
        Boolean gameStatus;
        Boolean isPlay;
        int a = scanner.nextInt();
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
        return isPlay;
    }

    private static String print(String mineSweeperPage, DynamicCentering dynamicCentering) {
        Random random = new Random();
        int numRows = getRowNumber();
        int numCols = getColumnNumber();

        int map[][] = new int[numRows][numCols];
        int board[][] = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                int randomBoom = random.nextInt(numCols);
                if (j == randomBoom) {
                    map[i][j] = -1;
                    mineSweeperPage += " * ";
                } else {
                    map[i][j] = 0;
                    mineSweeperPage += " - ";
                }
            }
            dynamicCentering.setCenteringPage(mineSweeperPage);
            mineSweeperPage = "";

        }
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");

        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                    mineSweeperPage += " - ";
            }
            dynamicCentering.setCenteringPage(mineSweeperPage);
            mineSweeperPage = "";

        }
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");
        return mineSweeperPage;
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
