package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class AirlineMismatchException extends AirNextControlSystemException {
    public AirlineMismatchException() {
        super("Flight and plane must belong to the same airline");
    }
}
