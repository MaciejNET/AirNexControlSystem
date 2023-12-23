package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyModelException extends AirNextControlSystemException {
    public EmptyModelException() {
        super("Model cannot be null or blank");
    }
}
