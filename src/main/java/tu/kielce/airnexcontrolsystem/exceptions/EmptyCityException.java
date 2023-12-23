package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyCityException extends AirNexControlSystemException {
    public EmptyCityException() {
        super("City cannot be null or blank");
    }
}
