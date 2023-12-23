package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class SameAirportException extends AirNextControlSystemException{
    public SameAirportException() {
        super("Departure and arrival airports cannot be the same");
    }
}
