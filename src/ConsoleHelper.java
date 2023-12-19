public class ConsoleHelper {
    public static void clearConsole() {
        for (int i = 1; i <= 50; i++) {
            System.out.println();
        }
    }

    public static void prettyHeader(String msg) {
        // guardian
        if (msg.length() > 54) { System.out.printf("The message \"%s\" is too long. (54 char max)\n"); }

        // calculate left/right spaces (odd messages will be 1 char off-center)
        int leftSpaces = (54 - msg.length()) / 2;
        int rightSpaces = 54 - leftSpaces - msg.length();

        //print front row
        printChar(60, "*", true);

        // print middle row
        printChar(3, "*", false);
        printChar(leftSpaces, " ", false);
        System.out.print(msg);
        printChar(rightSpaces, " ", false);
        printChar(3, "*", true);

        // print last row
        printChar(60, "*", true);
    }

    public static void printChar(int amount, String c, boolean breakAfter) {
        for (int i = 0; i < amount; i++) {
            System.out.print(c);
        }

        if (breakAfter) {
            System.out.println();
        }
    }
}