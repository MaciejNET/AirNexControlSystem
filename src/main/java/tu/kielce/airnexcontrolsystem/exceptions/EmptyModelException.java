package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyModelException extends AirNexControlSystemException {
    public EmptyModelException() {
        super("Model cannot be null or blank");
    }
}
