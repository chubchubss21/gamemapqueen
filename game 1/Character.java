public class Character {

    private Map map;
    private int x, y;
    private boolean hasArmour, armourEquipped;

    public Character(Map map, int x, int y) {
        this.map = map;
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isArmourEquipped() { return armourEquipped; }

    public void move(int x, int y) {
        if(map.isPassable(x, y)) {
            this.x = x;
            this.y = y;
        }
    }

    public void pickUpArmour() {
        this.hasArmour = true;
    }

    public boolean equipArmour() {
        if(hasArmour) armourEquipped = true;
        return armourEquipped;
    }

}
