public class Map {

    private int leversFlipped;

    private int[][] map = {
        {0, 0, 0, 1, 3, 1, 0, 0, 0},
        {0, 0, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 0},
        {1, 1, 1, 0, 0, 0, 1, 1, 1},
        {2, 1, 1, 0, 8, 0, 1, 1, 2},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 1, 1, 1, 1, 1, 1, 1, 0},
        {0, 0, 1, 1, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 0}
    };

    public int getValueAt(int x, int y) { return map[y][x]; }
    public void flipLever(int x, int y) { 
        map[y][x] = 1;
        leversFlipped++;
        
        // if both levers are flipped, open door
        if(leversFlipped >= 2) {
            map[4][4] = 9;
        }
    }
    public int getFlippedLevers() { return leversFlipped; }

    public boolean isPassable(int x, int y) {
        if (y < 0 || y >= map.length || x < 0 || x >= map[y].length) return false;
        return map[y][x] != 0;
    }

    public void print(Character character) {
        System.out.println("+---+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("|");
                if (j == character.getX() && i == character.getY()) {
                    System.out.print(" O ");
                }else {
                    switch(map[i][j]) {
                        default:                        // EMPTY
                            System.out.print("   ");
                            break;
                        case 0:                         // WALL
                            System.out.print(" █ ");
                            break;
                        case 2:                         // LEVER
                            System.out.print(" / ");
                            break;
                        case 8:                         // LOCKED DOOR (blends in with wall)
                            System.out.print(" ▓ ");
                            break;
                        case 9:                         // OPEN DOOR
                            System.out.print(" ░ ");
                            break;
                    }
                }
            }
            System.out.println("|\n+---+---+---+---+---+---+---+---+---+");
        }
        System.out.println("You are in room (" + character.getX() + ", " + character.getY() + "). Where would you like to go now?");
    }

}