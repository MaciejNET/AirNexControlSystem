package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class AirlineNotExistsException extends AirNexControlSystemException {
    public AirlineNotExistsException(final String airlineName) {
        super("Airline with name " + airlineName + " does not exists");
    }
}
