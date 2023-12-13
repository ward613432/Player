import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // simulate p1
        Player player1 = new Player();

        // simulate p2
        String p2Name = InputHelper.getNonZeroLenString(scanner, "Enter p2's name: ");
        int p2X = InputHelper.getInt(scanner, String.format("Enter %s's X position: ", p2Name));
        int p2Y = InputHelper.getInt(scanner, String.format("Enter %s's Y position: ", p2Name));
        int p2Z = InputHelper.getInt(scanner, String.format("Enter %s's Z position: ", p2Name));
        Player player2 = new Player(p2Name, p2X, p2Y, p2Z);

        // simulate p3
        String p3Name = InputHelper.getNonZeroLenString(scanner, "Enter p3's name: ");
        int p3X = InputHelper.getInt(scanner, String.format("Enter %s's X position: ", p3Name));
        int p3Y = InputHelper.getInt(scanner, String.format("Enter %s's Y position: ", p3Name));
        int p3Z = InputHelper.getInt(scanner, String.format("Enter %s's Z position: ", p3Name));
        int p3Hp = InputHelper.getRangedInt(scanner, String.format("Enter %s's Z health: ", p3Name), 1 ,100); // can't start dead, 1 instead of 0
        int p3Direction = InputHelper.getRangedInt(scanner, String.format("Enter %s's Z direction (1-6): ", p3Name), 1, 6);
        Player player3 = new Player(p3Name, p3X, p3Y, p3Z, p3Hp, p3Direction);

        // print players
        System.out.println(player1.toString()+"\n");
        System.out.println(player2.toString()+"\n");
        System.out.println(player3.toString());
    }
}