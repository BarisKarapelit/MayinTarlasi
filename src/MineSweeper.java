import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    private static int rowNumber;
    private static int columnNumber;
    static Integer[][] map; // -1: mine, 0: empty, 1-8: number of mines in adjacent squares
    static Integer[][] board; // 0: not opened, 1: opened, 2: flagged
    static int numCols;
    static int numRows;
    static Random random = new Random();

    public MineSweeper(int rowNumber, int columnNumber) {
        setRowNumber(rowNumber);
        setColumnNumber(columnNumber);
        this.numRows = getRowNumber();
        this.numCols = getColumnNumber();
        this.map = new Integer[numRows][numCols];
        this.board = new Integer[numRows][numCols];
    }

    public void run() {
        DynamicCentering dynamicCentering = new DynamicCentering();
        Boolean isPlay = true, gameStatus = true;
        String mineSweeperPage = "";
        Scanner scanner = new Scanner(System.in);
        prepareGame();
        int randomBoom = random.nextInt(numCols);
        do {
            dynamicCentering.setCenteringPage("==================================================");
            mineSweeperPage = print(mineSweeperPage, dynamicCentering, randomBoom);

            // Input validation for row number
            int userRowInt = -1;
            while (userRowInt < 0 || userRowInt >= numRows) {
                System.out.print("Enter the number of Row: ");
                String userRow = scanner.nextLine();
                if (!userRow.isEmpty()) {
                    try {
                        userRowInt = Integer.parseInt(userRow);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }
            }

            // Input validation for column number
            int userColumnInt = -1;
            while (userColumnInt < 0 || userColumnInt >= numCols) {
                System.out.print("Enter the number of Column: ");
                String userColumn = scanner.nextLine();
                if (!userColumn.isEmpty()) {
                    try {
                        userColumnInt = Integer.parseInt(userColumn);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }
            }

            if (map[userRowInt][userColumnInt] == -1) {
                gameStatus = false;
                isPlay = getaBoolean(scanner, dynamicCentering, gameStatus);
            } else {
                gameStatus = true;
                checkBoom(userRowInt, userColumnInt);
                //isPlay = getaBoolean(scanner, dynamicCentering, gameStatus);
            }
        } while (isPlay);
    }


    private static void checkBoom(int userRow, int userColumn) {
        if (userColumn + 1 < numCols && map[userRow][userColumn + 1] == -1) {
            board[userRow][userColumn + 1]++;
        }
        if (userRow + 1 < numRows && map[userRow + 1][userColumn] == -1) {
            board[userRow + 1][userColumn]++;
        }
        if (userRow - 1 >= 0 && map[userRow - 1][userColumn] == -1) {
            board[userRow - 1][userColumn]++;
        }
        if (userColumn - 1 >= 0 && map[userRow][userColumn - 1] == -1) {
            board[userRow][userColumn - 1]++;
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
            } else {
                System.out.println("Thank you for playing!");
                isPlay = false;
            }
        }
        return isPlay;
    }

    private static String print(String mineSweeperPage, DynamicCentering dynamicCentering, int randomBoom) {
        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
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
        System.out.println("Map:");
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
        System.out.println("\nPlease enter the number of rows and columns you want to play.\n");
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
