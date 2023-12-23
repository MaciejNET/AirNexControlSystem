package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidEmailException extends AirNextControlSystemException {
    public InvalidEmailException() {
        super("Email is invalid");
    }
}
