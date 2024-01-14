package tu.kielce.airnexcontrolsystem.exceptions;

public class AirlineAlreadyExistsException extends AirNexControlSystemException {
    public AirlineAlreadyExistsException(final String airlineName) {
        super("Airline with name: " + airlineName + "already exists");
    }
}
