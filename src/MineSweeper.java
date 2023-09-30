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

    // Constructor
    public MineSweeper(int rowNumber, int columnNumber) {
        setRowNumber(rowNumber);
        setColumnNumber(columnNumber);
        this.numRows = getRowNumber();
        this.numCols = getColumnNumber();
        this.map = new Integer[numRows][numCols];
        this.board = new Integer[numRows][numCols];
        this.size = numRows * numCols;
    }


    // Main method
    public void run() {
        DynamicCentering dynamicCentering = new DynamicCentering();
        Boolean isPlay = true, isSuccessful = true;
        int success = 0, userRowInt = 0, userColumnInt = 0;
        String mineSweeperPage = "";
        Scanner scanner = new Scanner(System.in);
        prepareGame();
        randomNumberGenerater();

        do {
            mineSweeperPage = print(mineSweeperPage, dynamicCentering, mineLocation);
            userPanel(dynamicCentering, mineSweeperPage);

            // Input validation for row number
            while (isSuccessful) {
                System.out.print("Enter the number of Row: ");
                userRowInt = scanner.nextInt();
                if (userRowInt >= 0 || userRowInt <= map.length) {
                    isSuccessful = false;
                } else {
                    isSuccessful = true;
                }
            }

            isSuccessful = true;
            while (isSuccessful) {
                System.out.print("Enter the number of Column: ");
                userColumnInt = scanner.nextInt();
                if (userColumnInt >= 0 || userColumnInt <= map.length) {
                    isSuccessful = false;
                } else {
                    isSuccessful = true;
                }
            }
            if (map[userRowInt][userColumnInt] == -1) {
                isPlay = getaBoolean(scanner, dynamicCentering, false);
            } else {
                int count = findNeighbors(userRowInt, userColumnInt);
                board[userRowInt][userColumnInt] = count;
                mineSweeperPage = userPanel(dynamicCentering, mineSweeperPage, count);
                success++;
                if (success == (size - (size / 4))) {
                    isPlay = getaBoolean(scanner, dynamicCentering, true);
                }
            }
        } while (isPlay);
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

    private static String userPanel(DynamicCentering dynamicCentering, String mineSweeperPage) {
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

    private static String userPanel(DynamicCentering dynamicCentering, String mineSweeperPage, int count) {
        System.out.println("Board:");
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");
        System.out.println();
        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] == board[userRowInt][userColumnInt]) {
                    mineSweeperPage += board[userRowInt][userColumnInt]+" ";
                } else {
                    mineSweeperPage += " - ";
                }
            }
            dynamicCentering.setCenteringPage(mineSweeperPage);
            mineSweeperPage = "";
        }
        System.out.println();
        dynamicCentering.setCenteringPage("==================================================");
        System.out.println("\nPlease enter the number of rows and columns you want to play.\n");
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

    private static String print(String mineSweeperPage, DynamicCentering dynamicCentering, Integer[] randomBoom) {
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

    public void prepareGame() {
        // Initialize map and board arrays with appropriate dimensions
        map = new Integer[numRows][numCols];
        board = new Integer[numRows][numCols];

        // Initialize map elements
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                map[i][j] = 0;  // Initialize to 0 (empty)
                board[i][j] = 0;  // Initialize to 0 (not opened)
            }
        }

        int randRow, randCol, count = 0;
        while (count != (rowNumber * columnNumber) / 4) {
            randRow = (int) (Math.random() * rowNumber);
            randCol = (int) (Math.random() * columnNumber);
            if (map[randRow][randCol] != -1) {
                map[randRow][randCol] = -1;
                count++;
            }
        }
    }


    public void printMap(Integer[][] map) {
        System.out.println("Map:");
        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == -1) {
                    System.out.print(" * ");
                } else if (map[i][j] == 0) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" " + map[i][j] + " ");
                }
            }
        }
        System.out.println();
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
