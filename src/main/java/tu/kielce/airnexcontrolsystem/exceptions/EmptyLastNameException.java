package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyLastNameException extends AirNexControlSystemException {
    public EmptyLastNameException() {
        super("Last name cannot be null or blank");
    }
}
