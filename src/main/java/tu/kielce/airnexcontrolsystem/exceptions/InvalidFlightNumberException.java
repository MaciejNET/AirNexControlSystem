package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidFlightNumberException extends AirNexControlSystemException {
    public InvalidFlightNumberException() {
        super("Flight number is invalid");
    }
}
