public class Extra {

    public static void isEndRoom() {
        System.out.println("Thanks for playing.");
    }

    public static void printRoomInfo(int roomType){
        switch(roomType) {
            case 1:
                System.out.println("The room is empty.");
                break;
            case 2:
                System.out.println("The only thing in this room is the lever we flipped.");
                break;
            case 3:
                System.out.println("This is a secret room with armor! Don't forget to (PICKUP) and (EQUIP).");
                break;
        }
    }

}