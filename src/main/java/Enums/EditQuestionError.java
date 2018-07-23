package Enums;

public enum EditQuestionError {

    NO_ERROR_DETECTED(0, ""),
    ADD_NEW_QUESTION_FAILED_ERROR(1, "Das hinzufügen einer neuen Frage ist gescheitert."),
    EDIT_QUESTION_FAILED_ERROR(2, "Das beabeiten der Frage ist gescheitert."),
    REQUIRED_INFORMATION_MISSING(3, "Sie haben wichtige Informationen zur Frage nicht angegeben!"),
    CORRECT_ANSWERS_NOT_VALID_ERROR(4, "Bitte überprüfen sie die Antwortmöglichkeiten und die richtigen Antworten");

    private final int code;
    private final String description;

    private EditQuestionError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCode() {
        return this.code;
    }
}
