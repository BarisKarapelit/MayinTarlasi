import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    private static int rowNumber; // Area of the board Rows
    private static int columnNumber; // Area of the board Columns
    static Integer[][] map; // -1: mine, 0: empty, 1-8: number of mines in adjacent squares
    static Integer[][] board; // 0: not opened, 1: opened, 2: flagged
    static int numCols; // number of columns
    static int numRows, size, userRowInt = 0, userColumnInt = 0; // number of rows
    static Random random = new Random(); // random number generator
    static Integer[] mineLocation; // location of mine
    static Boolean startPage;

    // Constructor
    public MineSweeper(int rowNumber, int columnNumber, Boolean startPage) {
        setRowNumber(rowNumber);
        setColumnNumber(columnNumber);
        this.numRows = getRowNumber();
        this.numCols = getColumnNumber();
        this.map = new Integer[numRows][numCols];
        this.board = new Integer[numRows][numCols];
        this.size = numRows * numCols;
        this.startPage = startPage;
    }


    // Main method
    public void run() {
        DynamicCentering dynamicCentering = new DynamicCentering();
        Boolean isPlay = true, isSuccessfulRow = true, isSuccessfulColumn = true;
        int success = 0;
        String mineSweeperPage = "";
        Scanner scanner = new Scanner(System.in);
        randomNumberGenerater();
        mineSweeperPage = print(dynamicCentering, mineLocation);
        do {
            isSuccessfulColumn = true;
            isSuccessfulRow = true;
            if (startPage == true) {
                userPanelAnswer(dynamicCentering, mineSweeperPage);
                startPage = false;
            }

            // Input validation for row number
            while (Boolean.TRUE.equals(isSuccessfulRow)) {
                System.out.println("\nPlease enter the number of rows and columns you want to play.\n");
                System.out.print("Enter the number of Row: ");
                userRowInt = scanner.nextInt();
                if (userRowInt >= 0 || userRowInt <= map.length) {
                    isSuccessfulRow = false;
                } else {
                    isSuccessfulRow = true;
                }
            }

            // Input validation for column number
            while (Boolean.TRUE.equals(isSuccessfulColumn)) {
                System.out.print("Enter the number of Column: ");

                userColumnInt = scanner.nextInt();
                if (userColumnInt >= 0 || userColumnInt <= map.length) {
                    isSuccessfulColumn = false;
                } else {
                    isSuccessfulColumn = true;
                }
            }


            if (map[userRowInt][userColumnInt] == -1) {
                isPlay = getaBoolean(scanner, dynamicCentering, false);
            } else {
                int count = findNeighbors(userRowInt, userColumnInt);
                board[userRowInt][userColumnInt] = count;
                mineSweeperPage = userPanel(dynamicCentering, count);
                success++;
                if (success == (size - (size / 4))) {
                    isPlay = getaBoolean(scanner, dynamicCentering, true);
                }
            }

        } while (Boolean.TRUE.equals(isPlay));
    }

    private static int findNeighbors(int row, int col) {
        int count = 0;

        int rStartIndex = (row - 1 < 0) ? 0 : row - 1;
        int rEndIndex = (row + 1 >= map.length) ? row : row + 1;

        int cStartIndex = (col - 1 < 0) ? 0 : col - 1;
        int cEndIndex = (col + 1 >= map[row].length) ? col : col + 1;

        for (int i = rStartIndex; i <= rEndIndex; i++) {
            for (int j = cStartIndex; j <= cEndIndex; j++) {
                if (map[i][j].equals(-1)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static String userPanelAnswer(DynamicCentering dynamicCentering, String mineSweeperPage) {
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");
        System.out.println();

        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] > 0) {
                    mineSweeperPage += board[userRowInt][userColumnInt];
                } else {
                    mineSweeperPage += " - ";
                }
            }
            dynamicCentering.setCenteringPage(mineSweeperPage);
            mineSweeperPage = "";
        }
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");
        return mineSweeperPage;
    }

    private static String userPanel(DynamicCentering dynamicCentering, int count) {
        String mineSweeperPage = "";
        startPage = false;
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");
        System.out.println();
        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] == board[userRowInt][userColumnInt]) {
                    mineSweeperPage += count;
                } else if (board[i][j] == 0) {
                    mineSweeperPage += " - ";
                } else if (board[i][j] == -1) {
                    mineSweeperPage += " - ";
                }
            }
            dynamicCentering.setCenteringPage(mineSweeperPage);
            mineSweeperPage = "";
        }
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");
        return mineSweeperPage;
    }


    private static void randomNumberGenerater() {
        // Initialize mineLocation array
        mineLocation = new Integer[numRows];
        // Generate random mine locations
        int numMines = (numRows * numCols) / 4;

        for (int i = 0; i < numMines; i++) {
            int location;
            boolean isDuplicate;

            do {
                location = random.nextInt(numCols);
                isDuplicate = false;

                // Check for duplicates
                for (int j = 0; j < i; j++) {
                    if (mineLocation[j] == location) {
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);

            mineLocation[i] = location;
        }
    }


    private static Boolean getaBoolean(Scanner scanner, DynamicCentering dynamicCentering, Boolean gameStatus) {
        Boolean isPlay;
        if (gameStatus) {
            dynamicCentering.setCenteringPage("You win!");
            dynamicCentering.setCenteringPage("Do you want to play? (1: Yes, 2: No)");
            int b = scanner.nextInt();
            if (b == 1) {
                isPlay = true;
                Main.main(null);
            } else {
                System.out.println("Thank you for playing!");
                isPlay = false;
            }

        } else {
            dynamicCentering.setCenteringPage("You lose!");
            dynamicCentering.setCenteringPage("Do you want to play? (1: Yes, 2: No)");
            int b = scanner.nextInt();
            if (b == 1) {
                isPlay = true;
                Main.main(null);
            } else {
                System.out.println("Thank you for playing!");
                isPlay = false;
            }
        }
        return isPlay;
    }

    private static String print(DynamicCentering dynamicCentering, Integer[] randomBoom) {
        String mineSweeperPage = "";
        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (randomBoom[i] != null) {
                    if (j == randomBoom[i]) {
                        // Mayinlar
                        map[i][j] = -1;
                        mineSweeperPage += " * ";
                    } else {
                        // Bos alanlar
                        map[i][j] = 0;
                        mineSweeperPage += " - ";
                    }
                } else {
                    // Bos alanlar
                    map[i][j] = 0;
                    mineSweeperPage += " - ";
                }

            }
            dynamicCentering.setCenteringPage(mineSweeperPage);
            mineSweeperPage = "";


        }
        board = Arrays.copyOf(map, map.length);
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
