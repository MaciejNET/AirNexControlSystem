package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidFlightNumberException extends AirNextControlSystemException {
    public InvalidFlightNumberException() {
        super("Flight number is invalid");
    }
}
