package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyFirstNameException extends AirNexControlSystemException {
    public EmptyFirstNameException() {
        super("First name cannot be null or blank");
    }
}
