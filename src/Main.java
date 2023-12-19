import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static Player[] players;
    public static int[] cooldowns;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        players = new Player[4];
        cooldowns = new int[4];
        boolean gameRunning = true;
        int turn = 1;

        // CHARACTER CREATION
        for (int i = 1; i <= 4; i++) {
            // Turn start
            ConsoleHelper.prettyHeader(String.format("PLAYER %d's TURN", i));

            // Turn content
            String name = InputHelper.getNonZeroLenString(scanner, "Enter your name");

            int x = InputHelper.getInt(scanner, String.format("Enter %s's X position: ", name));
            int y = InputHelper.getInt(scanner, String.format("Enter %s's Y position: ", name));
            int z = InputHelper.getInt(scanner, String.format("Enter %s's Z position: ", name));

            players[i-1] = new Player(name, x, y, z);
            cooldowns[i-1] = 0;

            // Turn end
            ConsoleHelper.clearConsole();
        }

        // START GAME
        showPlayers("PREPARE YOUR PLAYERS");
        System.out.println("Press enter to continue..");
        scanner.nextLine();
        ConsoleHelper.clearConsole();
        printRules();
        System.out.println("Press enter to begin.");
        scanner.nextLine();

        // GAME MAIN LOOP
        // will not run if they are not ready to start, this is a feature now
        while (gameRunning) {
            // Turn start
            if (turn > 4) { turn = 1; }
            // Turn content
            ConsoleHelper.prettyHeader(String.format("PLAYER %s's TURN", players[turn-1].getName()));

            boolean verify = false;
            do {

                if (players[turn-1].getHp() <= 0) { continue; } // skip their turn if they are dead
                int move = InputHelper.getRangedInt(scanner, "Choose your move: Attack (1) | Move (2) | Teleport (3) | Skip (4)", 1, 4);

                if (move == 1) {
                    // attack code
                    int attacking = InputHelper.getRangedInt(scanner, "Which player would you like to attack? [1-4]", 1, 4);
                    if (players[attacking-1].getHp() <= 0) {
                        System.out.println("You are literally just beating a corpse right now. Monster.");
                    }

                    double distance = players[turn-1].getDistance(players[attacking-1]);
                    if (distance >= 4) {
                        players[turn-1].attack(players[attacking-1], 5);
                    }
                } else if (move == 2) {
                    // move code
                    int direction = InputHelper.getRangedInt(scanner, "Choose your direction: [1-6]", 1, 6);
                    int units = InputHelper.getRangedInt(scanner, "Choose the amount of units: [1-10]", 1, 10);
                    players[turn-1].move(direction, units);
                } else if (move == 3) {
                    if (cooldowns[turn-1] > 0) {
                        System.out.printf("Your teleport is on cooldown for %d more turns!\n", cooldowns[turn-1]);
                    } else {
                        // teleport code
                        int teleportTo = InputHelper.getRangedInt(scanner, "Which player would you like to teleport to? [1-4]", 1, 4);

                        if (teleportTo == turn || players[teleportTo-1].getHp() <= 0) {
                            System.out.println("You just wasted your teleport..");
                        } else {
                            players[turn-1].teleport(players[teleportTo-1]);
                        }
                        cooldowns[turn-1] = 2;
                    }
                } else if (move == 4) {
                    continue;
                }

                if (cooldowns[turn-1] > 0) { cooldowns[turn-1]--; }
                turn++;

                showPlayers("PLAYER STATS AFTER YOUR TURN");
                System.out.println("Press enter to continue..");
                scanner.nextLine();
                ConsoleHelper.clearConsole();

                if (allPlayersDead()) {
                    verify = true;
                }
            } while (!verify);
        }
    }

    public static void showPlayers(String header) {
        ConsoleHelper.prettyHeader(header);
        for (int i = 1; i <= 4; i++) {
            System.out.println(players[i-1].toString());
            System.out.println();
        }
    }

    public static boolean allPlayersDead() {
        boolean allDead = false;
        for (int i = 1; i <= 4; i++) {
            if (players[i-1].getHp() <= 0) {
                allDead = true;
            }
        }

        return allDead;
    }

    public static void printRules() { // FINISH THE FUNCTION
        ConsoleHelper.prettyHeader("RULES");

        System.out.println("You can either move or attack or teleport in a turn.");
        System.out.println("To attack, you have to be within 4 units of the player.");
        System.out.println("To teleport, you have to have your 2 turn cooldown for teleporting over with.");
    }
}