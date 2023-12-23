package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidArrivalException extends AirNextControlSystemException{
    public InvalidArrivalException() {
        super("Arrival date cannot be before departure date");
    }
}
