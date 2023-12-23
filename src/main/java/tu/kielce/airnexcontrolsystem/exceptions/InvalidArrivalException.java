package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidArrivalException extends AirNexControlSystemException {
    public InvalidArrivalException() {
        super("Arrival date cannot be before departure date");
    }
}
