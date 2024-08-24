public enum HTTPSErrorCode {
    NOT_FOUND(404, "Not found"),
    ACTION_ALREADY_IN_PROGRESS(486, "An action is already in progress by this character"),
    ALREADY_AT_DESTINATION(490, "Character already at destination"),
    SLOT_EMPTY(491, "SLot is empty"),
    INVENTORY_FULL(497, "Character inventory is full"),
    CHARACTER_NOT_FOUND(498, "Character not found"),
    CHARACTER_COOLDOWN(499, "Character in cooldown"),
    MONSTER_NOT_FOUND(598, "Monster not found on this location");

    HTTPSErrorCode(int code, String defaultText) {
        this.code = code;
        this.defaultText = defaultText;
    }

    public int getCode() {
        return this.code;
    }

    public String getDefaultText() {
        return this.defaultText;
    }

    public static String getErrorText(int code) {
        for (var errorCode : HTTPSErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode.getDefaultText();
            }
        }

        return "Unknown error";
    }

    private final int code;
    private final String defaultText;
}
