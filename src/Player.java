public class Player {
    private static int numPlayers = 1;
    private int x;
    private int y;
    private int z;
    private int direction; //North, 1; South, 2; Up, 3; Down, 4; East, 5; West, 6.
    private int hp;
    private String name;

    // Constructors
    public Player() {
        this("P"+numPlayers, 0, 0, 0, 20, 1); // change to num players
    }

    public Player(String name, int x, int y, int z) {
        this(name, x, y, z, 20, 1);
    }

    public Player(String name, int x, int y, int z, int hp, int direction) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hp = hp;
        this.direction = direction;
        numPlayers++;
    }

    // Accessors
    public String toString() {
        return String.format("Name: %s\nPosition: X %d Y %d Z %d\nHealth: %d\nDirection: %d", name, x, y, z, hp, direction);
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getHp() {
        return hp;
    }

    public int getDirection() {
        return direction;
    }

    // Mutators
    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(100, hp));
    }

    public void setDirection(int direction) {
        this.direction = Math.max(1, Math.min(6, direction));
    }

    public void teleport(Player player) {
        this.teleport(player.getX(), player.getY(), player.getZ());
    }

    public void teleport(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void attack(Player player, int damage) {
        player.setHp(player.getHp() - damage);
    }

    // Other
    double getDistance(Player player) {
        return this.getDistance(player.getX(), player.getY(), player.getZ());
    }

    double getDistance(int x, int y, int z) {
        // group1 group2 group3
        double groupOne = Math.pow((x - this.x), 2);
        double groupTwo = Math.pow((y - this.y), 2);
        double groupThree = Math.pow(z - this.z, 2);
        return Math.sqrt(groupOne + groupTwo + groupThree);
    }
}