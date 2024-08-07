import java.util.function.BiFunction;

public enum MenuCommand {
    QUIT("quit", null),
    MOVE("move", ((character, args) -> character.move(Integer.parseInt(args[0]), Integer.parseInt(args[1])))),
    FIGHT("fight", ((character, args) -> character.fight())),
    GATHERING("gathering", ((character, args) -> character.gathering())),
    UNEQUIP("unequip", (((character, args) -> character.unequip(args[0])))),
    CRAFTING("crafting", (((character, args) -> character.crafting(args[0])))),
    EQUIP("equip", (((character, args) -> character.equip(args[0], args[1]))));
    
    MenuCommand(String text, BiFunction<MMOCharacter, String[], Integer> action) {
        this.text = text;
        this.action = action;
    }

    public String getText() {
        return this.text;
    }
    
    public Integer doAction(MMOCharacter character, String[] args) {
        return action.apply(character, args);
    }

    private final String text;
    private final BiFunction<MMOCharacter, String[], Integer> action;
}
