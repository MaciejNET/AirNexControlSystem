package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyFlightNumberException extends AirNextControlSystemException {
    public EmptyFlightNumberException() {
        super("Flight number cannot be null or blank");
    }
}
