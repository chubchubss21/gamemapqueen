import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Map map = new Map();

        Character player = new Character(map, 4, 8);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            map.print(player);
            System.out.println("Now in this room, you can go: north, east, south, west");
            System.out.print("Enter direction (north, east, south, west) or 'quit': ");
            System.out.print("or N, E, W or S: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Game over. Bye!");
                Extra.isEndRoom();
                break;
            }
            else if (input.equalsIgnoreCase("look")) {
                int roomType = map.getValueAt(player.getX(), player.getY());
                Extra.printRoomInfo(roomType);
                continue;
            }
            else if (input.toLowerCase().contains("equip")) {
                if (player.equipArmour()) System.out.println("You equipped the armour!");
                else System.out.println("You don't have the armour.");
            }
            else if (input.toLowerCase().contains("pick")) {
                if (map.getValueAt(player.getX(), player.getY()) == 3) {
                    player.pickUpArmour();
                    System.out.println("You picked up the armour.");
                } else System.out.println("There is nothing to pick up here.");
            }

            // movement
            int newX = player.getX();
            int newY = player.getY();
            if (input.equalsIgnoreCase("north") ||
                    (input.equalsIgnoreCase("n"))) {
                newY--;
            } else if (input.equalsIgnoreCase("east") ||
                    (input.equalsIgnoreCase("e"))) {
                newX++;
            } else if (input.equalsIgnoreCase("south") ||
                    (input.equalsIgnoreCase("s"))) {
                newY++;
            } else if (input.equalsIgnoreCase("west") ||
                    (input.equalsIgnoreCase("w"))) {
                newX--;
            }
            player.move(newX, newY);

            // events on certain room types
            switch (map.getValueAt(player.getX(), player.getY())) {
                case 2:     // lever
                    System.out.println("+---+---+---+---+---+---+---+---+---+");
                    System.out.println("| Would you like to flip the lever? |");
                    System.out.println("+---+---+                   +---+---+");
                    System.out.println("|          Enter (Y) or (N)         |");
                    System.out.println("+---+---+---+---+---+---+---+---+---+");
                    String answer = scanner.nextLine();

                    if (!answer.equalsIgnoreCase("Y")) {
                        answer = "";
                        int attempts = 0;
                        while (!answer.equalsIgnoreCase("N")) {
                            System.out.print("Are you sure");
                            for (int i = 0; i < attempts; i++) {
                                System.out.print(" you're sure");
                            }
                            System.out.print("? (y/n): ");
                            answer = scanner.nextLine();
                            attempts++;
                        }
                    }

                    map.flipLever(player.getX(), player.getY());
                    System.out.println("*Ker-chunk*\nYou flipped the lever!");
                    break;
                case 3:     // Armour room
                    System.out.println("This is a secret room with the armour!");
                    System.out.println("Don't forget to (PICKUP) your armor!");
                    System.out.println("Also (EQUIP) your armor or it will not help having it.");
                    break;
                case 8:     // locked door
                    System.out.println("The door is locked!");
                    break;
                case 9:     // open door
                    System.out.println("The door is open. Would you like to enter? YES OR NO?");
                    String response = scanner.nextLine();
                    if (!response.equalsIgnoreCase("yes") ||
                            (input.equalsIgnoreCase("y"))) {   // any response except Y or yes
                        System.out.println("Then be gone! *You are forcefully shoved backwards by a strong gust of wind.*");
                        player.move(player.getX(), player.getY() + 1);    // move down 1
                    } else {
                        System.out.println("You walk into the room, and the door slams shut behind you.");
                        System.out.println("The queen emerges from the shadows.");
                        System.out.println("She throws a fireball at you.");
                        if (player.isArmourEquipped()) {
                            // armour is equipped
                            System.out.println("The fireball deflects off your armour and disintegrates the queen. You win!");
                            Extra.isEndRoom();
                            return;
                        } else {
                            // armour is NOT equipped
                            System.out.println("The fireball obliterates your face. Game over!");
                            Extra.isEndRoom();
                            return;
                        }
                    }
                    break;
            }
        }
        scanner.close();
    }

}