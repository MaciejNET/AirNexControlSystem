package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class PastDepartureException extends AirNexControlSystemException {
    public PastDepartureException() {
        super("Departure date cannot be in the past");
    }
}
