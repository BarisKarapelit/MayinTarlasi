import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper { //Form 5

    private static int rowNumber; // Area of the board Rows
    private static int columnNumber; // Area of the board Columns
    static Integer[][] map; // -1: mine, 0: empty, 1-8: number of mines in adjacent squares
    static Integer[][] board; // 0: not opened, 1: opened, 2: flagged
    private static boolean[][] revealed; // true: revealed, false: not revealed
    static int numCols; // number of columns
    static int numRows, size, userRowInt = 0, userColumnInt = 0; // number of rows
    static Random random = new Random(); // random number generator
    static Integer[] mineLocation; // location of mine
    static Boolean startPage;

    // Constructor
    public MineSweeper(int rowNumber, int columnNumber, Boolean startPage) {
        setRowNumber(rowNumber); // set row number
        setColumnNumber(columnNumber);// set column number
        numRows = getRowNumber(); // get row number
        numCols = getColumnNumber(); // get column number
        map = new Integer[numRows][numCols]; // initialize map
        board = new Integer[numRows][numCols];  // initialize board
        size = numRows * numCols; // size of the board
        MineSweeper.startPage = startPage; // start page
        revealed = new boolean[numRows][numCols]; // initialize revealed
    }


    // Main method
    public void run() throws InterruptedException {
        DynamicCentering dynamicCentering = new DynamicCentering();
        boolean isPlay = true, isSuccessfulRow = true, isSuccessfulColumn = true;
        int success = 0;
        String mineSweeperPage = "";
        Scanner scanner = new Scanner(System.in);
        randomNumberGenerate();
        mineSweeperPage = print(mineLocation);
        do {

            isSuccessfulRow = true;
            if (startPage) {
                userPanelAnswer(mineSweeperPage);
                startPage = false;
            }
            //Form 9
            System.out.println("\nPlease enter the number of rows and columns you want to play.\n");
            // Input validation for row number
            while (Boolean.TRUE.equals(isSuccessfulRow)) {
                System.out.print("Enter the number of Row: ");
                userRowInt = scanner.nextInt()-1;

                System.out.print("Enter the number of Column: ");
                userColumnInt = scanner.nextInt()-1;
                //Form 10
                if ((userRowInt >= 0 && userRowInt < map.length)&&(userColumnInt >= 0 && userColumnInt < map.length)) {
                    if (revealed[userRowInt][userColumnInt]) {
                        System.err.println("This area is already open. Please enter a different area.");

                    } else {
                        isSuccessfulRow = false;
                    }

                } else {
                    System.out.printf("Please enter a number between 0 and %d\n", map.length);
                }
            }


            // Check if the user has stepped on a mine
            //Form 13
            if (map[userRowInt][userColumnInt] == -1) {
                isPlay = getaBoolean(scanner, dynamicCentering, false);
            } else {
                board[userRowInt][userColumnInt] = findNeighbors(userRowInt, userColumnInt);
                revealed[userRowInt][userColumnInt] = true;
                //Form 11
                mineSweeperPage = userPanel(dynamicCentering);
                // Check if the user has won
                //Form 14
                success++;
                if (success == (size - (size / 4))) {
                    isPlay = getaBoolean(scanner, dynamicCentering, true);
                }
            }

        } while (Boolean.TRUE.equals(isPlay));
    }

    // Find the number of mines in adjacent squares
    //Form 12
    // Girilen noktada mayın yoksa etrafındaki mayın sayısi hesaplanıyor.
    private static int findNeighbors(int row, int col) {
        int count = 0;

        int rStartIndex = Math.max(row - 1, 0);
        int rEndIndex = (row + 1 >= map.length) ? row : row + 1;

        int cStartIndex = Math.max(col - 1, 0);
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

    private static void userPanelAnswer(String mineSweeperPage) {
        System.out.println();
        DynamicCentering.setCenteringPage(Contact.bracket);
        System.out.println();

        StringBuilder mineSweeperPageBuilder = new StringBuilder(mineSweeperPage);
        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] > 0) {
                    mineSweeperPageBuilder.append(board[userRowInt][userColumnInt]);
                } else {
                    mineSweeperPageBuilder.append(" - ");
                }
            }
            DynamicCentering.setCenteringPage(mineSweeperPageBuilder.toString());
            mineSweeperPageBuilder = new StringBuilder();
        }
        System.out.println();
        DynamicCentering.setCenteringPage(Contact.bracket);
    }

    private static String userPanel(DynamicCentering dynamicCentering) {
        StringBuilder mineSweeperPage = new StringBuilder();
        startPage = false;
        System.out.println();
        DynamicCentering.setCenteringPage(Contact.bracket);
        System.out.println();
        // Print the board
        //Form 12
        // Girilen noktada mayın yoksa etrafındaki mayın sayısı veya 0 değeri yerine yazılmış oluyor.
        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (!revealed[i][j]) {
                    mineSweeperPage.append(" - ");
                } else if (revealed[i][j]) {
                    mineSweeperPage.append(" ").append(board[i][j]).append(" ");
                }
            }
            DynamicCentering.setCenteringPage(mineSweeperPage.toString());
            mineSweeperPage = new StringBuilder();
        }
        System.out.println();
        DynamicCentering.setCenteringPage(Contact.bracket);
        return mineSweeperPage.toString();
    }

    // Generate random mine locations
    //Form 8
    private static void randomNumberGenerate() {
        // Initialize mineLocation array
        mineLocation = new Integer[numRows];
        // Generate random mine locations
        int numMines = (numRows * numCols) / 4;

        for (int i = 0; i < numMines; i++) {
            int location;
            boolean isDuplicate;
            int attempts = 0;
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

                attempts++;
                if (attempts > 100) {
                    return;
                }
            } while (isDuplicate);

            mineLocation[i] = location;
        }
    }


    //Form 6
    private static Boolean getaBoolean(Scanner scanner, DynamicCentering dynamicCentering, Boolean gameStatus) throws InterruptedException {
        //Form 15
        //Kullanıcının oyunu kaybetme ya da kazanma durumunda uygun mesajlar kullanıcıya
        //gösteriliyor ve kullanıcıya tekrar oynamak isteyip istemediği soruluyor.
        Boolean isPlay;
        if (gameStatus) {
            DynamicCentering.setCenteringPage("You win!");
            DynamicCentering.setCenteringPage("Do you want to play? (1: Yes, 2: No)");
            int b = scanner.nextInt();
            if (b == 1) {
                isPlay = true;
                Main.main(null);

            } else {
                System.out.println("Thank you for playing!");
                isPlay = false;
            }

        } else {
            DynamicCentering.setCenteringPage("You lose!");
            DynamicCentering.setCenteringPage("Do you want to play? (1: Yes, 2: No)");
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

    private static String print( Integer[] randomBoom) {
        StringBuilder mineSweeperPage = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (randomBoom[i] != null) {
                    if (j == randomBoom[i]) {
                        // Mayinlar
                        map[i][j] = -1;
                        mineSweeperPage.append(" * ");
                    } else {
                        // Bos alanlar
                        map[i][j] = 0;
                        mineSweeperPage.append(" - ");
                    }
                } else {
                    // Bos alanlar
                    map[i][j] = 0;
                    mineSweeperPage.append(" - ");
                }

            }
            DynamicCentering.setCenteringPage(mineSweeperPage.toString());
            mineSweeperPage = new StringBuilder();


        }
        board = Arrays.copyOf(map, map.length);
        return mineSweeperPage.toString();
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
