import java.io.IOException;
import java.util.Scanner;

public class DynamicCentering {
    public static void main(String[] args) {
        String textToCenter = "This is centered text";
        // Get the console width
        setCenteringPage(textToCenter);
    }

    static void setCenteringPage(String textToCenter) {
        int consoleWidth = getConsoleWidth();

        int padding = (consoleWidth - textToCenter.length()) / 2;

        // Print the padding spaces followed by the centered text
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println(textToCenter);
    }

    public static int getConsoleWidth() {
        try {
            Process process = Runtime.getRuntime().exec("cmd /c mode");
            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Columns:")) {
                    return Integer.parseInt(line.split(":")[1].trim());
                }
            }
            return 80;  // Default width if unable to get console width
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions
            e.printStackTrace();
            return 80;  // Default width if unable to get console width
        }
    }
}
