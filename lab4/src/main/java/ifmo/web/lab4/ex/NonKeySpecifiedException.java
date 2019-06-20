package ifmo.web.lab4.ex;

public class NonKeySpecifiedException extends Exception {

    public static NonKeySpecifiedException DEFAULT_INSTANCE =
            new NonKeySpecifiedException("At least one key must be specified");

    public NonKeySpecifiedException(String message) {
        super(message);
    }
}