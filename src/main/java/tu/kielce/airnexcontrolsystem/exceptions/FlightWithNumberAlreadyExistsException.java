package tu.kielce.airnexcontrolsystem.exceptions;

public class FlightWithNumberAlreadyExistsException extends AirNexControlSystemException {
    public FlightWithNumberAlreadyExistsException(final String flightNumber) {
        super("Flight with number " + flightNumber + " already exists");
    }
}

