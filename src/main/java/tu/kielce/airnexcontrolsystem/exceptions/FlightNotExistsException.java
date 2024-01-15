package tu.kielce.airnexcontrolsystem.exceptions;

public class FlightNotExistsException extends AirNexControlSystemException {
    public FlightNotExistsException(final Long id) {
        super("Flight with id " + id + " does not exist");
    }
}
