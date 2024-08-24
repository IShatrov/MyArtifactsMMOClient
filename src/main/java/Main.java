import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Please enter token:");
        String token = scn.nextLine();

        System.out.println("Please enter character name:");
        String name = scn.nextLine();

        MMOCharacter character = new MMOCharacter(token, name);

        while (true) {
            if (!scn.hasNext()) {
                continue;
            }

            String input = scn.nextLine();

            String[] commandArgs = input.split("\s+");

            MenuCommand menuCommand = null;

            for (MenuCommand mc : MenuCommand.values()) {
                if (commandArgs[0].equals(mc.getText())) {
                    menuCommand = mc;
                    break;
                }
            }

            if (menuCommand == null) {
                System.out.println("Command " + commandArgs[0] + " not found");
                continue;
            }

            switch (menuCommand) {
                case QUIT -> {
                    System.out.println("Thank you for playing!");
                    return;
                }
                default -> menuCommand.doAction(character, Arrays.copyOfRange(commandArgs, 1, commandArgs.length));
            }
        }
    }
}