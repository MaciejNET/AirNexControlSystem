package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń, Mariusz Ignaciuk
 */
public class AirlineNotExistsException extends AirNexControlSystemException {
    public AirlineNotExistsException(final String airlineName) {
        super("Airline with name " + airlineName + " does not exists");
    }
    public AirlineNotExistsException(final Long id){
        super("Airline with name " + id + "does not exists");
    }
}
