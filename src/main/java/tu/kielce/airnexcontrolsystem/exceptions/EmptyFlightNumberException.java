package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyFlightNumberException extends AirNexControlSystemException {
    public EmptyFlightNumberException() {
        super("Flight number cannot be null or blank");
    }
}
