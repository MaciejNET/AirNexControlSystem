package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class AirlineMismatchException extends AirNexControlSystemException {
    public AirlineMismatchException() {
        super("Flight and plane must belong to the same airline");
    }
}
