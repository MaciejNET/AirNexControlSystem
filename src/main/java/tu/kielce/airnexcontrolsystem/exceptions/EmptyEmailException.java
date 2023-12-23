package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyEmailException extends AirNextControlSystemException {
    public EmptyEmailException() {
        super("Email cannot be null or blank");
    }
}
