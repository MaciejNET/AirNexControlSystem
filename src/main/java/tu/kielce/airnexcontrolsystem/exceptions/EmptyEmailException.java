package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyEmailException extends AirNexControlSystemException {
    public EmptyEmailException() {
        super("Email cannot be null or blank");
    }
}
