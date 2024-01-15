package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class FlightNotExistsException extends AirNexControlSystemException {
    public FlightNotExistsException(final Long id) {
        super("Flight with id " + id + " does not exist");
    }
}
