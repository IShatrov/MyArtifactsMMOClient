import java.util.function.BiFunction;

public enum MenuCommand {
    QUIT("quit", null),
    MOVE("move", ((character, args) -> character.move(Integer.parseInt(args[0]), Integer.parseInt(args[1])))),
    FIGHT("fight", ((character, args) -> character.fight()));
    
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
