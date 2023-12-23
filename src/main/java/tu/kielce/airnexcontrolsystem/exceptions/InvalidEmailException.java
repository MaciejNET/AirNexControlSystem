package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidEmailException extends AirNexControlSystemException {
    public InvalidEmailException() {
        super("Email is invalid");
    }
}
